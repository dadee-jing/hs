package com.ruoyi.duge.third.foshan.socket;

import com.ruoyi.duge.service.IConfigDataService;
import org.apache.mina.core.RuntimeIoException;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SendMsgClient {

    private IoSession session;
    private NioSocketConnector connector;
    //private final IConfigDataService configDataService;
    private final SendMsgClientHandler sendMsgClientHandler;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static final Logger log = LoggerFactory.getLogger(SendMsgClient.class);
    private String host;
    private int port;

    @Autowired
    public SendMsgClient(IConfigDataService configDataService,
                         SendMsgClientHandler sendMsgClientHandler) {
        //this.configDataService = configDataService;
        this.sendMsgClientHandler = sendMsgClientHandler;
        host = configDataService.getConfigValue("foshan.tcp.host");
        port = Integer.parseInt(configDataService.getConfigValue("foshan.tcp.port"));
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


        // 断线重连
        /*
        connector.getFilterChain().addFirst("reconnection", new IoFilterAdapter() {
            @Override
            public void sessionClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(5000);
                        ConnectFuture future = connector.connect();
                        future.awaitUninterruptibly();// 等待连接创建成功
                        session = future.getSession();// 获取会话
                        if (session.isConnected()) {
                            log.info("断线重连[" + connector.getDefaultRemoteAddress().getHostName() + ":"
                                    + connector.getDefaultRemoteAddress().getPort() + "]成功");
                            break;
                        }
                    } catch (Exception ex) {
                        log.info("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());
                    }
                }
            }
        });

        /*
        // 协议解析
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("GBK"),
                        LineDelimiter.DEFAULT.getValue(), LineDelimiter.DEFAULT.getValue())));
        */

    }

    //@Scheduled(fixedDelay = 9000, initialDelay = 5000)
    public void heartbeat() {
        sendMessage(FoshanMessage.builder().messageType(FoshanMessage.HEART_BEAT_MSG).build());
    }

    public synchronized void connect() {
        if (null != session && session.isConnected()) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            try {
                ConnectFuture future = connector.connect();
                // 等待连接创建成功
                future.awaitUninterruptibly();
                // 获取会话
                session = future.getSession();
                log.info("连接佛山服务端" + host + ":" + port + "[成功],时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                break;
            } catch (RuntimeIoException e) {
                log.error("连接佛山服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e.getMessage());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    log.error("连接佛山服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e1.getMessage());
                }
            }
        }
    }

    public synchronized void sendMessage(FoshanMessage foshanMessage) {
        if (null == session || !session.isConnected()) {
            connect();
        }
        session.write(foshanMessage);
    }
}
