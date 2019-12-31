package com.ruoyi.duge.third.storageManage;

import com.ruoyi.common.utils.StringUtils;
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
import java.util.concurrent.atomic.LongAdder;

@Component
public class StorageManage {
    private static final Logger log = LoggerFactory.getLogger(StorageManage.class);
    private final IWeightDataMapperService weightDataMapperService;
    private final IConfigDataService configDataService;
    SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    public StorageManage(IWeightDataMapperService weightDataMapperService, IConfigDataService configDataService) {
        this.weightDataMapperService = weightDataMapperService;
        this.configDataService = configDataService;
    }
    /**过车数据删除*/
    @Scheduled(cron = "${storagemanage.scheduled40DayAgo}")
    public void delete40DayAgo() {
        System.out.println("定时任务执行");
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
            List<WeightData> list = weightDataMapperService.selectTruckNumberByTime();
            LongAdder successCount = new LongAdder();
            log.info("passcar and 40 days ago delete count:" + list.size());
            if(list.size()>0) {
                for (WeightData weightData : list) {
                    if (weightDataMapperService.selectByTruckNumber(weightData.getWeightingDate(), weightData.getTruckNumber()) > 0) {
                        weightData.setMarkDel(2);
                        weightDataMapperService.updateWeightDataBefore40Days(weightData);
                        continue;
                    }
                    System.out.println(weightData.getStationId());
                    deleteFileByWeightData(weightData);
                    successCount.increment();
                    weightData.setMarkDel(1);
                    weightDataMapperService.updateWeightDataBefore40Days(weightData);
                }
            }
            log.info("passcar and 40 days ago success count:" + successCount);
        }
    }
    /**报警车辆对应其过车数据删除*/
    @Scheduled(cron = "${storagemanage.scheduled90DayAgo}")
    public void deleteIllegalOr90DayAgo(){
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
        List<WeightData> list=weightDataMapperService.selectTruckNumberOver90Date();
        log.info("passcar  and 90 days ago delete count:" + list.size());
        LongAdder successCount = new LongAdder();
        if(list.size()>0) {
            for (WeightData weightData : list) {
                List<WeightData> weightDatalist = weightDataMapperService
                        .selectByTruckNumberOver90Date(weightData.getWeightingDate(),weightData.getTruckNumber());
                for (WeightData weight : weightDatalist) {
                    deleteFileByWeightData(weightData);
                    successCount.increment();
                    weight.setMarkDel(1);
                    weightDataMapperService.updateWeightDataBefore40Days(weightData);
                }
            }
            log.info("passcar and 90 days ago success count:" + successCount);
        }
        }
    }
    @Scheduled(cron = "${storagemanage.scheduled2YearAgo}")
    public void deleteIllegalAnd2YearAgo(){
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
            List<WeightData> list=weightDataMapperService.selectIllegalAnd2YearAgo();
            LongAdder successCount = new LongAdder();
            log.info("peccancy  and 2 years ago delete count:" + list.size());
            if(list.size()>0) {
                for (WeightData weightData : list) {
                    deleteFileByWeightData(weightData);
                    successCount.increment();
                    weightData.setMarkDel(1);
                    weightDataMapperService.updateWeightDataBefore40Days(weightData);
                }
            }
            log.info("peccancy  and 2 years ago success count:" + successCount);
        }
    }
    public void deleteFileByWeightData(WeightData wd){
            String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
            if(StringUtils.isNotBlank(wd.getFtpHead())){
                new File(basePath+wd.getFtpHead()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpAxle())){
                new File(basePath+wd.getFtpAxle()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpTail())){
                new File(basePath+wd.getFtpTail()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpPriorHead())){
                new File(basePath+wd.getFtpPriorHead()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpPlate())){
                new File(basePath+wd.getFtpPlate()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpFullView())){
                new File(basePath+wd.getFtpFullView()).delete();}
        }
}
