package com.hs.rs.schedule;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hs.rs.common.XStreamUtil;
import com.hs.rs.common.httphelper.HttpCustomClient;
import com.hs.rs.common.httphelper.HttpCustomException;
import com.hs.rs.model.Envelope;
import com.hs.rs.persistence.dao.*;
import com.hs.rs.persistence.entity.*;
import com.hs.rs.service.TransformService;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@Component
public class UploadTask {

    private SysparastrRepository sysparastrRepository;
    private BlacksmokevehicleInfoRepository blacksmokevehicleInfoRepository;
    private LineRepository lineRepository;
    private MonitorDataRepository monitorDataRepository;
    private StationRepository stationRepository;
    private TrafficFlowRepository trafficFlowRepository;
    private VehicleInfoRepository vehicleInfoRepository;
    private VehicleTrajectoryRepository vehicleTrajectoryRepository;
    private TransformService transformService;
    private ObjectMapper objectMapper;
    private final HttpCustomClient httpCustomClient;
    private ConcurrentHashMap<String, String> header = new ConcurrentHashMap<>();
    private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static  String URL = null;
    private ConcurrentHashMap<String, String> header1;
    private String X_Auth_Token = "";
    private static final Logger log = LoggerFactory.getLogger(UploadTask.class);
    @Autowired
    public UploadTask(SysparastrRepository sysparastrRepository,
                      BlacksmokevehicleInfoRepository blacksmokevehicleInfoRepository,
                      LineRepository lineRepository,
                      MonitorDataRepository monitorDataRepository,
                      StationRepository stationRepository,
                      TrafficFlowRepository trafficFlowRepository,
                      VehicleInfoRepository vehicleInfoRepository,
                      VehicleTrajectoryRepository vehicleTrajectoryRepository,
                      TransformService transformService,
                      ObjectMapper objectMapper,
                      HttpCustomClient httpCustomClient) {
        //配置表
        this.sysparastrRepository = sysparastrRepository;
        this.URL=this.NewUrl();

        this.blacksmokevehicleInfoRepository = blacksmokevehicleInfoRepository;
        this.lineRepository = lineRepository;
        this.monitorDataRepository = monitorDataRepository;
        this.stationRepository = stationRepository;
        this.trafficFlowRepository = trafficFlowRepository;
        this.vehicleInfoRepository = vehicleInfoRepository;
        this.vehicleTrajectoryRepository = vehicleTrajectoryRepository;
        this.transformService = transformService;
        this.objectMapper = objectMapper;
        this.httpCustomClient = httpCustomClient;
        header.put("Content-Type", CONTENT_TYPE);
        header.put("Accept-Charset", "utf-8");
    }

