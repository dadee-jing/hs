package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.StationInfo;

import java.util.List;

/**
 * 站点 服务层
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
public interface IStationInfoService 
{
	/**
     * 查询站点信息
     * 
     * @param id 站点ID
     * @return 站点信息
     */
	public StationInfo selectStationInfoById(Integer id, String laneMid);

	/**
	 * 查询站点列表
	 *
	 * @param stationInfo 站点信息
	 * @return 站点集合
	 */
	public List<StationInfo> selectStationInfoList(StationInfo stationInfo);

	/**
     * 新增站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     */
	public int insertStationInfo(StationInfo stationInfo);
	
	/**
     * 修改站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     */
	public int updateStationInfo(StationInfo stationInfo);
		
	/**
     * 删除站点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStationInfoByIds(String ids);

	/**
	 * 查询站点ip信息
	 */
	public List<StationInfo> selectStationStateInfo();

	String getStationLatesCarRecordTime(Integer stationId);

	String getRecentTime(Integer stationId);

}
