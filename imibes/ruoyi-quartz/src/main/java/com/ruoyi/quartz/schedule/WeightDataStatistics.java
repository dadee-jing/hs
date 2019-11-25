package com.ruoyi.quartz.schedule;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.StationStatistics;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.service.IStationStatisticsService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeightDataStatistics {

    private final IStationStatisticsService stationStatisticsService;
    private final IWeightDataMapperService weightDataMapperService;
    private final IStationInfoService stationInfoService;

    @Autowired
    public WeightDataStatistics(IStationStatisticsService stationStatisticsService,
                                IWeightDataMapperService weightDataMapperService,
                                IStationInfoService stationInfoService) {
        this.stationStatisticsService = stationStatisticsService;
        this.weightDataMapperService = weightDataMapperService;
        this.stationInfoService = stationInfoService;
    }

    //@Scheduled(cron = "* 5 * * * *")
    public void hourlyStatistics() {
        Date currentDate = new Date();
        String hourStr = DateFormatUtils.format(currentDate, "yyyy-MM-dd HH") + ":00:00";
        statisticsHour(hourStr);
    }

    public void statisticsHour(String hourStr) {
        Date beginDate = DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", hourStr);
        Instant instant = beginDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        Date endDate = org.apache.commons.lang3.time.DateUtils.addHours(beginDate, 1);
        WeightData weightData = new WeightData();
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss"));
        map.put("endTime", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss"));
        weightData.setParams(map);
        stationInfoService.selectStationInfoList(new StationInfo()).forEach(stationInfo -> {
            weightData.setStationId(stationInfo.getId().longValue());
            Map resultMap = weightDataMapperService.selectStationStatistics(weightData);
            StationStatistics stationStatistics = StationStatistics.builder()
                    .dateTime(beginDate)
                    .year(localDateTime.getYear())
                    .month(localDateTime.getMonthValue())
                    .day(localDateTime.getDayOfMonth())
                    .stationId(weightData.getStationId())
                    .trafficFlow((long) resultMap.get("trafficFlow"))
                    .overCount(resultMap.get("overCount") != null ? ((BigDecimal) resultMap.get("overCount")).longValue() : 0)
                    .build();
            if (stationStatistics.getTrafficFlow() > 0) {
                stationStatisticsService.insertStationStatistics(stationStatistics);
            }
        });
    }
}
