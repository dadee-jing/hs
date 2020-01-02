package com.ruoyi.duge.third.trafficPolice.scheduled;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.trafficPolice.utils.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

@Component
public class TrafficPoliceApiService {
    @Autowired
    IWeightDataMapperService weightDataMapperService;
    @Autowired
    IStationInfoService stationInfoService;
    @Autowired
    IConfigDataService configDataService;
    private static final Logger log = LoggerFactory.getLogger(TrafficPoliceApiService.class);
    @Scheduled(cron = "${trafficpolice.scheduled}")
    public void doUpload() throws UnsupportedEncodingException {
        if ("1".equals(configDataService.getConfigValue("upload_jj"))) {
            List<WeightData> weightDataList= weightDataMapperService.selectNotUploadJj() ;
            if (null != weightDataList && weightDataList.size() > 0) {
                log.info("to insert count:" + weightDataList.size());
                LongAdder successCount = new LongAdder();
                for (WeightData weightData : weightDataList) {
                    StationInfo stationInfo = stationInfoService.selectStationInfoById(weightData.getStationId().intValue(),weightData.getLaneMid());
                    Double overRate = weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue();
                    int overLevel = overRate > 0 ? overRate >= 0.3 ? 2 : 1 : 0;
                    int speedValue=weightData.getSpeed().intValue()-stationInfo.getSpeedLimit();
                    if (overLevel > 0 || speedValue>0) {
                        if(IOUtil.IllegalImages(weightData, stationInfo)){
                            weightData.setUploadJj(1);
                            successCount.increment();
                        }else {
                            weightData.setUploadJj(2);
                        }
                    } else {
                        if(IOUtil.normalImages(weightData, stationInfo)){
                            weightData.setUploadJj(1);
                            successCount.increment();
                        }else {
                            weightData.setUploadJj(2);
                        }
                    }
                    weightDataMapperService.updateData(weightData);
                }
                log.info("success count:" + successCount);
            }
        }
    }

}
