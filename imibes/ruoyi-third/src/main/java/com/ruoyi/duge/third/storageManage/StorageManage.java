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
    @Scheduled(cron = "${storagemanage.scheduled2year}")
    public void deleteIllegalAndOverDate(){
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
        List<WeightData> list=weightDataMapperService.selectIllegalAndOverDate();
        if(list.size()>0) {
            deleteFileByWeightDataList(list);
            for (WeightData weightData : list) {
                weightData.setMarkDel(1);
                weightDataMapperService.updateData(weightData);
            }
        }
        }
    }
    /**过车数据删除*/
    @Scheduled(cron = "${storagemanage.scheduled14day}")
    public void deleteOverDate(){
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
        List<String> list=weightDataMapperService.selectTruckNumberByTime();
        for (String  truckNumber:list){
            if( 0==weightDataMapperService.selectIsIllegalByTruckNumber(truckNumber).size()){
                List<WeightData> weightDatalist=weightDataMapperService.selectByTruckNumber(truckNumber);
                deleteFileByWeightDataList(weightDatalist);
                for(WeightData weightData : weightDatalist ){
                    weightData.setMarkDel(1);
                    weightDataMapperService.updateData(weightData);
                }
            }
        }
        }
    }
    /**报警车辆对应其过车数据删除*/
    @Scheduled(cron = "${storagemanage.scheduled90day}")
    public void deleteIllegalOrOverDate(){
        if ("1".equals(configDataService.getConfigValue("do_storage_manage"))) {
        List<String> list=weightDataMapperService.selectTruckNumberOver90Date();
        System.out.println("list.size()="+list.size());
        if(list.size()>0) {
            for (String truckNumber : list) {
                System.out.println("truckNumber=" + truckNumber);
                List<WeightData> weightDatalist = weightDataMapperService.selectByTruckNumberOver90Date(truckNumber);
                System.out.println("weightDatalist.size()=" + weightDatalist.size());
                deleteFileByWeightDataList(weightDatalist);
                for (WeightData weightData : weightDatalist) {
                    weightData.setMarkDel(1);
                    weightDataMapperService.updateData(weightData);
                }
            }
        }
        }
    }
    public void deleteFileByWeightDataList(List<WeightData> list){
        for (WeightData wd:list) {
            log.info("Delete file of "+wd.getTruckNumber()+" CreateTime:"+format.format(wd.getCreateTime()));
            String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
            if(StringUtils.isNotBlank(wd.getFtpHead())){
                log.info(basePath+wd.getFtpHead());
                new File(basePath+wd.getFtpHead()).delete();}
            else if(StringUtils.isNotBlank(wd.getFtpAxle())){
                log.info(basePath+wd.getFtpAxle());
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
}
