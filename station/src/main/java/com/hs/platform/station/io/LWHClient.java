package com.hs.platform.station.io;

import com.hs.platform.station.handler.LWHClientHandler;
import com.hs.platform.station.util.WeightAndLWHContainer;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LWHClient {
    private static Logger LOGGER = LoggerFactory.getLogger(LWHClient.class);
    public static final int CONNECT_TIMEOUT = 3000;
    private String host;
    private int port;
    private IoSession session;
    private NioSocketConnector connector;
    private ScheduledExecutorService scheduledExecutorService;
    private final Object lockObject;


    public LWHClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.lockObject = new Object();
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(10000);// 10s 超时

        // 断线重连
        connector.getFilterChain().addFirst("reconnection", new IoFilterAdapter() {
            @Override
            public void sessionClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
                for (; ; ) {
                    try {
                        synchronized (lockObject) {
                            Thread.sleep(3000);
                            ConnectFuture future = connector.connect();
                            future.awaitUninterruptibly();// 等待连接创建成功
                            session = future.getSession();// 获取会话
                            if (session.isConnected()) {
                                LOGGER.info("断线重连[" + connector.getDefaultRemoteAddress().getHostName() + ":"
                                        + connector.getDefaultRemoteAddress().getPort() + "]成功");
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        LOGGER.info("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());
                    }
                }
            }
        });
        // 协议解析
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("GBK"),
                        LineDelimiter.DEFAULT.getValue(), LineDelimiter.DEFAULT.getValue())));

        connector.getSessionConfig().setReceiveBufferSize(10240);// 设置接收缓冲区的大小
        connector.getSessionConfig().setSendBufferSize(10240);// 设置发送缓冲区的大小
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30000); // 读写都空闲时间:30秒
        connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 40000);// 读(接收通道)空闲时间:40秒
        connector.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE, 50000);// 写(发送通道)空闲时间:50秒
        // 添加处理器
        connector.setHandler(new LWHClientHandler());
        connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));// 设置默认访问地址
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::connect, 0, 120, TimeUnit.SECONDS);

    }

    public void connect() {
        synchronized (lockObject) {
            LOGGER.info("lwh connect");
            long nowTime = System.currentTimeMillis();
            if (LWHClientHandler.lwhHeartBeat && (nowTime - LWHClientHandler.heartBeatTime.get() > (60 * 1000))) {
                session.closeNow();
                session = null;
                LOGGER.info("timeout session close");
            }
            if (null != session && session.isConnected() && connector.isActive()) {
                //LOGGER.info("lwh connect," + session.isConnected() + "," + connector.isActive());
                try {
                    session.write("1");
                } catch (Exception e) {
                    session.closeNow();
                    session = null;
                    LOGGER.info("lwh heartbeat fail", e);
                }
                return;
            }
            try {
                ConnectFuture future = connector.connect();
                // 等待连接创建成功
                future.awaitUninterruptibly();
                // 获取会话
                session = future.getSession();
                LOGGER.info("连接服务端" + host + ":" + port + "[成功],时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } catch (RuntimeIoException e) {
                LOGGER.error("连接服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e.getMessage());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    LOGGER.error("连接服务端" + host + ":" + port + "失败,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",异常内容:" + e1.getMessage());
                }
            }
        }
    }

    public boolean isConnected() {
        return (session != null && session.isConnected());
    }

    /*
     * public void connect() { ConnectFuture connectFuture = connector.connect(new
     * InetSocketAddress(host, port));
     * connectFuture.awaitUninterruptibly(CONNECT_TIMEOUT); try { session =
     * connectFuture.getSession(); } catch (RuntimeIoException e) {
     * LOGGER.error(e.getMessage(), e); } }
     */
    public void disconnect() {
        if (session != null) {
            try {
                scheduledExecutorService.shutdownNow();
                session.closeNow().awaitUninterruptibly(CONNECT_TIMEOUT);
                session = null;
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

/*    public void reactiveScheduled() {
        try {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(this::connect, 0, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }*/

}
