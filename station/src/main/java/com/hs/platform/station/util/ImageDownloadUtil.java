package com.hs.platform.station.util;

import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.entity.ConfigData;
import com.hs.platform.station.persistence.local.entity.WeightData;
import com.hs.platform.station.third.foshan.service.FoshanApiService;
import com.hs.platform.station.third.foshan.socket.FoshanMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static com.hs.platform.station.Constants.*;
import static com.hs.platform.station.third.foshan.service.FoshanApiService.mappingPlateColor;
import static com.hs.platform.station.third.foshan.socket.StructUtil.getCarData2Info;

@Component
public class ImageDownloadUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageDownloadUtil.class);

    public static FTPClient newlxFtpClient = null;
    public static  FTPClient shundeFtpClient = null;
    @Autowired
    private ConfigDataRepository configDataRepository;

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
                FoshanMessage foshanMessage = new FoshanMessage();
                //前，后，侧，车牌，视频，市局无需视频
                List<String> pathList = Arrays.asList(FtpPriorHead, FtpTail, FtpHead, FtpAxle, FtpPlate, FtpFullView);
                //上传图片，组装对象
                uploadFile(foshanMessage,pathList,targetParentPath,entity);
                //加入队列
                FoshanApiService.addEntity(foshanMessage);
            }
        } catch (Exception e) {
            LOGGER.error("FILE TRANSFORM ERROR " + e.getMessage());
        }
    }

    private void uploadFile(FoshanMessage foshanMessage,List<String> pathList, String targetParentPath, WeightData weightData) {
        int picCount = 0;
        for(int i = 0; i < pathList.size(); i++){
            String filePath = pathList.get(i);
            if (StringUtils.isNotBlank(filePath)) {
                byte[] pic = FTPClientUtil.ftpToFtp(filePath, targetParentPath + '/' + filePath,
                        newlxFtpClient, shundeFtpClient,weightData.getWeightingDate());
                if(pic != null){
                    if(i == 0){
                        foshanMessage.setPic1(pic);
                        picCount++;
                    }
                    else if(i == 1){
                        foshanMessage.setPic2(pic);
                        picCount++;
                    }
                    else if(i == 2){
                        foshanMessage.setPic3(pic);
                        picCount++;
                    }
                    else if(i == 3){
                        foshanMessage.setPic4(pic);
                        picCount++;
                    }
                    else if(i == 4){
                        foshanMessage.setPic5(pic);
                        picCount++;
                    }
                }
            }
        }
        //组装对象
        combineFoshanMessage(foshanMessage,weightData,picCount);
    }

    private void combineFoshanMessage(FoshanMessage foshanMessage,WeightData weightData,int picCount) {
        foshanMessage.setMessageType(FoshanMessage.BODY_MSG);
        foshanMessage.setCarData2Info
                //改：改成配置文件或者只查询一次
                (getCarData2Info(Integer.parseInt(configDataRepository.findFirstByKey("site_id").getValue()),
                        weightData.getLane(),
                        weightData.getWeightingDate(),
                        weightData.getAxleCount(),
                        null,
                        weightData.getWeight().floatValue(),
                        weightData.getSpeed().floatValue(),
                        Float.parseFloat(weightData.getLength()) / 1000.0f,
                        Float.parseFloat(weightData.getWidth()) / 1000.0f,
                        Float.parseFloat(weightData.getHeight()) / 1000.0f,
                        0.00f, 0.00f,
                        weightData.getPlate(),
                        0,
                        mappingPlateColor(weightData.getTruckCorlor()),
                        5,
                        0, 0, 0, 0, picCount));
    }

    /**
     * 检查两个ftp连接
     */
    public static FTPClient checkNewlxFtpConnect(){
        if (!FTPReply.isPositiveCompletion(newlxFtpClient.getReplyCode())) {
            newlxFtpClient = ImageDownloadUtil.resetFTPClient(newlxFtpClient,true);
        }
        return newlxFtpClient;
    }

    public static FTPClient checkShundeFtpConnect(){
        if (!FTPReply.isPositiveCompletion(shundeFtpClient.getReplyCode())) {
            shundeFtpClient = ImageDownloadUtil.resetFTPClient(shundeFtpClient,false);
        }
        return shundeFtpClient;
    }

}
