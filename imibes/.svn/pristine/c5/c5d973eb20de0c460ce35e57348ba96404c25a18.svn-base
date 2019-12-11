package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.DeviceData;

import java.util.List;	

/**
 * 设备 数据层
 * 
 * @author ruoyi
 * @date 2018-12-09
 */
public interface DeviceDataMapper {
	/**
     * 查询设备信息
     * 
     * @param id 设备ID
     * @return 设备信息
     */
	public DeviceData selectDataById(Long id);

	public List<DeviceData> selectNotUpload();

	/**
     * 查询设备列表
     * 
     * @param data 设备信息
     * @return 设备集合
     */
	public List<DeviceData> selectDataList(DeviceData data);
	
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
     * 删除设备
     * 
     * @param id 设备ID
     * @return 结果
     */
	public int deleteDataById(Long id);
	
	/**
     * 批量删除设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDataByIds(String[] ids);
	
}