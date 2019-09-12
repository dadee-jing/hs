package com.hs.platform.station.util;

import com.hs.platform.station.persistence.local.entity.WeightData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hs.platform.station.Constants.*;

public class ImageDownloadUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageDownloadUtil.class);

    public static final ExecutorService service = Executors.newFixedThreadPool(5);


    public static void submitDownloadTask(final WeightData entity) {
        try {
            final String newlxFTPServerHost = newlx_ftp_server_host;
            final int newlxFTPServerPort = newlx_ftp_server_port;
            final String newlxFTPServerUser = newlx_ftp_user;
            final String newlxFTPServerPasswd = newlx_ftp_passwd;
            final String shundeFTPServerHost = shunde_ftp_server_host;
            final int shundeFTPServerPort = shunde_ftp_server_port;
            final String shundeFTPServerUser = shunde_ftp_user;
            final String shundeFTPServerPasswd = shunde_ftp_passwd;
            final String stationId = station_id + "";
            // 按照固定目录存放 ftppath/stationID/date/   ftp/1/20190717/
            String targetParentPath = "/" + stationId + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");

            service.submit(() -> {
                FTPClient newlxFtpClient = null;
                FTPClient shundeFtpClient = null;
                try {
                    newlxFtpClient = FTPClientUtil.getFTPClient(newlxFTPServerHost, newlxFTPServerPasswd, newlxFTPServerUser, newlxFTPServerPort);
                    shundeFtpClient = FTPClientUtil.getFTPClient(shundeFTPServerHost, shundeFTPServerPasswd, shundeFTPServerUser, shundeFTPServerPort);
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
                        newlxFtpClient.logout();
                        shundeFtpClient.logout();
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                } finally {
                    try {
                        if (null != newlxFtpClient) {
                            newlxFtpClient.disconnect();
                        }
                        if (null != shundeFtpClient) {
                            shundeFtpClient.disconnect();
                        }
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }

            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
