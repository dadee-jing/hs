package com.royi.duge.camel;

import org.apache.camel.builder.RouteBuilder;

public class DownloadRouteBuilder extends RouteBuilder {

    private final String LOCAL_PATH;
    private final String FTP_PATH;

    public DownloadRouteBuilder(String LOCAL_PATH, String FTP_PATH) {
        this.LOCAL_PATH = LOCAL_PATH;
        this.FTP_PATH = FTP_PATH;
    }

    @Override
    public void configure() throws Exception {
        try {
            from(FTP_PATH)      // ftp服务器源地址
                    .to(LOCAL_PATH)   // 模板存储地址
                    .log("Downloaded file ${file:name} complete."); // 下载后处理文件
        } catch (Exception e) {
            throw e;
        }
    }
}
