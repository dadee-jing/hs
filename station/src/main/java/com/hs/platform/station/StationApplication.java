package com.hs.platform.station;

import com.hs.platform.station.codec.XxxCodecFactory;
import com.hs.platform.station.handler.DataIntoDBHandler;
import com.hs.platform.station.io.LWHClient;
import com.hs.platform.station.util.NoMatchUtil;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {"com.hs.platform.station"})
@org.springframework.context.annotation.Configuration
@EnableScheduling
@EnableAsync
public class StationApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(StationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StationApplication.class, args);

        try {
            Integer newlxTcpServerPort = Constants.newlx_tcp_server_port;
            String lwhServerHost = Constants.lwh_server_host;
            Integer lwhServerPort = Constants.lwh_server_port;
            LOGGER.info("newlx_tcp_server_port:" + newlxTcpServerPort);
            LOGGER.info("lwh_server_host:" + lwhServerHost);
            LOGGER.info("lwh_server_port:" + lwhServerPort);

            // 打开tcpserver接收新流向称重数据
            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            ProtocolCodecFactory factory = new XxxCodecFactory();
            acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(factory));
            DataIntoDBHandler handler = new DataIntoDBHandler();
            acceptor.setHandler(handler);

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            // 防止第一次bind失败，重试直至成功
            executorService.scheduleAtFixedRate(() -> {
                if (!acceptor.isActive()) {
                    try {
                        LOGGER.info("trying bind:" + lwhServerHost + ":" + newlxTcpServerPort);
                        acceptor.bind(new InetSocketAddress(lwhServerHost, newlxTcpServerPort));
                        LOGGER.info("Weight Server is listening at port " + newlxTcpServerPort);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }, 0, 30, TimeUnit.SECONDS);

            // 打开tcpclient请求外廓数据
            LWHClient client = new LWHClient(lwhServerHost, lwhServerPort);
            LOGGER.info("LWH client connect : " + lwhServerHost + " : " + lwhServerPort + " success.");

            // 开启匹配线程
            //NoMatchUtil.notMatchInsertDB();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
