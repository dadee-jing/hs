package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.DeviceData;

import java.util.List;

/**
 * 设备 服务层
 * 
 * @author ruoyi
 * @date 2018-12-09
 */
public interface IDeviceDataService
{
	/**
     * 查询设备信息
     * 
     * @param id 设备ID
     * @return 设备信息
     */
	public DeviceData selectDataById(Long id);
	
	/**
     * 查询设备列表
     * 
     * @param data 设备信息
     * @return 设备集合
     */
	public List<DeviceData> selectDataList(DeviceData data);

	public List<DeviceData> selectNotUploadDataList();
	
	/**
     * 新增设备
     * 
     * @param data 设备信息
     * @return 结果
     */
	public int insertData(DeviceData data);
	
	/**
     * 修改设备
     * 
     * @param data 设备信息
     * @return 结果
     */
	public int updateData(DeviceData data);
		
	/**
     * 删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDataByIds(String ids);
	
}
