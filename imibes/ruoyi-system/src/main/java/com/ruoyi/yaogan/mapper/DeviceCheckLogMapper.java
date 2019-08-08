package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.DeviceCheckLog;

import java.util.List;

/**
 * 遥测设备检查 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface DeviceCheckLogMapper 
{
	/**
     * 查询遥测设备检查信息
     * 
     * @param iD 遥测设备检查ID
     * @return 遥测设备检查信息
     */
	public DeviceCheckLog selectDeviceCheckLogById(Integer iD);
	
	/**
     * 查询遥测设备检查列表
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 遥测设备检查集合
     */
	public List<DeviceCheckLog> selectDeviceCheckLogList(DeviceCheckLog deviceCheckLog);
	
	/**
     * 新增遥测设备检查
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 结果
     */
	public int insertDeviceCheckLog(DeviceCheckLog deviceCheckLog);
	
	/**
     * 修改遥测设备检查
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 结果
     */
	public int updateDeviceCheckLog(DeviceCheckLog deviceCheckLog);
	
	/**
     * 删除遥测设备检查
     * 
     * @param iD 遥测设备检查ID
     * @return 结果
     */
	public int deleteDeviceCheckLogById(Integer iD);
	
	/**
     * 批量删除遥测设备检查
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceCheckLogByIds(String[] iDs);
	
}