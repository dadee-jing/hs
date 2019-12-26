package com.ruoyi.web.controller.duge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.duge.domain.*;
import com.ruoyi.duge.mapper.StationDeviceInfoMapper;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.third.foshan.service.FoshanApiService;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.shunde.service.ShundeApiService;
import com.ruoyi.duge.third.trafficPolice.utils.IOUtil;
import com.ruoyi.web.core.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicController extends BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(PublicController.class);
    @Autowired
    private com.ruoyi.duge.service.IWeightDataMapperService dataService;
    @Autowired
    private ShundeApiService shundeApiService;
    @Autowired
    private IStationInfoService stationInfoService;
    @Autowired
    private StationDeviceInfoMapper stationDeviceInfoMapper;


    /**
     * 新增站点信息与设备信息
     */
    @PostMapping("/addStationAndDeviceInfo")
    @ResponseBody
    public AjaxResult addStationAndDeviceInfo(@RequestBody StationInfoWithDeviceInfoList stationInfoWithDeviceInfoList) {
        int stationId;
        int result = 0;
        if(null != stationInfoWithDeviceInfoList.getId() && 0 != stationInfoWithDeviceInfoList.getId()){
            stationInfoService.updateStationInfo(stationInfoWithDeviceInfoList);
            stationId = stationInfoWithDeviceInfoList.getId();
        }
        else{
            stationInfoService.insertStationInfo(stationInfoWithDeviceInfoList);
            stationId = stationInfoWithDeviceInfoList.getId();
        }
        List<StationDeviceInfoVo> stationDeviceInfoVoList = stationInfoWithDeviceInfoList.getDeviceList();
        for(StationDeviceInfoVo stationDeviceInfoVo : stationDeviceInfoVoList){
            stationDeviceInfoVo.setStationId(stationId);
            Integer deviceTypeId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceTypeName(),1);
            Integer deviceNameId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceName(),2);
            if(null == deviceTypeId || deviceTypeId == 0){
                DeviceEnum deviceEnum = new DeviceEnum();
                deviceEnum.setValue(stationDeviceInfoVo.getDeviceTypeName());
                deviceEnum.setType(1);
                stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                deviceTypeId = deviceEnum.getId();
            }
            if(null == deviceNameId || deviceNameId == 0){
                DeviceEnum deviceEnum = new DeviceEnum();
                deviceEnum.setValue(stationDeviceInfoVo.getDeviceName());
                deviceEnum.setType(2);
                stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                deviceNameId = deviceEnum.getId();
            }
            stationDeviceInfoVo.setDeviceNameId(deviceNameId);
            stationDeviceInfoVo.setDeviceTypeId(deviceTypeId);
            result = stationDeviceInfoMapper.insertStationDeviceInfoVo(stationDeviceInfoVo);
        }
        return toAjax(result);
    }

/*
    *//**
     * 从站点新增设备信息
     * @param stationDeviceInfoVoList
     * @return
     *//*
    @PostMapping("/addStationDeviceInfo")
    @ResponseBody
    public int addStationDeviceInfo(@RequestBody List<StationDeviceInfoVo> stationDeviceInfoVoList) {
        //传入设备名称，类型名称。查找是否存在，不存在新增
        int result = 0;
        for(StationDeviceInfoVo stationDeviceInfoVo : stationDeviceInfoVoList){
            Integer deviceTypeId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceTypeName(),1);
            Integer deviceNameId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceName(),2);
            if(null == deviceTypeId || deviceTypeId == 0){
                DeviceEnum deviceEnum = new DeviceEnum();
                deviceEnum.setValue(stationDeviceInfoVo.getDeviceTypeName());
                deviceEnum.setType(1);
                stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                deviceTypeId = deviceEnum.getId();
            }
            if(null == deviceNameId || deviceNameId == 0){
                DeviceEnum deviceEnum = new DeviceEnum();
                deviceEnum.setValue(stationDeviceInfoVo.getDeviceName());
                deviceEnum.setType(2);
                stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                deviceNameId = deviceEnum.getId();
            }
            stationDeviceInfoVo.setDeviceNameId(deviceNameId);
            stationDeviceInfoVo.setDeviceTypeId(deviceTypeId);
            result += stationDeviceInfoMapper.insertStationDeviceInfoVo(stationDeviceInfoVo);
        }
        return result;
    }

    *//**
     * 新增站点信息
     * @param stationInfo
     * @return
     *//*
    @PostMapping("/addStationInfo")
    @ResponseBody
    public int addStationInfo(@RequestBody StationInfo stationInfo) {
        int result;
        if(null != stationInfo.getId() && 0 != stationInfo.getId()){
            result = stationInfoService.updateStationInfo(stationInfo);
            return result;
        }
        stationInfoService.insertStationInfo(stationInfo);
        result = stationInfo.getId();
        return result;
    }*/


    @PostMapping("/weightData/add")
    @ResponseBody
    public AjaxResult outerAddSave(@RequestBody com.ruoyi.duge.domain.WeightData data) {
        int result;
        try {
            data.setId(null);
            data.setCreateTime(new Date());
            data.setUpdateTime(new Date());
            result = dataService.insertData(data);
        } catch (DuplicateKeyException e) {
            result = 1;
            System.out.println("DuplicateKeyException");
        } catch (Exception e) {
            result = 0;
        }
        if(result == 1){
            LOGGER.info("insert "+ data.getStationId() + " " + data.getWeightingDate() + " " + data.getTruckNumber());
        }
        return toAjax(result);
    }

    @Autowired
    private FoshanApiService foshanApiService;

    @GetMapping("/submit/foshan")
    @ResponseBody
    public String submitFoshan(String id) {
        try {
            foshanApiService.submitVehicleData(Long.parseLong(id));
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/submit/byte")
    @ResponseBody
    public String submitByte(@RequestBody String byteString) {
        try {
            foshanApiService.submitByte(byteString);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/submit/shunde/{id}")
    @ResponseBody
    public String submitShunde(@PathVariable("id") Long id) {
        try {
            WeightData weightData = dataService.selectDataById(id);
            shundeApiService.submitVehicleData(BaseVehicleDataRequest.builder().weightData(weightData).build());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 从站点发请求更新状态信息
     * @param stationInfo
     * @return
     */
    @PostMapping("/updateStationState")
    @ResponseBody
    public AjaxResult updateStationStateInfo(@RequestBody com.ruoyi.duge.domain.StationInfo stationInfo) {
        int result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());
        stationInfo.setRemarkInfo(now);
        try {
            result = stationInfoService.updateStationInfo(stationInfo);
        }catch (Exception e) {
            result = 0;
        }
        if(result == 1){
            LOGGER.info("update "+ stationInfo.getId() + " " + stationInfo.getName());
        }
        return toAjax(result);
    }


}
