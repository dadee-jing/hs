package com.ruoyi.yaogan.service;

import com.ruoyi.yaogan.domain.DeviceDynamicCheck;

import java.util.List;

/**
 * 遥测设备动态检查过程数据 服务层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface IDeviceDynamicCheckService 
{
	/**
     * 查询遥测设备动态检查过程数据信息
     * 
     * @param iD 遥测设备动态检查过程数据ID
     * @return 遥测设备动态检查过程数据信息
     */
	public DeviceDynamicCheck selectDeviceDynamicCheckById(Integer iD);
	
	/**
     * 查询遥测设备动态检查过程数据列表
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 遥测设备动态检查过程数据集合
     */
	public List<DeviceDynamicCheck> selectDeviceDynamicCheckList(DeviceDynamicCheck deviceDynamicCheck);
	
	/**
     * 新增遥测设备动态检查过程数据
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 结果
     */
	public int insertDeviceDynamicCheck(DeviceDynamicCheck deviceDynamicCheck);
	
	/**
     * 修改遥测设备动态检查过程数据
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 结果
     */
	public int updateDeviceDynamicCheck(DeviceDynamicCheck deviceDynamicCheck);
		
	/**
     * 删除遥测设备动态检查过程数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceDynamicCheckByIds(String ids);
	
}
