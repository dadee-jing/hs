package com.ruoyi.duge.service.impl;

import com.ruoyi.duge.domain.StationStatistics;
import com.ruoyi.duge.mapper.StationStatisticsMapper;
import com.ruoyi.duge.service.IStationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationStatisticsServiceImpl implements IStationStatisticsService {

    @Autowired
    private StationStatisticsMapper stationStatisticsMapper;

    @Override
    public StationStatistics selectStationInfoById(Integer id) {
        return stationStatisticsMapper.selectStationInfoById(id);
    }

    @Override
    public List<StationStatistics> selectStationStatisticsList(StationStatistics statistics) {
        return stationStatisticsMapper.selectStationStatisticsList(statistics);
    }

    @Override
    public List<StationStatistics> selectStationStatisticsDay(StationStatistics statistics) {
        return stationStatisticsMapper.selectStationStatisticsDay(statistics);
    }

    @Override
    public List<StationStatistics> selectStationStatisticsMonth(StationStatistics statistics) {
        return stationStatisticsMapper.selectStationStatisticsMonth(statistics);
    }

    @Override
    public int insertStationStatistics(StationStatistics statistics) {
        return stationStatisticsMapper.insertStationStatistics(statistics);
    }

    @Override
    public int updateStationStatistics(StationStatistics statistics) {
        return stationStatisticsMapper.updateStationStatistics(statistics);
    }

    @Override
    public int deleteStationStatisticsByIds(String[] ids) {
        return stationStatisticsMapper.deleteStationStatisticsByIds(ids);
    }

    @Override
    public int deleteStationStatisticsById(Integer id) {
        return stationStatisticsMapper.deleteStationStatisticsById(id);
    }
}
