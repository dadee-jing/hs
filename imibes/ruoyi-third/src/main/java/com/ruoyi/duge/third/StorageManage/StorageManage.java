package com.ruoyi.duge.third.StorageManage;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
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
    private final IConfigDataService configDataService;
    SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public StorageManage(IWeightDataMapperService weightDataMapperService, IConfigDataService configDataService) {
        this.weightDataMapperService = weightDataMapperService;
        this.configDataService = configDataService;
    }
    @Scheduled(cron = "${storagemanage.scheduled}")
    public void doStorageManage(){
      if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
        deleteIllegalAndOverDate();
        deleteOverDate();
        deleteIllegalOrOverDate();
      }
    }
    public void deleteIllegalAndOverDate(){
        List<WeightData> list=weightDataMapperService.selectIllegalAndOverDate();
        deleteFileByWeightDataList(list);
    }
    public void deleteOverDate(){
        List<WeightData> list=weightDataMapperService.selectOverDate();
        deleteFileByWeightDataList(list);
    }
    public void deleteIllegalOrOverDate(){
        List<WeightData> list=weightDataMapperService.selectIllegalOrOverDate();
        deleteFileByWeightDataList(list);
    }
    public void deleteFileByWeightDataList(List<WeightData> list){
        for (WeightData wd:list) {
            log.info("Delete file of "+wd.getTruckNumber()+" CreateTime:"+format.format(wd.getCreateTime()));
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
