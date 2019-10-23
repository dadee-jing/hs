package com.hs.platform.station.upload_foshan;
import com.hs.platform.station.upload_foshan.domaim.WeightData;
import com.hs.platform.station.upload_foshan.model.BaseEquipmentStatusRequest;
import com.hs.platform.station.upload_foshan.model.BaseThirdApiResponse;
import com.hs.platform.station.upload_foshan.model.BaseVehicleDataRequest;
import com.hs.platform.station.upload_foshan.service.IConfigDataService;
import com.hs.platform.station.upload_foshan.service.IWeightDataMapperService;
import com.hs.platform.station.upload_foshan.service.ThirdApiService;
import com.hs.platform.station.upload_foshan.socket.FoshanMessage;
import com.hs.platform.station.upload_foshan.socket.SendMsgClient;
import com.hs.platform.station.util.FTPClientUtil;
import com.ruoyi.common.enums.BusinessStatus;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.hs.platform.station.upload_foshan.socket.StructUtil.getCarData2Info;
import static com.hs.platform.station.upload_foshan.socket.StructUtil.getPic;


@Component
public class FoshanApiService implements ThirdApiService {
    private static final Logger log = LoggerFactory.getLogger(FoshanApiService.class);
    static SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
    private final SendMsgClient sendMsgClient;
    private final IWeightDataMapperService weightDataMapperService;
    private final IConfigDataService configDataService;


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

    @Scheduled(cron="*/3 * * * * ?")
//  @Scheduled(cron = "${foshan.scheduled}")
    public void submitVehicleData() {
//        if ("1".equals(configDataService.getConfigValue("do_foshan_scheduled"))) {
            System.out.println("佛山市局定时任务执行");
            List<WeightData> list = weightDataMapperService.selectNotUploadSj();
            for (WeightData weightData : list) {
                BaseThirdApiResponse baseThirdApiResponse = submitVehicleData(BaseVehicleDataRequest.builder()
                        .weightData(weightData).build());
                if (baseThirdApiResponse.getBusinessStatus() == BusinessStatus.SUCCESS) {
                    weightData.setUploadSj(1);
                    System.out.println(weightData.getStationName()+"上送成功！！！");
                    weightDataMapperService.updateData(weightData);
                }
            }
        }
//    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        BaseThirdApiResponse baseThirdApiResponse = new BaseThirdApiResponse();
        try {
            WeightData weightData = request.getWeightData();
            int picCount = 0;
            FoshanMessage foshanMessage = new FoshanMessage();

            if (StringUtils.isNoneBlank(weightData.getFtpPriorHead())) {
                InputStream inputStream = FTPClientUtil.getInputStream(weightData.getFtpPriorHead());
                if (inputStream != null) {
                    foshanMessage.setPic1(getPic(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpTail())) {
                InputStream inputStream= FTPClientUtil.getInputStream(weightData.getFtpTail());
                if(inputStream!=null){
                    foshanMessage.setPic2(getPic(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpPlate())) {
                InputStream inputStream= FTPClientUtil.getInputStream(weightData.getFtpPlate());
                if(inputStream!=null){
                    foshanMessage.setPic3(getPic(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpHead())) {
                InputStream inputStream= FTPClientUtil.getInputStream(weightData.getFtpHead());
                if(inputStream!=null){
                    foshanMessage.setPic4(getPic(weightData.getWeightingDate(), inputStream));
                    picCount++;
                }
            }
            if (StringUtils.isNoneBlank(weightData.getFtpAxle())) {
                InputStream inputStream= FTPClientUtil.getInputStream(weightData.getFtpHead());
                if(inputStream!=null){
                    foshanMessage.setPic5(getPic(weightData.getWeightingDate(),inputStream));
                    picCount++;
                }
            }
            foshanMessage.setMessageType(FoshanMessage.BODY_MSG);
            foshanMessage.setCarData2Info(getCarData2Info(weightData.getSiteId(),
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
                System.out.println("执行上送");
                sendMsgClient.sendMessage(foshanMessage);
                baseThirdApiResponse.setBusinessStatus(BusinessStatus.SUCCESS);
                return baseThirdApiResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseThirdApiResponse.setBusinessStatus(BusinessStatus.FAIL);
            return baseThirdApiResponse;
        }
        System.out.println("上送失败！！！");
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
