package com.ruoyi.duge.service.impl;

import com.ruoyi.duge.mapper.ReportMapper;
import com.ruoyi.duge.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    /**
     *  查找站点车流量信息
     *
     * @param month 月份
     * @param month 年份
     * @return 车流量信息
     */
    public List<LinkedHashMap<String, Object>> selectVehicleFlow(int year, int month){
        List<LinkedHashMap<String, Object>> vehicleFlow = reportMapper.selectVehicleFlow(year,month);
        System.out.println(vehicleFlow);
        return vehicleFlow;
    }
}
