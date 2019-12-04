package com.hs.rs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hs.rs.common.XStreamUtil;
import com.hs.rs.model.Envelope;
import com.hs.rs.persistence.dao.*;
import com.hs.rs.persistence.entity.*;
import com.hs.rs.service.TransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
@RestController
public class TestController {
@Autowired
private VehicleTrajectoryRepository vehicleTrajectoryRepository;
@Autowired
TrafficFlowRepository trafficFlowRepository;
@Autowired
VehicleInfoRepository vehicleInfoRepository;
@Autowired
MonitorDataRepository monitorDataRepository;
@Autowired
BlacksmokevehicleInfoRepository blacksmokevehicleInfoRepository;
@Autowired
TransformService transformService;
@Autowired
ObjectMapper objectMapper;
    /**
     * 测试车辆轨迹数据
     * @return
     */
    @GetMapping("/testVehicleTrajectory")
    public String testVehicleTrajectory(){
        List<VehicleTrajectory> vehicleTrajectoryList = vehicleTrajectoryRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        System.out.println("vehicleTrajectoryList Size is :"+vehicleTrajectoryList.size());
        for (int i=0;i<10;i++){
            System.out.println(vehicleTrajectoryList.get(i).toString());
        }
        return "ok";
    }/**
     * 测试交通流量信息
     * @return
     */
    @GetMapping("/trafficFlow")
    public String trafficFlow(){
        List<TrafficFlow> trafficFlowList = trafficFlowRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        System.out.println("trafficFlowList Size is :"+trafficFlowList.size());
        for (int i=0;i<10;i++){
            System.out.println(trafficFlowList.get(i).toString());
        }
        return "ok";
    }
    @GetMapping("/vehicleInfo")
    public String vehicleInfo(){
        List<VehicleInfo> vehicleInfoList = vehicleInfoRepository.findTop200ByUpLoadStatusIsNullOrderByUpLoadStatusDesc();
        System.out.println("vehicleInfoList Size is :"+vehicleInfoList.size());
        for (int i=0;i<10;i++){
            System.out.println(vehicleInfoList.get(i).toString());
        }
        return "ok";
    }
    /**
     * 测试遥感监测数据
     * @return
     */
  @GetMapping("/testMonitorData")
  public String testMonitorData(){
      List<MonitorDataLog> monitorDataLogList= monitorDataRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
      System.out.println("monitorDataLogList Size is :"+monitorDataLogList.size());
      for (int i=0;i<10;i++){
          System.out.println(monitorDataLogList.get(i).toString());
      }
      return "ok";
  }

    /**
     * 测试黑烟车数据
     * @return
     */
    @GetMapping("/testBlacksomkevehicle")
    public String testBlacksomkevehicle(){
        List<BlacksmokevehicleInfo> blacksmokevehicleInfoList= blacksmokevehicleInfoRepository.findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(1);
        System.out.println("blacksmokevehicleInfoList Size is :"+blacksmokevehicleInfoList.size());
        blacksmokevehicleInfoList.forEach(blacksmokevehicleInfo -> {
                String requestBody = wrapperXml(transformService.transBlacksmokevehicleDto(blacksmokevehicleInfo), "blacksmokevehicle");
                System.out.println("requestBody======"+requestBody);
        });
        return "ok";
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

}
