package com.ruoyi.web.controller.duge.report;

import com.ruoyi.duge.domain.StationStatistics;
import com.ruoyi.duge.service.IStationStatisticsService;
import com.ruoyi.web.core.base.BaseController;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistics")
public class StationStatisticsController extends BaseController {

    private String prefix = "statistics";

    @Autowired
    private IStationStatisticsService stationStatisticsService;

    @PostMapping("/list")
    @ResponseBody
    public List list(StationStatistics data) {
        List<StationStatistics> list = stationStatisticsService.selectStationStatisticsList(data);
        return list.stream()
                .map(s -> {
                    List tmp = new ArrayList(2);
                    tmp.add(DateFormatUtils.format(s.getDateTime(), "yyyy-MM-dd HH:mm:ss"));
                    tmp.add(s.getOverCount());
                    return tmp;
                }).collect(Collectors.toList());
    }

    @PostMapping("/groupByDay")
    @ResponseBody
    public Map groupByDay(StationStatistics data) {
        List<StationStatistics> list = stationStatisticsService.selectStationStatisticsDay(data);

        List<Long> overCountList = list.stream().map(StationStatistics::getOverCount).collect(Collectors.toList());
        List<Long> trafficFlowList = list.stream().map(statistics -> statistics.getTrafficFlow() - statistics.getOverCount())
                .collect(Collectors.toList());
        List<String> dateStrList = list.stream()
                .map(s -> String.format("%04d-%02d-%02d", s.getYear(), s.getMonth(), s.getDay())).collect(Collectors.toList());
        Map resultMap = new HashMap();
        resultMap.put("overCountList", overCountList);
        resultMap.put("trafficFlowList", trafficFlowList);
        resultMap.put("dateStrList", dateStrList);
        return resultMap;
    }

    @PostMapping("/groupByMonth")
    @ResponseBody
    public Map groupByMonth(StationStatistics data) {
        List<StationStatistics> list = stationStatisticsService.selectStationStatisticsMonth(data);

        List<Long> overCountList = list.stream().map(StationStatistics::getOverCount).collect(Collectors.toList());
        List<Long> trafficFlowList = list.stream().map(statistics -> statistics.getTrafficFlow() - statistics.getOverCount())
                .collect(Collectors.toList());
        List<String> dateStrList = list.stream()
                .map(s -> String.format("%04d-%02d", s.getYear(), s.getMonth())).collect(Collectors.toList());
        Map resultMap = new HashMap();
        resultMap.put("overCountList", overCountList);
        resultMap.put("trafficFlowList", trafficFlowList);
        resultMap.put("dateStrList", dateStrList);
        return resultMap;
    }
}
