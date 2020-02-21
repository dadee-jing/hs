package com.ruoyi.duge.third.databackups;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IWeightDataMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

@Component
public class DataBackups {
    @Autowired
    private  IWeightDataMapperService weightDataMapperService;
    private List<WeightData> list;
    private static final Logger log = LoggerFactory.getLogger(DataBackups.class);
    @Scheduled(cron = "${databackups.scheduled}")
    private void backupBefore40Days(){
        List<WeightData> list= weightDataMapperService.selectBefore40Days();
        log.info("to insert count:" + list.size());
        LongAdder successCount = new LongAdder();
        for(WeightData weightData:list){
            int result=weightDataMapperService.insertIntoWeightDataBefore40Days(weightData);
            if(result==1){
                weightDataMapperService.deleteDataByIds(weightData.getId().toString());
                successCount.increment();
            }
        }
        log.info("success count:" + successCount);
    }
}