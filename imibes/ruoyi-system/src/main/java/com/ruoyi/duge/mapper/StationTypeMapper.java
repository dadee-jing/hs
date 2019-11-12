package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.DeptTest;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.StationType;

import java.util.List;

/**
 * 站点 数据层
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
public interface StationTypeMapper
{
	/**
     * 查询站点信息
     * 
     * @param id 站点ID
     * @return 站点信息
     */
	//public StationInfo selectStationInfoById(Integer id);
	
	/**
     * 查询站点列表
     * 
     * @param StationType 站点信息
     * @return 站点集合
     */
	public List<StationType> selectStationTypeList();

	public List<DeptTest> selectDeptTest();

/*	*//**
     * 新增站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     *//*
	public int insertStationInfo(StationInfo stationInfo);
	
	*//**
     * 修改站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     *//*
	public int updateStationInfo(StationInfo stationInfo);
	
	*//**
     * 删除站点
     * 
     * @param id 站点ID
     * @return 结果
     *//*
	public int deleteStationInfoById(Integer id);
	
	*//**
     * 批量删除站点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     *//*
	public int deleteStationInfoByIds(String[] ids);

	*//**
	 * 查询站点ip信息
	 *//*
	public List<StationInfo> selectStationStateInfo();*/
}