package com.hs.platform.station.third.foshan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.apache.commons.io.FileUtils;
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
public class FoshanApiService {
    private static final Logger log = LoggerFactory.getLogger(FoshanApiService.class);
    private final SendMsgClient sendMsgClient;
    private final ConfigDataRepository configDataRepository;
    private static ConcurrentLinkedQueue<FoshanMessage> shiJuQueue = new ConcurrentLinkedQueue<>();
    public static Boolean uploadShiJu = false;
    public static volatile int totalCount = 0;
    public static volatile int sendCount = 0;
    public static int sendSuccessCount = 0;
    public static int receiveCount = 0;

    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient, ConfigDataRepository configDataRepository) {
        this.sendMsgClient = sendMsgClient;
        this.configDataRepository = configDataRepository;
    }

    public static void addEntity(FoshanMessage entity) {
        shiJuQueue.add(entity);
        totalCount++;
        if(shiJuQueue.size() > 300){
            shiJuQueue.poll();
        }
        //log.info("FoshanMessage add size:" + shiJuQueue.size());
    }

    @Scheduled(fixedRate = 5000)
    public void submitShiJuData() {
        if ("1".equals(getDbConfigValue("do_foshan_scheduled"))) {
            uploadShiJu = true;
            if (shiJuQueue != null && shiJuQueue.size() != 0) {
                log.info("uploadShiJu start size:" + shiJuQueue.size());
                for (FoshanMessage foshanMessage : shiJuQueue) {
                    foshanMessage = shiJuQueue.poll();
                    if (foshanMessage != null) {
                        try {
                            sendMsgClient.sendMessage(foshanMessage);
                            sendCount++;
                        } catch (Exception e) {
                            log.info("uploadShiJu fail",e);
                        }
                    }
                }
            }
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

}
