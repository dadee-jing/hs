package com.ruoyi.duge.third.trafficPolice.controller;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.trafficPolice.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
        //是不是违法也要我们判断一下
//     Double overRate = weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue();
//     int overLevel = overRate > 0 ? overRate >= 0.3 ? 2 : 1 : 0;
//     if (overLevel > 0){
//     }else {
//     }
//
        ImageUploadUtil.IllegalImages(weightData, stationInfo);
        return "success";
    }
}
