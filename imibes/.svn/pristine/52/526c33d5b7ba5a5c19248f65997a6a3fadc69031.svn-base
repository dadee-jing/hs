package com.ruoyi.duge.third.foshan.socket;

import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.third.foshan.service.FoshanApiService;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ruoyi.duge.third.foshan.socket.FoshanMessage.BODY_MSG;

@Component
public class SendMsgClient {

    private IoSession session;
    private NioSocketConnector connector;
    private final SendMsgClientHandler sendMsgClientHandler;
    private static final Logger log = LoggerFactory.getLogger(SendMsgClient.class);
    private String host;
    private int port;
    private final Object lockObject;

    @Autowired
    public SendMsgClient(IConfigDataService configDataService,
                         SendMsgClientHandler sendMsgClientHandler) {
        this.sendMsgClientHandler = sendMsgClientHandler;
        host = configDataService.getConfigValue("foshan.tcp.host");
        port = Integer.parseInt(configDataService.getConfigValue("foshan.tcp.port"));
        this.lockObject = new Object();
    }

    public boolean isConnected() {
        return null != session && session.isActive();
    }

    @PostConstruct
    public void init() {

        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(10000);// 10s 超时
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new FoshanCodecFactory()));
        connector.getSessionConfig().setReceiveBufferSize(10240);// 设置接收缓冲区的大小
        connector.getSessionConfig().setSendBufferSize(10240);// 设置发送缓冲区的大小
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30000); // 读写都空闲时间:30秒
        connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 40000);// 读(接收通道)空闲时间:40秒
        connector.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE, 50000);// 写(发送通道)空闲时间:50秒
        // 添加处理器
        connector.setHandler(sendMsgClientHandler);
        connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));// 设置默认访问地址
    }

    @Scheduled(fixedDelay = 1000 * 30, initialDelay = 10000)
    public void heartbeat() {
        sendMessage(FoshanMessage.builder().messageType(FoshanMessage.HEART_BEAT_MSG).build());
    }

    public boolean connect() {
        synchronized (lockObject){
            if (null != session && session.isConnected()) {
                return true;
            }
            for (int i = 0; i < 5; i++) {
                try {
                    ConnectFuture future = connector.connect();
                    // 等待连接创建成功
                    future.awaitUninterruptibly();
                    // 获取会话
                    session = future.getSession();
                    log.info("连接佛山服务端" + host + ":" + port + "[成功],时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    return true;
                } catch (Exception e) {
                    log.error("连接佛山服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e.getMessage());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        log.error("连接佛山服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e1.getMessage());
                    }
                }
            }
            return false;
        }
    }

    public boolean sendMessage(FoshanMessage foshanMessage) {
        if (connect()) {
            try{
                synchronized (lockObject){
                    session.write(foshanMessage).awaitUninterruptibly(10000);
                }
                if(foshanMessage.getMessageType() == BODY_MSG){
                    FoshanApiService.sendCount.increment();
                }
                return true;
            }catch (Exception e){
                log.info("sendMessage error",e);
            }
        }
        else{
            log.info("connect fail,send fail");
        }
        return false;
    }

}
