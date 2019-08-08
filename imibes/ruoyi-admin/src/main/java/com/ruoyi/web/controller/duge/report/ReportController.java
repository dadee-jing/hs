package com.ruoyi.web.controller.duge.report;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IReportService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.BlacksmokevehicleInfo;
import com.ruoyi.yaogan.mapper.BlacksmokevehicleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
    private String prefix = "report";

    @Autowired
    private IReportService reportService;
    @Autowired
    private IWeightDataMapperService weightDataMapperService;
    @Autowired
    private BlacksmokevehicleInfoMapper blacksmokevehicleInfoMapper;

    @GetMapping()
    public String report() {
        return prefix + "/report";
    }

    @GetMapping("/total")
    public String reportAll() {
        return prefix + "/reportAll";
    }

    @GetMapping("/single")
    public String reportSingle() {
        return prefix + "/reportSingle";
    }

    @GetMapping("/reportCar")
    public String reportCar() {
        return prefix + "/reportCar";
    }

    @GetMapping("/reportTraffic")
    public String reportTraffic() {
        return prefix + "/reportTraffic";
    }

    /**
     * 加载车流量信息
     */
    @GetMapping("/selectVehicleFlow")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> selectVehicleFlow() {
        List<LinkedHashMap<String, Object>> vehicleFlow = reportService.selectVehicleFlow(2018, 12);
        return vehicleFlow;
    }

    @GetMapping("/indexReport")
    @ResponseBody
    public Map indexReport() {
        LocalDateTime localDateTime = LocalDateTime.now();
        WeightData weightData = new WeightData();
        weightData.setStationId(3l);
        Map<String, Object> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.put("beginTime", formatter.format(LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
                localDateTime.getDayOfMonth(), 0, 0)));
        map.put("endTime", formatter.format(localDateTime));
        weightData.setParams(map);
        Map overMap = weightDataMapperService.selectStationStatistics(weightData);
        BlacksmokevehicleInfo blacksmokevehicleInfo = new BlacksmokevehicleInfo();
        blacksmokevehicleInfo.setParams(map);
        Long blacksmokeCount = blacksmokevehicleInfoMapper.selecBlacksmokeCount(blacksmokevehicleInfo);
        overMap.put("blacksmokeCount", blacksmokeCount);
        return overMap;
    }
}