package com.hs.rs.schedule;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadTask.class);
    private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static  String URL = null;
    private ConcurrentHashMap<String, String> header1;
    private static  String X_Auth_Token = "b8a066fc-cf8c-4f6a-866c-ece30a12d856";

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
        header.put("X-Auth-Token", X_Auth_Token);
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
            LOGGER.info("post " + URL);
            LOGGER.info("header " + objectMapper.writeValueAsString(header));
            LOGGER.info("body " + strBody);
            String result = httpCustomClient.doPost(URL, header, strBody);
            LOGGER.info("response " + result);
            return result;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw HttpCustomException.instance(e.getMessage());
        }
    }

    public String wrapperXml(Object data, String datatype) {
        ArrayNode datas = objectMapper.createArrayNode();
        JsonNode node = objectMapper.convertValue(data, JsonNode.class);
        datas.add(node);
        try {
            String datasStr = objectMapper.writeValueAsString(datas);
            LOGGER.info("datas:" + datasStr);
            Envelope envelope = Envelope.BuildEnvelope(datatype, datasStr);
            return XStreamUtil.objectToXml(envelope);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



    // 看看文档，遥感线信息 只需要提交一次
    //
    @Scheduled(fixedDelay = 1000000, initialDelay = 5000)
    public void uploadLine() {
        List<Line> lineList = lineRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        String requestBody = wrapperXml(transformService.transLineDto(lineList.get(0)), "monitoringline");
        String responseStr = callApi(requestBody);
    }

    // 看看文档，站点信息 只需要提交一次
    //  @Scheduled(fixedDelay = 1000000, initialDelay = 5000)

    public void uploadStation() {
        List<Station> stationList = stationRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        String requestBody = wrapperXml(transformService.transStationDto(stationList.get(0)), "monitoringpoint");
        callApi(requestBody);

    }
    // 理解这个，其他按照这个写

    public void uploadBlacksomkevehicle() {
        List<BlacksmokevehicleInfo> blacksmokevehicleInfoList = blacksmokevehicleInfoRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
//         逐条数据提交
        blacksmokevehicleInfoList.forEach(blacksmokevehicleInfo -> {
            try {
                String requestBody = wrapperXml(transformService.transBlacksmokevehicleDto(blacksmokevehicleInfo), "blacksmokevehicle");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                // 根据文档判断 数据是否提交成功
                if(response.get("status").equals(1)) {  //  看文档实现
                    blacksmokevehicleInfo.setUpLoadStatus(1);
                } else {
                    blacksmokevehicleInfo.setUpLoadStatus(2);
                }
                blacksmokevehicleInfoRepository.save(blacksmokevehicleInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 上传遥感监测数据
     */

    public void uploadMonitorData() {
        List<MonitorDataLog> monitorDataLogList = monitorDataRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        monitorDataLogList.forEach(monitorDataLog -> {
            try {
                String requestBody = wrapperXml(transformService.transMonitorDataDto(monitorDataLog), "monitoringdata");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                System.out.println(response.get("status"));
                if(response.get("status").equals(1)) {
                    monitorDataLog.setUpLoadStatus(1);
                } else {
                    monitorDataLog.setUpLoadStatus(2);
                }
                monitorDataRepository.save(monitorDataLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void uploadTrafficFlow() {
        List<TrafficFlow> trafficFlowList = trafficFlowRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        trafficFlowList.forEach(trafficFlow -> {
            try {
                String requestBody= wrapperXml(transformService.transTrafficFlowDto(trafficFlow), "trafficflow");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").equals(1)) {
                    trafficFlow.setUpLoadStatus(1);
                } else {
                    trafficFlow.setUpLoadStatus(2);
                }
                trafficFlowRepository.save(trafficFlow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void uploadVehicleInfo() {
        List<VehicleInfo> vehicleInfoList = vehicleInfoRepository.findTop200ByUpLoadStatusIsNullOrderByUpLoadStatusDesc();
        vehicleInfoList.forEach(vehicleInfo -> {
            try {
                String requestBody = wrapperXml(transformService.transVehicleInfoDto(vehicleInfo), "vehicle");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").equals(1)) {
                    vehicleInfo.setUpLoadStatus(1);
                } else {
                    vehicleInfo.setUpLoadStatus(2);
                }
                vehicleInfoRepository.save(vehicleInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 上传车辆轨道信息
     */

    public void uploadVehicleTrajectory() {
        List<VehicleTrajectory> vehicleTrajectoryList = vehicleTrajectoryRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        vehicleTrajectoryList.forEach(vehicleTrajectory -> {
            try{
                String requestBody = wrapperXml(transformService.transVehicleTrajectoryDto(vehicleTrajectory), "vehicletrajectory");
                String responseStr = callApi(requestBody);
                JsonNode response = objectMapper.readTree(responseStr);
                if(response.get("status").equals(1)) {
                    vehicleTrajectory.setUpLoadStatus(1);
                } else {
                    vehicleTrajectory.setUpLoadStatus(2);
                }
                vehicleTrajectoryRepository.save(vehicleTrajectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    //工作日的上传时间段为： 18：00~8：00
    //@Scheduled(cron="*/10 * 18,19,20,21,22,23,24,1,2,3,4,5,6,7 * * MON-FRI")
   @Scheduled(cron="${workday}")
    public void workDay(){
        uploadVehicleTrajectory();
        uploadTrafficFlow();
        uploadVehicleInfo();
        uploadMonitorData();
        uploadBlacksomkevehicle();
    }
    //非工作日全天传输
    //@Scheduled(cron="*/10 * * * * SAT-SUN")
    @Scheduled(cron="${nonworkday}")
    public void  nonWorkDay(){
        uploadVehicleTrajectory();
        uploadTrafficFlow();
        uploadVehicleInfo();
        uploadMonitorData();
        uploadBlacksomkevehicle();
    }
}
