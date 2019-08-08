package com.ruoyi.duge.mapper;

import java.util.LinkedHashMap;
import java.util.List;

public interface ReportMapper {
    /**
     *  查找站点车流量信息
     *
     * @param month 月份
     * @param month 年份
     * @return 车流量信息
     */
    public List<LinkedHashMap<String, Object>> selectVehicleFlow(int year,int month);
}
