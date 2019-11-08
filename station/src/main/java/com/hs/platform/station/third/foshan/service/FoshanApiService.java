package com.hs.platform.station.third.foshan.service;

import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.dao.WeightDataRepository;
import com.hs.platform.station.persistence.local.entity.ConfigData;
import com.hs.platform.station.persistence.local.entity.WeightData;
import com.hs.platform.station.third.common.enums.BusinessStatus;
import com.hs.platform.station.third.foshan.socket.FoshanMessage;
import com.hs.platform.station.third.foshan.socket.SendMsgClient;
import com.hs.platform.station.third.model.BaseEquipmentStatusRequest;
import com.hs.platform.station.third.model.BaseThirdApiResponse;
import com.hs.platform.station.third.model.BaseVehicleDataRequest;
import com.hs.platform.station.third.service.ThirdApiService;

import com.hs.platform.station.util.ImageDownloadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.hs.platform.station.third.foshan.socket.StructUtil.*;

@Component
public class FoshanApiService
        //implements ThirdApiService
{
    private static final Logger log = LoggerFactory.getLogger(FoshanApiService.class);
    private final SendMsgClient sendMsgClient;
    private final ConfigDataRepository configDataRepository;
    private final WeightDataRepository weightDataRepository;

    private static ConcurrentLinkedQueue<FoshanMessage> shiJuQueue = new ConcurrentLinkedQueue<>();
    public static Boolean uploadShiJu = false;

    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient, ConfigDataRepository configDataRepository,
                            WeightDataRepository weightDataRepository) {
        this.sendMsgClient = sendMsgClient;
        this.configDataRepository = configDataRepository;
        this.weightDataRepository = weightDataRepository;
    }

    public static void addEntity(FoshanMessage entity) {
        shiJuQueue.add(entity);
        log.info("FoshanMessage add size:" + shiJuQueue.size());
        //log.info("FoshanEntity:" + entity);
    }

    @Scheduled(fixedRate = 5000)
    public void submitShiJuData() {
        if ("1".equals(getDbConfigValue("do_foshan_scheduled"))) {
            log.info("市局定时任务执行开始");
            uploadShiJu = true;
            if (shiJuQueue != null && shiJuQueue.size() != 0) {
                log.info("市局定时任务size:" + shiJuQueue.size());
                for (FoshanMessage foshanMessage : shiJuQueue) {
                    foshanMessage = shiJuQueue.poll();
                    if (foshanMessage != null) {
                        try {
                            //改：上送成功判断可能要改
                            sendMsgClient.sendMessage(foshanMessage);
                            log.info("市局上送成功 ");
                        } catch (Exception e) {
                            log.info("市局上送失败 ");
                        }
                    }
                }
            }
            log.info("市局定时任务执行完成");
        }
        else{
            //log.info("foshan clear "+ shiJuQueue.size());
            uploadShiJu = false;
            shiJuQueue.clear();
        }
    }

    private String getDbConfigValue(String key) {
        try {
            ConfigData configData = configDataRepository.findFirstByKey(key);
            if (null != configData) {
                return configData.getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

/*    public void submitVehicleData(Long id) {
        WeightData weightData = weightDataRepository.findWeightDataById(id);
        submitVehicleData(BaseVehicleDataRequest.builder()
                .weightData(weightData).build());
    }

    //@Scheduled(cron = "${foshan.scheduled}")
    public void submitVehicleData() {
        if ("1".equals(configDataRepository.findFirstByKey("do_foshan_scheduled").getValue())) {
            log.info("佛山市局定时任务执行开始");
            Date readyDate = new Date(System.currentTimeMillis() - 1000 * NumberUtils.toInt(getDbConfigValue("data_upload_delay"), 30));
            List<WeightData> list = weightDataRepository.findTop5ByUploadSjIsNotAndWeightingDateBeforeOrderByUploadSjAscIdAsc(1,readyDate);
            log.info("shiju size:" + list.size());
            for (WeightData weightData : list) {
                BaseThirdApiResponse baseThirdApiResponse = submitVehicleData(BaseVehicleDataRequest.builder()
                        .weightData(weightData).build());
                if (baseThirdApiResponse.getBusinessStatus() == BusinessStatus.SUCCESS) {
                    weightData.setUploadSj(1);
                    log.info("上送成功！！！");
                    weightDataRepository.save(weightData);
                }
            }
            log.info("佛山市局定时任务执行完成");
        }
    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        BaseThirdApiResponse baseThirdApiResponse = new BaseThirdApiResponse();
        try {
            WeightData weightData = request.getWeightData();
            int picCount = 0;
            FoshanMessage foshanMessage = new FoshanMessage();
            FTPClient sourceClient = ImageDownloadUtil.newlxFtpClient;
            sourceClient.changeWorkingDirectory("/");
            if (StringUtils.isNoneBlank(weightData.getFtpPriorHead())) {
                //从ftp读取流文件
                InputStream inputStream = sourceClient.retrieveFileStream(weightData.getFtpPriorHead());
                if (inputStream != null) {
                    foshanMessage.setPic1(getPicByStream(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpTail())) {
                InputStream inputStream = sourceClient.retrieveFileStream(weightData.getFtpTail());
                if (inputStream != null) {
                    foshanMessage.setPic2(getPicByStream(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpPlate())) {
                InputStream inputStream = sourceClient.retrieveFileStream(weightData.getFtpPlate());
                if (inputStream != null) {
                    foshanMessage.setPic3(getPicByStream(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpHead())) {
                InputStream inputStream = sourceClient.retrieveFileStream(weightData.getFtpHead());
                if (inputStream != null) {
                    foshanMessage.setPic4(getPicByStream(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpAxle())) {
                InputStream inputStream = sourceClient.retrieveFileStream(weightData.getFtpAxle());
                if (inputStream != null) {
                    foshanMessage.setPic5(getPicByStream(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            foshanMessage.setMessageType(FoshanMessage.BODY_MSG);
            foshanMessage.setCarData2Info
                    (getCarData2Info(Integer.parseInt(configDataRepository.findFirstByKey("site_id").getValue()),
                    weightData.getLane(),
                    //new Date(1559017800000l),
                    //2,
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
            if (picCount > 0) {
                log.info("执行上送");
                sendMsgClient.sendMessage(foshanMessage);
                baseThirdApiResponse.setBusinessStatus(BusinessStatus.SUCCESS);
                return baseThirdApiResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseThirdApiResponse.setBusinessStatus(BusinessStatus.FAIL);
            return baseThirdApiResponse;
        }
        log.info("上送失败！！！");
        baseThirdApiResponse.setBusinessStatus(BusinessStatus.FAIL);
        return baseThirdApiResponse;
    }

    public void submitByte(String sendva) {
        FoshanMessage foshanMessage = FoshanMessage.builder().messageBody(sendva).build();
        sendMsgClient.sendMessage(foshanMessage);
    }*/

    public static int mappingPlateColor(String colorChs) {
        switch (colorChs) {
            case "蓝":
                return 0;
            case "黄":
                return 1;
            case "黑":
                return 3;
            case "白":
                return 2;
            case "绿":
                return 4;
            default:
                return 9;
        }
    }

/*    private String getDbConfigValue(String key) {
        try {
            ConfigData configData = configDataRepository.findFirstByKey(key);
            if (null != configData) {
                return configData.getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }*/
/*    @Override
    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request) {
        return null;
    }*/
}
