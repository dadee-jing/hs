package com.ruoyi.duge.third.foshan.service;

import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.duge.domain.StationStatistics;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IStationStatisticsService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.foshan.socket.FoshanMessage;
import com.ruoyi.duge.third.foshan.socket.SendMsgClient;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.service.ThirdApiService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ruoyi.duge.third.foshan.socket.StructUtil.getCarData2Info;
import static com.ruoyi.duge.third.foshan.socket.StructUtil.getPic;

@Component
public class FoshanApiService implements ThirdApiService {

    private final SendMsgClient sendMsgClient;
    private final IWeightDataMapperService weightDataMapperService;
    private final IStationStatisticsService stationStatisticsService;
    private final IConfigDataService configDataService;
    private int serialNo = 100;

    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient,
                            IWeightDataMapperService weightDataMapperService,
                            IStationStatisticsService stationStatisticsService,
                            IConfigDataService configDataService) {
        this.sendMsgClient = sendMsgClient;
        this.weightDataMapperService = weightDataMapperService;
        this.stationStatisticsService = stationStatisticsService;
        this.configDataService = configDataService;
    }

    public void submitVehicleData(Long id) {
        WeightData weightData = weightDataMapperService.selectDataById(id);
        submitVehicleData(BaseVehicleDataRequest.builder()
                .weightData(weightData).build());
    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        try {
            WeightData weightData = request.getWeightData();
            FoshanMessage foshanMessage = FoshanMessage.builder()
                    .messageType(FoshanMessage.BODY_MSG)
                    .carData2Info(getCarData2Info(Integer.parseInt(configDataService.getConfigValue("site_id")),
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
//                            7.3F,
//                            52.58033F,
//                            6.403F,
//                            2.336F,
//                            3.109F,
                            0.00f, 0.00f,
                            weightData.getPlate(),
                            0,
                            mappingPlateColor(weightData.getTruckCorlor()),
                            5,
                            0, 0, 0, 0, 2))
                    .pic1(getPic(weightData.getWeightingDate(), new File("/pic/3.jpg")))
                    .pic2(getPic(weightData.getWeightingDate(), new File("/pic/6.jpg")))
                    .build();
            sendMsgClient.sendMessage(foshanMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseThirdApiResponse.builder()
                    .businessStatus(BusinessStatus.FAIL)
                    .errorCode("SYSTEM ERROR")
                    .errorMsg(e.getMessage())
                    .build();
        }
        return BaseThirdApiResponse.builder()
                .businessStatus(BusinessStatus.SUCCESS)
                .build();
    }

    public void submitByte(String sendva) {
        FoshanMessage foshanMessage = FoshanMessage.builder().messageBody(sendva).build();
        sendMsgClient.sendMessage(foshanMessage);
        /*
        String ip = "19.128.109.103";
        int port = Integer.parseInt("10100");
        //String sendva = "7e50020000000000000000000000000000007e";
        byte[] sendByte = hexStringToByte(sendva);
        String testv = bytesToHexString(sendByte);
        System.out.println(testv);
        System.out.println(ip + ":" + port);

        SockeUtil socketClient = null;
        ;
        try {
            socketClient = new SockeUtil(ip, port);
        } catch (UnknownHostException e) {
//            log.error("socket链接异常,链接信息："+ip+端口);
            e.printStackTrace();
        } catch (IOException e) {
//            log.error("socket IO异常");
            e.printStackTrace();
        }
        byte[] ss = null;

        try {
            ss = socketClient.sentByte(sendByte);
            String retu = bytesToHexString(ss);
            System.out.println("response:" + retu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
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
