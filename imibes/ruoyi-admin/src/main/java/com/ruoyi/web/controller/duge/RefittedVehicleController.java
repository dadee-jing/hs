package com.ruoyi.web.controller.duge;

import com.ruoyi.duge.domain.RefittedVehicle;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.RefittedVehicleService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 改装车模块 控制层
 */
@Controller
@RequestMapping("/refittedVehicle")
public class RefittedVehicleController  extends BaseController{
    private String prefix = "refittedVehicle";

    @Autowired
    private RefittedVehicleService refittedVehicleService;

    @Autowired
    private IConfigDataService configDataService;

    @GetMapping("/dashboard")
    public String main(ModelMap mmap) {
        return prefix + "/refittedVehicleDashboard";
    }

    @PostMapping("/stations")
    @ResponseBody
    public TableDataInfo list(StationInfo stationInfo) {
        startPage();
        List<StationInfo> list = new ArrayList<>();
        StationInfo stationInfo1 = StationInfo.builder()
                .address("揭阳市揭东区榕西路口（公交站")
                .name("榕西路口")
                .index(1)
                .hourOverCount(8l)
                .longitude_GCJ02("116.330032")
                .latitude_GCJ02("23.545624")
                .build();
        list.add(stationInfo1);
        return getDataTable(list);
    }

    @GetMapping()
    public String refittedVehicleInfo(){ return prefix + "/refittedVehicleInfo"; }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RefittedVehicle refittedVehicle) {
        startPage();
        List<RefittedVehicle> list = refittedVehicleService.listRefittedVehicles(refittedVehicle);
        return getDataTable(list);
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
        RefittedVehicle refittedVehicle = refittedVehicleService.getRefittedVehicleById(id);
        mmap.put("data", refittedVehicle);
        mmap.put("ctxPath", configDataService.getConfigValue("nginx_url")
                + refittedVehicle.getId() + "/" + refittedVehicle.getTime() + "/");
        mmap.put("picPath", configDataService.getConfigValue("ftp_car_pic"));
        return "refittedVehicle/refittedVehicleDetail";
    }
}
