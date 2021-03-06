package com.ruoyi.duge.third.foshan.service;

import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.foshan.socket.FTPClientUtil;
import com.ruoyi.duge.third.foshan.socket.FoshanMessage;
import com.ruoyi.duge.third.foshan.socket.SendMsgClient;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.service.ThirdApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

import static com.ruoyi.duge.third.foshan.socket.StructUtil.getCarData2Info;
import static com.ruoyi.duge.third.foshan.socket.StructUtil.getPic;

@Component
public class FoshanApiService implements ThirdApiService {
    private static final Logger log = LoggerFactory.getLogger(FoshanApiService.class);
    static SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
    private final SendMsgClient sendMsgClient;
    private final IWeightDataMapperService weightDataMapperService;
    private final IConfigDataService configDataService;
    public static LongAdder totalCount = new LongAdder();//总记录数
    public static LongAdder sendCount = new LongAdder();//执行发送的次数
    public static LongAdder sendSuccessCount = new LongAdder();//发送成功的次数
    public static LongAdder receiveCount = new LongAdder();//收到5020次数
    public static ConcurrentHashMap<String, String> waitingResponseMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> okPlateMap = new ConcurrentHashMap<>();

    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient,
                            IWeightDataMapperService weightDataMapperService,
                            IConfigDataService configDataService) {
        this.sendMsgClient = sendMsgClient;
        this.weightDataMapperService = weightDataMapperService;
        this.configDataService = configDataService;
    }

    public void submitVehicleData(Long id) {
        WeightData weightData = weightDataMapperService.selectDataById(id);
        submitVehicleData(BaseVehicleDataRequest.builder()
                .weightData(weightData).build());
    }

    //@Scheduled(cron="*/3 * * * * ?")
    @Scheduled(cron = "${foshan.scheduled}")
    public void submitVehicleData() {
        if ("1".equals(configDataService.getConfigValue("do_foshan_scheduled"))) {
            List<WeightData> list = weightDataMapperService.selectNotUploadSj();
            System.out.println("佛山市局定时任务执行 size:" + list.size());
            for (WeightData weightData : list) {
                totalCount.increment();
                BaseThirdApiResponse baseThirdApiResponse = submitVehicleData(BaseVehicleDataRequest.builder()
                        .weightData(weightData).build());
                if (baseThirdApiResponse.getBusinessStatus() == BusinessStatus.SUCCESS) {
                    weightData.setUploadSj(1);
                    System.out.println(weightData.getStationName()+"上送成功！！！");
                    weightDataMapperService.updateData(weightData);
                }
                log.info("sendCount:" + sendCount.longValue() + ",sendSuccessCount:" + sendSuccessCount.longValue() +
                        ",receiveCount:" + receiveCount.longValue() + ",totalCount:" + totalCount.longValue() + ",lsize:" +list.size());
            }
        }
    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        BaseThirdApiResponse baseThirdApiResponse = new BaseThirdApiResponse();
        try {
            WeightData weightData = request.getWeightData();
            int picCount = 0;
            FoshanMessage foshanMessage = new FoshanMessage();
            String baseDir = "/sharedata/ftp/" + weightData.getStationId() + "/" + today.format(weightData.getCreateTime()) + "/";
            if (StringUtils.isNoneBlank(weightData.getFtpPriorHead())) {
                File file = new File(baseDir + weightData.getFtpPriorHead());
                if (file.exists() && file.length() > 0) {
                    foshanMessage.setPic1(getPic(weightData.getWeightingDate(), file));
                    picCount++;
                }
            }
        /*if (StringUtils.isNoneBlank(weightData.getFtpTail())) {
                File file = new File(baseDir + weightData.getFtpTail());
                if (file.exists() && file.length() > 0) {
                    foshanMessage.setPic2(getPic(weightData.getWeightingDate(), file));
                    picCount++;
                }
            }*/
            if (StringUtils.isNoneBlank(weightData.getFtpPlate())) {
                File file = new File(baseDir + weightData.getFtpPlate());
                if (file.exists() && file.length() > 0) {
                    foshanMessage.setPic3(getPic(weightData.getWeightingDate(), file));
                    picCount++;
                }
            }
/*            if (StringUtils.isNoneBlank(weightData.getFtpHead())) {
                File file = new File(baseDir + weightData.getFtpHead());
                if (file.exists() && file.length() > 0) {
                    foshanMessage.setPic4(getPic(weightData.getWeightingDate(), file));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpAxle())) {
                File file = new File(baseDir + weightData.getFtpAxle());
                if (file.exists() && file.length() > 0) {
                    foshanMessage.setPic5(getPic(weightData.getWeightingDate(), file));
                    picCount++;
                }
            }*/
            foshanMessage.setMessageType(FoshanMessage.BODY_MSG);
            foshanMessage.setCarData2Info(getCarData2Info(Integer.parseInt(configDataService.getConfigValue("site_id")),
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
    }
    static int mappingPlateColor(String colorChs) {
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
    @Override
    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request) {
        return null;
    }
}
