package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.StationCheckLog;

import java.util.List;

/**
 * 遥测设备自检 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface StationCheckLogMapper 
{
	/**
     * 查询遥测设备自检信息
     * 
     * @param iD 遥测设备自检ID
     * @return 遥测设备自检信息
     */
	public StationCheckLog selectStationCheckLogById(Integer iD);
	
	/**
     * 查询遥测设备自检列表
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 遥测设备自检集合
     */
	public List<StationCheckLog> selectStationCheckLogList(StationCheckLog stationCheckLog);
	
	/**
     * 新增遥测设备自检
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 结果
     */
	public int insertStationCheckLog(StationCheckLog stationCheckLog);
	
	/**
     * 修改遥测设备自检
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 结果
     */
	public int updateStationCheckLog(StationCheckLog stationCheckLog);
	
	/**
     * 删除遥测设备自检
     * 
     * @param iD 遥测设备自检ID
     * @return 结果
     */
	public int deleteStationCheckLogById(Integer iD);
	
	/**
     * 批量删除遥测设备自检
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteStationCheckLogByIds(String[] iDs);
	
}