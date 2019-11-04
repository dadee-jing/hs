package com.ruoyi.duge.third.trafficPolice.scheduled;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.trafficPolice.utils.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
@Component
public class TrafficPoliceApiService {
    @Autowired
    IWeightDataMapperService weightDataMapperService;
    @Autowired
    IStationInfoService stationInfoService;
    @Autowired
    IConfigDataService configDataService;
    @Scheduled(cron = "${trafficpolice.scheduled}")
    public void doUpload() throws UnsupportedEncodingException {
        if ("1".equals(configDataService.getConfigValue("upload_jj"))) {
             List<WeightData> list= weightDataMapperService.selectNotUploadJj() ;
             for(WeightData weightData:list){
                 StationInfo stationInfo = stationInfoService.selectStationInfoById(weightData.getStationId().intValue());
                 Double overRate = weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue();
                 int overLevel = overRate > 0 ? overRate >= 0.3 ? 2 : 1 : 0;
                 if (overLevel > 0){
                     IOUtil.IllegalImages(weightData, stationInfo);

                 }else {
                     IOUtil.normalImages(weightData,stationInfo);
                 }
                 for (WeightData weightData1:list) {
                     weightData1.setUploadJj(1);
                     weightDataMapperService.updateData(weightData1);
                 }

             }
        }
    }
}
