package com.hs.platform.station.util;

import com.hs.platform.station.persistence.local.entity.WeightData;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static com.hs.platform.station.Constants.*;

@Component
public class ImageDownloadUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageDownloadUtil.class);

    public static FTPClient newlxFtpClient = null;
    public static  FTPClient shundeFtpClient = null;

    @PostConstruct
    void init() {
        newlxFtpClient = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        shundeFtpClient = FTPClientUtil.getFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
    }

    private FTPClient checkFTPClient(FTPClient ftpClient, boolean newlx) {
        if (null != ftpClient && ftpClient.isConnected() && ftpClient.isAvailable()) {
            return ftpClient;
        } else {
            return resetFTPClient(ftpClient, newlx);
        }
    }

    public static FTPClient resetFTPClient(FTPClient ftpClient, boolean newlx) {
        LOGGER.info("to reset " + newlx);
        FTPClientUtil.ftpClose(ftpClient);
        FTPClient newOne;
        if (newlx) {
            newOne = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        } else {
            newOne = FTPClientUtil.getFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
        }
        LOGGER.info("over reset " + newlx);
        return newOne;
    }


    public void submitDownloadTask(final WeightData entity) {
        try {

            final String stationId = station_id + "";
            // 按照固定目录存放 ftppath/stationID/date/   ftp/1/20190717/
            String targetParentPath = "/" + stationId + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");

            if (null != newlxFtpClient && null != shundeFtpClient) {
                String FtpHead = entity.getFtpHead();
                String FtpAxle = entity.getFtpAxle();
                String FtpTail = entity.getFtpTail();
                String FtpPriorHead = entity.getFtpPriorHead();
                String FtpPlate = entity.getFtpPlate();
                String FtpFullView = entity.getFtpFullView();

                List<String> pathList = Arrays.asList(FtpHead, FtpAxle, FtpTail, FtpPriorHead, FtpPlate, FtpFullView);
                pathList.forEach(filePath -> {
                    if (StringUtils.isNotBlank(filePath)) {
                        FTPClientUtil.ftpToFtp(filePath, targetParentPath + '/' + filePath, newlxFtpClient, shundeFtpClient);
                    }
                });
            }
        } catch (Exception e) {
            LOGGER.error("FILE TRANSFORM ERROR " + e.getMessage());
        }
    }

    /**
     * 检查两个ftp连接
     */
    public static void checkFtpConnect(){
        if (!FTPReply.isPositiveCompletion(newlxFtpClient.getReplyCode())) {
            newlxFtpClient = ImageDownloadUtil.resetFTPClient(newlxFtpClient,true);
        }
        if (!FTPReply.isPositiveCompletion(shundeFtpClient.getReplyCode())) {
            shundeFtpClient = ImageDownloadUtil.resetFTPClient(shundeFtpClient,false);
        }
    }
}
