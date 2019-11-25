package com.hs.platform.station.third.foshan.socket;

import com.hs.platform.station.third.foshan.service.FoshanApiService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.hs.platform.station.third.foshan.service.FoshanApiService.*;

@Component
public class SendMsgClientHandler extends IoHandlerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(SendMsgClientHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        session.closeNow();
        LOGGER.warn(cause.getMessage(), cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        long startTime = System.currentTimeMillis();
        try{
            super.messageReceived(session, message);
            if (!"receive_online".equals(message.toString())) {
                if(message.toString().contains("5002")){
                    //LOGGER.info("receive heartbeat:" + message.toString());
                }
                else if(message.toString().contains("5020")){
                    receiveCount++;
                    long endTime = System.currentTimeMillis();
                    LOGGER.info("receive success:totalCount:" + totalCount + ",sendCount:" + sendCount + ",sendSuccessCount:"
                            + sendSuccessCount + ",receiveCount:" + receiveCount + ",cost:" + (endTime - startTime));
                }
            }
        }catch (Exception e){
            LOGGER.info("接收到的消息失败！" + message,e);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message)  {
        long startTime = System.currentTimeMillis();
        try{
            super.messageSent(session, message);
            int msgType = ((FoshanMessage)message).getMessageType();
            if(msgType == 20482){
                //LOGGER.info("send heartbeat");
            }
            else if(msgType == 20512){
                sendSuccessCount++;
                long endTime = System.currentTimeMillis();
                LOGGER.info("send data,cost:" + (endTime - startTime));
            }
        }catch (Exception e){
            LOGGER.info("向服务器发送消息失败！" + message,e);
        }

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionCreated(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionOpened(session);
    }
}
