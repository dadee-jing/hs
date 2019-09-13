package com.hs.platform.station.util;

import com.hs.platform.station.persistence.local.entity.WeightData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.LongAdder;

import static com.hs.platform.station.Constants.*;

@Component
public class ImageDownloadUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageDownloadUtil.class);

    final private Object clientLock = new Object();

    private FTPClient newlxFtpClient = null;
    private FTPClient shundeFtpClient = null;
    private ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(1);
    private final LongAdder count = new LongAdder();

    @PostConstruct
    void init() {
        newlxFtpClient = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        shundeFtpClient = FTPClientUtil.getFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
        /*
        scheduleService.scheduleAtFixedRate(() -> {
            try {
                newlxFtpClient = resetFTPClient(newlxFtpClient, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                shundeFtpClient = resetFTPClient(shundeFtpClient, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 5000, 3, TimeUnit.MINUTES);
        */
    }

    private FTPClient checkFTPClient(FTPClient ftpClient, boolean newlx) {
        if (null != ftpClient && ftpClient.isConnected() && ftpClient.isAvailable()) {
            return ftpClient;
        } else {
            return resetFTPClient(ftpClient, newlx);
        }
    }

    private FTPClient resetFTPClient(FTPClient ftpClient, boolean newlx) {
        LOGGER.info("to reset " + newlx);
        FTPClientUtil.ftpClose(ftpClient);
        FTPClient newOne;
        if (newlx) {
            newOne = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        } else {
            newOne = FTPClientUtil.getFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
        }
        count.reset();
        LOGGER.info("over reset " + newlx);
        return newOne;
    }


    public void submitDownloadTask(final WeightData entity) {
        try {

            final String stationId = station_id + "";
            // 按照固定目录存放 ftppath/stationID/date/   ftp/1/20190717/
            String targetParentPath = "/" + stationId + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");

            if (count.intValue() >= 100) {
                newlxFtpClient = resetFTPClient(newlxFtpClient, true);
                shundeFtpClient = resetFTPClient(shundeFtpClient, false);
            } else {
                newlxFtpClient = checkFTPClient(newlxFtpClient, true);
                shundeFtpClient = checkFTPClient(shundeFtpClient, false);
            }

            if (null != newlxFtpClient && null != shundeFtpClient) {
                String FtpHead = entity.getFtpHead();
                String FtpAxle = entity.getFtpAxle();
                String FtpTail = entity.getFtpTail();
                String FtpPriorHead = entity.getFtpPriorHead();
                String FtpPlate = entity.getFtpPlate();
                String FtpFullView = entity.getFtpFullView();

                //去下ftp服务器下载图片到应用服务器 ,循环下载多张图片和视频，最会关闭连接
                if (null != FtpPlate && !"".equals(FtpPlate)) {
                    FTPClientUtil.ftpToFtp(FtpPlate, targetParentPath + '/' + FtpPlate, newlxFtpClient, shundeFtpClient);
                }
                if (null != FtpHead && !"".equals(FtpHead)) {
                    FTPClientUtil.ftpToFtp(FtpHead, targetParentPath + '/' + FtpHead, newlxFtpClient, shundeFtpClient);
                }
                if (null != FtpAxle && !"".equals(FtpAxle)) {
                    FTPClientUtil.ftpToFtp(FtpAxle, targetParentPath + '/' + FtpAxle, newlxFtpClient, shundeFtpClient);
                }
                if (null != FtpTail && !"".equals(FtpTail)) {
                    FTPClientUtil.ftpToFtp(FtpTail, targetParentPath + '/' + FtpTail, newlxFtpClient, shundeFtpClient);
                }
                if (null != FtpPriorHead && !"".equals(FtpPriorHead)) {
                    FTPClientUtil.ftpToFtp(FtpPriorHead, targetParentPath + '/' + FtpPriorHead, newlxFtpClient, shundeFtpClient);
                }
                if (null != FtpFullView && !"".equals(FtpFullView)) {
                    FTPClientUtil.ftpToFtp(FtpFullView, targetParentPath + '/' + FtpFullView, newlxFtpClient, shundeFtpClient);
                }
                count.increment();
            }
        } catch (Exception e) {
            LOGGER.error("FILE TRANSFORM ERROR " + e.getMessage(), e);
        }
    }
}
