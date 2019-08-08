package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.StationStatistics;

import java.util.List;

/**
 * 站点 服务层
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
public interface IStationStatisticsService
{
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
     * 删除站点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStationStatisticsByIds(String[] ids);

	public int deleteStationStatisticsById(Integer id);
	
}
