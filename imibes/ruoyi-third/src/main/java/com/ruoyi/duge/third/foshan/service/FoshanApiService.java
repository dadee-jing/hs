package com.ruoyi.duge.third.foshan.service;

import com.ruoyi.common.enums.BusinessStatus;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;


import static com.ruoyi.duge.third.foshan.socket.StructUtil.getCarData2Info;
import static com.ruoyi.duge.third.foshan.socket.StructUtil.getPic;

@Component
public class FoshanApiService implements ThirdApiService {
    private static final Logger log = LoggerFactory.getLogger(FoshanApiService.class);
    static SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );
    private final SendMsgClient sendMsgClient;
    private final IWeightDataMapperService weightDataMapperService;
    private final IStationStatisticsService stationStatisticsService;
    private final IConfigDataService configDataService;
    private int serialNo = 100;
    @Value("${foshan.baseDir}")
    private String baseDir;
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
//    @Scheduled(cron="*/15 * * * * ?")
    @Scheduled(cron="${foshan.scheduled}")
    public void submitVehicleData() {
        if ("1".equals(configDataService.getConfigValue("do_foshan_scheduled"))) {
        log.info("佛山市局定时任务执行");
        List<WeightData> list= weightDataMapperService.selectNotUploadSj();
        for (WeightData weightData:list) {
            BaseThirdApiResponse baseThirdApiResponse= submitVehicleData(BaseVehicleDataRequest.builder()
                    .weightData(weightData).build());
            if (baseThirdApiResponse.getBusinessStatus()==BusinessStatus.SUCCESS){
                weightData.setUploadSj(1);
                log.info("上送成功！！！");
                weightDataMapperService.updateData(weightData);}
        }
        }
    }
    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        BaseThirdApiResponse baseThirdApiResponse=new BaseThirdApiResponse();
        try {
            WeightData weightData = request.getWeightData();
            int picCount=0;
            FoshanMessage foshanMessage = new FoshanMessage();
            String baseDir="/sharedata/ftp/"+weightData.getStationId()+"/"+today.format(weightData.getCreateTime())+"/";
            if(StringUtils.isNoneBlank(weightData.getFtpPriorHead()) ){
                File file=new File(baseDir+weightData.getFtpPriorHead());
                if (file.exists()&& file.length()>0){
                foshanMessage.setPic1(getPic(weightData.getWeightingDate(),file));
                picCount++;}
            }
            if(StringUtils.isNoneBlank(weightData.getFtpTail())){
                File file=new File(baseDir+weightData.getFtpTail());
                if (file.exists()&& file.length()>0){
                foshanMessage.setPic2(getPic(weightData.getWeightingDate(),file ));
                picCount++;}
            }
            if(StringUtils.isNoneBlank(weightData.getFtpPlate())){
                File file=new  File(baseDir+weightData.getFtpPlate());
                if (file.exists()&& file.length()>0){
                foshanMessage.setPic3(getPic(weightData.getWeightingDate(), file));
                picCount++;}
            }
            if(StringUtils.isNoneBlank(weightData.getFtpHead())){
                File file=new File(baseDir+weightData.getFtpHead());
                if (file.exists()&& file.length()>0){
                foshanMessage.setPic4(getPic(weightData.getWeightingDate(),file ));
                picCount++;}
            }
            if(StringUtils.isNoneBlank(weightData.getFtpAxle())){
                File file=new File(baseDir+weightData.getFtpAxle());
                if (file.exists()&& file.length()>0){
                foshanMessage.setPic5(getPic(weightData.getWeightingDate(),file ));
                picCount++;}
            }
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
                            0, 0, 0, 0, picCount));
            if(picCount>0){
                    log.info("执行上送");
                    sendMsgClient.sendMessage(foshanMessage);
            baseThirdApiResponse.setBusinessStatus(BusinessStatus.SUCCESS);
            return baseThirdApiResponse;}
        } catch (Exception e) {
            e.printStackTrace();
            baseThirdApiResponse.setBusinessStatus(BusinessStatus.FAIL);
            return baseThirdApiResponse;
        }
        log.info("上送失败！！！");
        baseThirdApiResponse.setBusinessStatus(BusinessStatus.FAIL);
        return  baseThirdApiResponse;
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
