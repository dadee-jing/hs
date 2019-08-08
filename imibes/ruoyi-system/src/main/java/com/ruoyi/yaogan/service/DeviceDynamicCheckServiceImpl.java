package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.DeviceDynamicCheck;
import com.ruoyi.yaogan.mapper.DeviceDynamicCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥测设备动态检查过程数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class DeviceDynamicCheckServiceImpl implements IDeviceDynamicCheckService 
{
	@Autowired
	private DeviceDynamicCheckMapper deviceDynamicCheckMapper;

	/**
     * 查询遥测设备动态检查过程数据信息
     * 
     * @param iD 遥测设备动态检查过程数据ID
     * @return 遥测设备动态检查过程数据信息
     */
    @Override
	public DeviceDynamicCheck selectDeviceDynamicCheckById(Integer iD)
	{
	    return deviceDynamicCheckMapper.selectDeviceDynamicCheckById(iD);
	}
	
	/**
     * 查询遥测设备动态检查过程数据列表
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 遥测设备动态检查过程数据集合
     */
	@Override
	public List<DeviceDynamicCheck> selectDeviceDynamicCheckList(DeviceDynamicCheck deviceDynamicCheck)
	{
	    return deviceDynamicCheckMapper.selectDeviceDynamicCheckList(deviceDynamicCheck);
	}
	
    /**
     * 新增遥测设备动态检查过程数据
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 结果
     */
	@Override
	public int insertDeviceDynamicCheck(DeviceDynamicCheck deviceDynamicCheck)
	{
	    return deviceDynamicCheckMapper.insertDeviceDynamicCheck(deviceDynamicCheck);
	}
	
	/**
     * 修改遥测设备动态检查过程数据
     * 
     * @param deviceDynamicCheck 遥测设备动态检查过程数据信息
     * @return 结果
     */
	@Override
	public int updateDeviceDynamicCheck(DeviceDynamicCheck deviceDynamicCheck)
	{
	    return deviceDynamicCheckMapper.updateDeviceDynamicCheck(deviceDynamicCheck);
	}

	/**
     * 删除遥测设备动态检查过程数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceDynamicCheckByIds(String ids)
	{
		return deviceDynamicCheckMapper.deleteDeviceDynamicCheckByIds(Convert.toStrArray(ids));
	}
	
}
