package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.StationStatistics;

import java.util.List;

/**
 * 站点 数据层
 *
 * @author ruoyi
 * @date 2018-11-22
 */
public interface StationStatisticsMapper {
    /**
     * 查询站点信息
     *
     * @param id 站点ID
     * @return 站点信息
     */
    public StationStatistics selectStationInfoById(Integer id);

    /**
     * 查询站点列表
     *
     * @param statistics 站点信息
     * @return 站点集合
     */
    public List<StationStatistics> selectStationStatisticsList(StationStatistics statistics);


    public List<StationStatistics> selectStationStatisticsDay(StationStatistics statistics);

    public List<StationStatistics> selectStationStatisticsMonth(StationStatistics statistics);

    /**
     * 新增站点
     *
     * @param statistics 站点信息
     * @return 结果
     */
    public int insertStationStatistics(StationStatistics statistics);

    /**
     * 修改站点
     *
     * @param statistics 站点信息
     * @return 结果
     */
    public int updateStationStatistics(StationStatistics statistics);

    /**
     * 删除站点
     *
     * @param id 站点ID
     * @return 结果
     */
    public int deleteStationStatisticsById(Integer id);

    /**
     * 批量删除站点
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStationStatisticsByIds(String[] ids);

}