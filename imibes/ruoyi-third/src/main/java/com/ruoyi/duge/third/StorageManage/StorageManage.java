package com.ruoyi.duge.third.StorageManage;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IWeightDataMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
@Component
public class StorageManage {
    private static final Logger log = LoggerFactory.getLogger(StorageManage.class);
    @Autowired
    private final IWeightDataMapperService weightDataMapperService;

    SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );

    public StorageManage(IWeightDataMapperService weightDataMapperService) {
        this.weightDataMapperService = weightDataMapperService;
    }
    //@Scheduled(cron="*/3 * * * * ?")
    public void deleteIllegalAndOverDate(){
        log.info("deleteIllegalAndOverDate start");
        System.out.println("deleteIllegalAndOverDate start");
        List<WeightData> list=weightDataMapperService.selectIllegalAndOverDate();
        for (WeightData wd:list) {
            String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
             if(wd.getFtpHead()!=null){
                 new File(basePath+wd.getFtpHead()).delete();}
             else if(wd.getFtpAxle()!=null){
                new File(basePath+wd.getFtpAxle()).delete();}
             else if(wd.getFtpTail()!=null){
                new File(basePath+wd.getFtpTail()).delete();}
             else if(wd.getFtpPriorHead()!=null){
                new File(basePath+wd.getFtpPriorHead()).delete();}
             else if(wd.getFtpPlate()!=null){
                new File(basePath+wd.getFtpPlate()).delete();}
             else if(wd.getFtpFullView()!=null){
                 new File(basePath+wd.getFtpFullView()).delete();}
        }
    }

}
