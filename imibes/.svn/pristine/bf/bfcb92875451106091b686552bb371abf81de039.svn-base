package com.royi.duge.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import static org.apache.commons.net.ftp.FTPClientConfig.SYST_NT;

public class FtpScanner {

    private DownloadRouteBuilder builder;
    private final String LOCAL_PATH;
    private final String FTP_PATH;

    public FtpScanner(String LOCAL_PATH, String FTP_PATH) {
        this.LOCAL_PATH = LOCAL_PATH;
        this.FTP_PATH = FTP_PATH;
        this.builder = new DownloadRouteBuilder(LOCAL_PATH, FTP_PATH);
    }

    public void startPull() {
        FTPClient client = new FTPClient();
        client.setControlEncoding("GB2312");
        FTPClientConfig ftpClientConfig = new FTPClientConfig(SYST_NT);
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        // 添加过滤器, downloadFilter 在FTP 的url 定义，留意配置
        simpleRegistry.put("ftpClient", client);
        simpleRegistry.put("ftpClientConfig", ftpClientConfig);
        CamelContext context = new DefaultCamelContext(simpleRegistry);

        try {
            context.addRoutes(builder);
            context.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
