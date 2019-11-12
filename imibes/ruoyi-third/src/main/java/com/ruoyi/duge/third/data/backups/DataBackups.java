package com.ruoyi.duge.third.data.backups;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IWeightDataMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBackups {
    @Autowired
    private  IWeightDataMapperService weightDataMapperService;
    @Scheduled(cron = "${databackups.scheduled}")
    private void backupBefore40Days(){
       List<WeightData> list= weightDataMapperService.selectBefore40Days();
       for(WeightData weightData:list){
        int result=weightDataMapperService.insertIntoWeightDataBefore40Days(weightData);
        if(result==1){
        weightDataMapperService.deleteDataByIds(weightData.getId().toString());}
        }
    }
}
