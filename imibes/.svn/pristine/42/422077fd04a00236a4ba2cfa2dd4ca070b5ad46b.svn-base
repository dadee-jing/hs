package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.DeviceStaticCheck;
import com.ruoyi.yaogan.mapper.DeviceStaticCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥测设备静态检查过程数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class DeviceStaticCheckServiceImpl implements IDeviceStaticCheckService 
{
	@Autowired
	private DeviceStaticCheckMapper deviceStaticCheckMapper;

	/**
     * 查询遥测设备静态检查过程数据信息
     * 
     * @param iD 遥测设备静态检查过程数据ID
     * @return 遥测设备静态检查过程数据信息
     */
    @Override
	public DeviceStaticCheck selectDeviceStaticCheckById(Integer iD)
	{
	    return deviceStaticCheckMapper.selectDeviceStaticCheckById(iD);
	}
	
	/**
     * 查询遥测设备静态检查过程数据列表
     * 
     * @param deviceStaticCheck 遥测设备静态检查过程数据信息
     * @return 遥测设备静态检查过程数据集合
     */
	@Override
	public List<DeviceStaticCheck> selectDeviceStaticCheckList(DeviceStaticCheck deviceStaticCheck)
	{
	    return deviceStaticCheckMapper.selectDeviceStaticCheckList(deviceStaticCheck);
	}
	
    /**
     * 新增遥测设备静态检查过程数据
     * 
     * @param deviceStaticCheck 遥测设备静态检查过程数据信息
     * @return 结果
     */
	@Override
	public int insertDeviceStaticCheck(DeviceStaticCheck deviceStaticCheck)
	{
	    return deviceStaticCheckMapper.insertDeviceStaticCheck(deviceStaticCheck);
	}
	
	/**
     * 修改遥测设备静态检查过程数据
     * 
     * @param deviceStaticCheck 遥测设备静态检查过程数据信息
     * @return 结果
     */
	@Override
	public int updateDeviceStaticCheck(DeviceStaticCheck deviceStaticCheck)
	{
	    return deviceStaticCheckMapper.updateDeviceStaticCheck(deviceStaticCheck);
	}

	/**
     * 删除遥测设备静态检查过程数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceStaticCheckByIds(String ids)
	{
		return deviceStaticCheckMapper.deleteDeviceStaticCheckByIds(Convert.toStrArray(ids));
	}
	
}
