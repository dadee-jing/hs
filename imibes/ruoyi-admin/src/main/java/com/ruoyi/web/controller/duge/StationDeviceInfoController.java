package com.ruoyi.web.controller.duge;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.duge.domain.*;
import com.ruoyi.duge.mapper.StationDeviceInfoMapper;
import com.ruoyi.duge.mapper.StationInfoMapper;
import com.ruoyi.duge.mapper.StationTypeMapper;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站点设备
 *
 * @author ruoyi
 * @date 2018-11-22
 */
@Controller
@RequestMapping("/module/stationDeviceInfo")
public class StationDeviceInfoController extends BaseController {
    private String prefix = "stationDeviceInfo";

    @Autowired
    private StationDeviceInfoMapper stationDeviceInfoMapper;
    @Autowired
    private StationInfoMapper stationInfoMapper;

    @GetMapping()
    public String stationType(ModelMap mmap) {
        setDataList(mmap);
        return prefix + "/stationDeviceInfo";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StationDeviceInfo stationDeviceInfo) {
        startPage();
        List<StationDeviceInfoVo> stationDeviceInfoList = stationDeviceInfoMapper.selectStationDeviceInfoList(stationDeviceInfo);
        return getDataTable(stationDeviceInfoList);
    }

    @PostMapping("/stationDeviceList/{stationId}")
    @ResponseBody
    public TableDataInfo stationDeviceInfoList(@PathVariable("stationId") Integer stationId, ModelMap mmap) {
        startPage();
        List<StationDeviceInfoVo> stationDeviceInfoList
                = stationDeviceInfoMapper.selectStationDeviceListByStationId(stationId);
        mmap.put("stationDeviceInfoList",stationDeviceInfoList);
        return getDataTable(stationDeviceInfoList);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap) {
        setDataList(mmap);
        return prefix + "/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StationDeviceInfo stationDeviceInfo) {
        return toAjax(stationDeviceInfoMapper.insertStationDeviceInfo(stationDeviceInfo));
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        setDataList(mmap);
        mmap.put("stationDeviceInfo", stationDeviceInfoMapper.selectDeviceById(id));
        return prefix + "/edit";
    }


    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StationDeviceInfo stationDeviceInfo) {
        return toAjax(stationDeviceInfoMapper.updateStationDeviceInfo(stationDeviceInfo));
    }

    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Integer id) {
        return toAjax(stationDeviceInfoMapper.deleteStationDeviceInfoById(id));
    }


    private void setDataList(ModelMap mmap) {
        List<StationInfo> stationInfoList = stationInfoMapper.selectStationInfoList(null);
        List<DeviceEnum> deviceTypeList = stationDeviceInfoMapper.selectDeviceEnumList(1);
        List<DeviceEnum> deviceNameList = stationDeviceInfoMapper.selectDeviceEnumList(2);
        mmap.put("stationList",stationInfoList);
        mmap.put("deviceTypeList",deviceTypeList);
        mmap.put("deviceNameList",deviceNameList);
    }

}
