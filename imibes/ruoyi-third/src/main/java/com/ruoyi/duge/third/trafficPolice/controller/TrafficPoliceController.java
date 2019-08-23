package com.ruoyi.duge.third.trafficPolice.controller;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.trafficPolice.utils.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrafficPoliceController {
    @Autowired
    IWeightDataMapperService weightDataMapperService;
    @Autowired
    IStationInfoService stationInfoService;
    @RequestMapping("/ftpserver")
    public String  ftpServer(String id) {
        long weightId = Long.parseLong(id);
        WeightData weightData = weightDataMapperService.selectDataById(weightId);
        StationInfo stationInfo = stationInfoService.selectStationInfoById(weightData.getStationId().intValue());

    Double overRate = weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue();
    int overLevel = overRate > 0 ? overRate >= 0.3 ? 2 : 1 : 0;
    if (overLevel > 0){
        IOUtil.IllegalImages(weightData, stationInfo);
    }else {
        IOUtil.normalImages(weightData);
    }
        return "success";
    }
}
