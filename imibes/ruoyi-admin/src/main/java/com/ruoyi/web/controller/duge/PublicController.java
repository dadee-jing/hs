package com.ruoyi.web.controller.duge;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.duge.domain.*;
import com.ruoyi.duge.mapper.StationDeviceInfoMapper;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.third.foshan.service.FoshanApiService;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.shunde.service.ShundeApiService;
import com.ruoyi.web.core.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

/*
    */
/**
     * 从站点发请求更新状态信息
     * @param stationInfo
     * @return
     *//*

    @PostMapping("/updateStationState")
    @ResponseBody
    public AjaxResult updateStationStateInfo(@RequestBody com.ruoyi.duge.domain.StationInfo stationInfo) {
        int result;
        try {
            LOGGER.info("updateStationState:" + stationInfo.toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = simpleDateFormat.format(new Date());
            stationInfo.setRemarkInfo(now);
            result = stationInfoService.updateStationInfo(stationInfo);
        }catch (Exception e){
            result = 0;
            LOGGER.info("updateStationState error," + stationInfo.getName(),e);
        }
        return toAjax(result);
    }
*/


    /**
     * 新增站点信息与设备信息
     */
    @PostMapping("/addStationAndDeviceInfo")
    @ResponseBody
    public int addStationAndDeviceInfo(@RequestBody StationInfoWithDeviceInfoList stationInfoWithDeviceInfoList) {
        int result = 0;
        int stationId = 0;
        try {
            LOGGER.info("addStationAndDeviceInfo:" + stationInfoWithDeviceInfoList.toString());
            //id为0代表新增站点，不为0更新站点信息
            if (null != stationInfoWithDeviceInfoList.getId() && 0 != stationInfoWithDeviceInfoList.getId()) {
                stationInfoService.updateStationInfo(stationInfoWithDeviceInfoList);
                stationId = stationInfoWithDeviceInfoList.getId();
            } else {
                stationInfoService.insertStationInfo(stationInfoWithDeviceInfoList);
                stationId = stationInfoWithDeviceInfoList.getId();
            }
            //传入设备名称，类型名称。查找设备枚举是否存在，不存在新增
            List<StationDeviceInfoVo> stationDeviceInfoVoList = stationInfoWithDeviceInfoList.getDeviceList();
            for (StationDeviceInfoVo stationDeviceInfoVo : stationDeviceInfoVoList) {
                stationDeviceInfoVo.setStationId(stationId);
                Integer deviceTypeId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceTypeName(), 1);
                Integer deviceNameId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceName(), 2);
                if (null == deviceTypeId || deviceTypeId == 0) {
                    DeviceEnum deviceEnum = new DeviceEnum();
                    deviceEnum.setValue(stationDeviceInfoVo.getDeviceTypeName());
                    deviceEnum.setType(1);
                    stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                    deviceTypeId = deviceEnum.getId();
                }
                if (null == deviceNameId || deviceNameId == 0) {
                    DeviceEnum deviceEnum = new DeviceEnum();
                    deviceEnum.setValue(stationDeviceInfoVo.getDeviceName());
                    deviceEnum.setType(2);
                    stationDeviceInfoMapper.insertDeviceEnum(deviceEnum);
                    deviceNameId = deviceEnum.getId();
                }
                //查找站点是否存在设备，存在则更新，不存在新增
                Integer deviceInfoId = stationDeviceInfoMapper.getStationDeviceInfoId(stationId, deviceTypeId,deviceNameId);
                stationDeviceInfoVo.setDeviceNameId(deviceNameId);
                stationDeviceInfoVo.setDeviceTypeId(deviceTypeId);
                if (null == deviceInfoId || deviceInfoId == 0) {
                    stationDeviceInfoMapper.insertStationDeviceInfoVo(stationDeviceInfoVo);
                }
                else{
                    stationDeviceInfoMapper.updateStationDeviceInfo(stationDeviceInfoVo);
                }
            }
        }catch (Exception e){
            LOGGER.info("addStationAndDeviceInfo error," + stationInfoWithDeviceInfoList.toString(),e);
        }
        return stationId;
    }

    /**
     * 更新站点和设备状态
     * @param stationInfoWithDeviceInfoList
     * @return
     */
    @PostMapping("/updateStationDeviceState")
    @ResponseBody
    public AjaxResult updateStationDeviceState(@RequestBody StationInfoWithDeviceInfoList stationInfoWithDeviceInfoList) {
        int result;
        try {
            //站点状态
            LOGGER.info("updateStationDeviceState:" + stationInfoWithDeviceInfoList.toString());
            StationInfo stationInfo = new StationInfo();
            BeanUtils.copyProperties(stationInfoWithDeviceInfoList,stationInfo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = simpleDateFormat.format(new Date());
            stationInfo.setRemarkInfo(now);
            result = stationInfoService.updateStationInfo(stationInfo);
            //设备状态
            List<StationDeviceInfoVo> stationDeviceInfoVoList = stationInfoWithDeviceInfoList.getDeviceList();
            for (StationDeviceInfoVo stationDeviceInfoVo : stationDeviceInfoVoList) {
                Integer deviceNameId = stationDeviceInfoMapper.getDeviceEnumIdByName(stationDeviceInfoVo.getDeviceName(), 2);
                StationDeviceInfo stationDeviceInfo = stationDeviceInfoMapper.selectDeviceByStationAndNameId(stationInfo.getId(),deviceNameId);
                stationDeviceInfo.setState(stationDeviceInfoVo.getState());
                stationDeviceInfo.setRemark(stationDeviceInfoVo.getRemark());
                stationDeviceInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                result = stationDeviceInfoMapper.updateStationDeviceInfo(stationDeviceInfo);
            }

        }catch (Exception e){
            result = 0;
            LOGGER.info("updateStationDeviceState error," + stationInfoWithDeviceInfoList.getName(),e);
        }
        return toAjax(result);
    }


}
