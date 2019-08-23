package com.ruoyi.web.controller.tic;

import com.ruoyi.duge.domain.DeviceHealthState;
import com.ruoyi.duge.service.IDeviceHealthStateService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tic")
public class TicController extends BaseController{
    @Autowired
    IDeviceHealthStateService deviceHealthStateService;

    @GetMapping("/main")
    public String main(ModelMap mmap) {
        return "/tic/tic_main";
    }

    @GetMapping("/station")
    public String station(ModelMap mmap) {
        return "/tic/tic_station";
    }

    @GetMapping("/record")
    public String record(ModelMap mmap) {
        return "/tic/tic_record";
    }

    @GetMapping("/weight")
    public String weight(ModelMap mmap) {
        return "/tic/tic_weight";
    }

    @GetMapping("/stationDetails")
    public String stationDetails(ModelMap mmap) {
        return "/tic/tic_station_details";
    }

    @PostMapping("/listDeviceState")
    @ResponseBody
    public TableDataInfo listDeviceState(String stationCode){
        List<Integer> deviceIdList = deviceHealthStateService.findDeviceIdListByStationCode(stationCode);
        startPage();
        List<DeviceHealthState> deviceHealthStateList = deviceHealthStateService.findNewestDeviceStateByDeviceIdList(deviceIdList);
        return getDataTable(deviceHealthStateList);
    }

}
