package com.hs.platform.station.third.foshan.socket;

import com.hs.platform.station.third.foshan.service.FoshanApiService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.hs.platform.station.third.foshan.service.FoshanApiService.*;
import static com.hs.platform.station.third.foshan.socket.FoshanMessage.BODY_MSG;
import static com.hs.platform.station.third.foshan.socket.FoshanMessage.HEART_BEAT_MSG;

@Component
public class SendMsgClientHandler extends IoHandlerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(SendMsgClientHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        //session.closeNow();
        LOGGER.warn(cause.getMessage(), cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        try {
            super.messageReceived(session, message);
            FoshanRspMessage foshanRspMessage = (FoshanRspMessage) message;
            if ("5002".equals(foshanRspMessage.getInstrId())) {
                //LOGGER.info("receive heartbeat:" + message.toString());
            }
            else if ("5020".equals(foshanRspMessage.getInstrId())) {
                receiveCount.increment();
                String plate = waitingResponseMap.remove(foshanRspMessage.getSerialNo());
                if (null != plate) {
                    // 通知正在等待的返回
                    if (null != failPlateMap.remove(plate)) {
                        // 处理迟到返回，修正统计
                        successCount.increment();
                        failCount.decrement();
                    } else {
                        try {
                            okPlateMap.put(plate, 1);
                            lock.lockInterruptibly();
                            cond.signal();
                        } catch (Exception e) {
                            LOGGER.error("lock error", e);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
                LOGGER.info("receive 5020:" + ((FoshanRspMessage) message).getSerialNo() + " " + plate);
            }
        } catch (Exception e) {
            LOGGER.info("接收到的消息失败！" + message, e);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) {
        try {
            super.messageSent(session, message);
            int msgType = ((FoshanMessage) message).getMessageType();
            if (msgType == HEART_BEAT_MSG) {
                //LOGGER.info("send heartbeat");
            } else if (msgType == BODY_MSG) {
                sendSuccessCount.increment();
            }
        } catch (Exception e) {
            LOGGER.info("向服务器发送消息失败！" + message, e);
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOGGER.info("sessionClosed");
        super.sessionClosed(session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOGGER.info("sessionCreated");
        super.sessionCreated(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        LOGGER.info("sessionIdle");
        super.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOGGER.info("sessionOpened");
        super.sessionOpened(session);
    }
}
