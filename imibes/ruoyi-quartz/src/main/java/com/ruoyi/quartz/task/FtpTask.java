package com.ruoyi.quartz.task;

import com.royi.duge.camel.FtpScanner;
import com.ruoyi.duge.domain.FtpServer;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IFtpServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("ftpTask")
public class FtpTask {

    private static final Logger log = LoggerFactory.getLogger(FtpTask.class);
    private final IFtpServerService iFtpServerService;
    private final IConfigDataService configDataService;

    @Autowired
    public FtpTask(IConfigDataService configDataService,
                   IFtpServerService iFtpServerService) {
        this.configDataService = configDataService;
        this.iFtpServerService = iFtpServerService;
    }

    public void scanFtp() {
        log.info("scan ftp start");
        String ftpPath = configDataService.getConfigValue("ftp_path");
        List<FtpServer> ftpServerList = iFtpServerService.selectDataList(new FtpServer());
        if (null != ftpServerList) {
            ftpServerList.forEach(ftpServer -> {
                FtpScanner ftpScanner = new FtpScanner("file:" + ftpPath + ftpServer.getName(), ftpServer.getUrl());
                ExecutorService singlePool = Executors.newSingleThreadExecutor();
                singlePool.submit(ftpScanner::startPull);
            });
        }
    }
}