    private String NewUrl() {
        String urls="";
        try {
            Sysparastr sysparastr = sysparastrRepository.findFirstByTypeNameEn("config");
            urls=sysparastr.getParamValue();
        } catch (Exception e) {
            return ""+e+"";
        }
        return urls;
    }
    private String callApi(String strBody) {
        try {
            String result = httpCustomClient.doPost(URL, header, strBody);
            return result;
        } catch (Exception e) {
            throw HttpCustomException.instance(e.getMessage());
        }
    }
    public String wrapperXml(Object data, String datatype) {
        ArrayNode datas = objectMapper.createArrayNode();
        JsonNode node = objectMapper.convertValue(data, JsonNode.class);
        datas.add(node);
        try {
            String datasStr = objectMapper.writeValueAsString(datas);
            Envelope envelope = Envelope.BuildEnvelope(datatype, datasStr);
            return XStreamUtil.objectToXml(envelope);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 看看文档，遥感线信息 只需要提交一次
    public void uploadLine() {
        List<Line> lineList = lineRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        String requestBody = wrapperXml(transformService.transLineDto(lineList.get(0)), "monitoringline");
        String responseStr = callApi(requestBody);
        System.out.println("responseStr======"+responseStr);
    }

    // 看看文档，站点信息 只需要提交一次

    public void uploadStation() {
        List<Station> stationList = stationRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        String requestBody = wrapperXml(transformService.transStationDto(stationList.get(0)), "monitoringpoint");
        String responseStr = callApi(requestBody);
        System.out.println("responseStr======"+responseStr);
    }
    // 理解这个，其他按照这个写

    public void uploadBlacksomkevehicle() {
        List<BlacksmokevehicleInfo> blacksmokevehicleInfoList = blacksmokevehicleInfoRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
//         逐条数据提交
        log.info("uploadBlacksomkevehicle insert count:" + blacksmokevehicleInfoList.size());
        LongAdder successCount = new LongAdder();
        blacksmokevehicleInfoList.forEach(blacksmokevehicleInfo -> {
            try {
                String requestBody = wrapperXml(transformService.transBlacksmokevehicleDto(blacksmokevehicleInfo), "blacksmokevehicle");
                String responseStr = callApi(requestBody);
                System.out.println("responseStr======"+responseStr);
                JsonNode response = objectMapper.readTree(responseStr);
                // 根据文档判断 数据是否提交成功
                if(response.get("status").toString().replace("\"", "").equals("1")) {  //  看文档实现
                    blacksmokevehicleInfo.setUpLoadStatus(1);
                    successCount.increment();
                } else {
                    blacksmokevehicleInfo.setUpLoadStatus(2);
                }
                blacksmokevehicleInfoRepository.save(blacksmokevehicleInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("uploadBlacksomkevehicle success count:" + successCount);
    }
    public void uploadTrafficFlow() {
        List<TrafficFlow> trafficFlowList = trafficFlowRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        log.info("uploadTrafficFlow insert count:" + trafficFlowList.size());
        LongAdder successCount = new LongAdder();
        trafficFlowList.forEach(trafficFlow -> {
            try {
                String requestBody= wrapperXml(transformService.transTrafficFlowDto(trafficFlow), "trafficflow");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").toString().replace("\"", "").equals("1")) {
                    trafficFlow.setUpLoadStatus(1);
                    successCount.increment();
                } else {
                    trafficFlow.setUpLoadStatus(2);
                }
                trafficFlowRepository.save(trafficFlow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("uploadTrafficFlow success count:" + successCount);
    }
    @Scheduled(cron="*/10 * * * * *")
    public void uploadVehicleInfo() {
        List<VehicleInfo> vehicleInfoList = vehicleInfoRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        log.info("uploadVehicleInfo insert count:" + vehicleInfoList.size());
        LongAdder successCount = new LongAdder();
        vehicleInfoList.forEach(vehicleInfo -> {
            try {
                vehicleInfo.setHpzl(parsePlateType(vehicleInfo.getCpys(),vehicleInfo.getHphm()));
                String requestBody = wrapperXml(transformService.transVehicleInfoDto(vehicleInfo), "vehicle");
                String responseStr = callApi(requestBody);
                System.out.println("responseStr===="+responseStr);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").toString().replace("\"", "").equals("1")) {
                    vehicleInfo.setUpLoadStatus(1);
                    successCount.increment();
                } else {
                    vehicleInfo.setUpLoadStatus(2);
                }
                vehicleInfoRepository.save(vehicleInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("uploadVehicleInfo success count:" + successCount);
    }
    /**
     * 上传遥感监测数据
     */

    public void uploadMonitorData() {
        List<MonitorDataLog> monitorDataLogList = monitorDataRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        log.info("uploadMonitorData insert count:" + monitorDataLogList.size());
        LongAdder successCount = new LongAdder();
        monitorDataLogList.forEach(monitorDataLog -> {
            if(monitorDataLog.getRlzl()=="Z"){
                monitorDataLog.setRlzl("Y");
            }
            if(StringUtils.isBlank(monitorDataLog.getHpzl())){
                monitorDataLog.setRlzl(parsePlateType(monitorDataLog.getCpys(),monitorDataLog.getHphm()));
            }
            if (monitorDataLog.getClsd().equals("0")){
                return;
            }
            if (monitorDataLog.getCojg().equals("0")&& monitorDataLog.getNojg().equals("0")
                    && monitorDataLog.getHcjg().equals("0")){
                return;
            }
            try {
                String requestBody = wrapperXml(transformService.transMonitorDataDto(monitorDataLog), "monitoringdata");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").toString().replace("\"", "").equals("1")) {
                    monitorDataLog.setUpLoadStatus(1);
                    successCount.increment();
                } else {
                    monitorDataLog.setUpLoadStatus(2);
                }
                monitorDataRepository.save(monitorDataLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("uploadMonitorData success count:" + successCount);
    }

    /**
     * 上传车辆轨道信息
     */
    public void uploadVehicleTrajectory() {
        List<VehicleTrajectory> vehicleTrajectoryList = vehicleTrajectoryRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        log.info("uploadVehicleTrajectory insert count:" + vehicleTrajectoryList.size());
        LongAdder successCount = new LongAdder();
        vehicleTrajectoryList.forEach(vehicleTrajectory -> {
            try{
                String requestBody = wrapperXml(transformService.transVehicleTrajectoryDto(vehicleTrajectory), "vehicletrajectory");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").toString().replace("\"", "").equals("1")) {
                    System.out.println("responseStr===="+responseStr);
                    vehicleTrajectory.setUpLoadStatus(1);
                    successCount.increment();
                } else {
                    vehicleTrajectory.setUpLoadStatus(2);
                }
                vehicleTrajectoryRepository.save(vehicleTrajectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("uploadVehicleTrajectory success count:" + successCount);
    }

    public void  ALLDay(){
        uploadVehicleTrajectory();
        uploadTrafficFlow();
        uploadVehicleInfo();
        uploadMonitorData();
        uploadBlacksomkevehicle();
    }
    static String parsePlateType(String colorStr,String truckNumber){
        switch (colorStr) {
            case "0":
                return "02";
            case "1":
                    return "16";
            case "2":
                    return "23";
            case "3":
                if(truckNumber.contains("使")){
                    return "03";
                }else if(truckNumber.contains("领")){
                    return "04";
                }else if(truckNumber.contains("港")){
                    return  "05";
                }else if(truckNumber.contains("澳")){
                    return "05";
                }
            case "4":
                    return "52";
            default:
                return "99";
        }
    }
    @Scheduled(fixedDelay = 3600000, initialDelay = 4000)
    public void initToken() {
        Map<String, String> map = new HashMap<>();
        map.put("account", "aG9uZ3NoZW5nMDAx");
        map.put("pwdcode", "aG9uZ3NoZW5nMDAx");
        String data = JSON.toJSONString(map);
        String result = HttpCustomClient.doPost("http://10.194.164.75:8083/ygjc/login", data, 4000);
        System.out.println("result="+result);
        JsonNode response = null;
        try {
            response = objectMapper.readTree(result);
            System.out.println("status="+response.get("status"));
            JsonNode resdata=objectMapper.readTree(response.get("data").toString());
            System.out.println("X_Auth_Token="+resdata.get("X-Auth-Token"));
            if (response.get("status").toString().equals("\"1\"")) {
                X_Auth_Token=resdata.get("X-Auth-Token").toString().replace("\"", "");
                header.put("X-Auth-Token", X_Auth_Token);
                System.out.println("X_Auth_Token="+X_Auth_Token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
