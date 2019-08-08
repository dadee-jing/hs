package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.DeviceCheckLog;
import com.ruoyi.yaogan.mapper.DeviceCheckLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥测设备检查 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class DeviceCheckLogServiceImpl implements IDeviceCheckLogService 
{
	@Autowired
	private DeviceCheckLogMapper deviceCheckLogMapper;

	/**
     * 查询遥测设备检查信息
     * 
     * @param iD 遥测设备检查ID
     * @return 遥测设备检查信息
     */
    @Override
	public DeviceCheckLog selectDeviceCheckLogById(Integer iD)
	{
	    return deviceCheckLogMapper.selectDeviceCheckLogById(iD);
	}
	
	/**
     * 查询遥测设备检查列表
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 遥测设备检查集合
     */
	@Override
	public List<DeviceCheckLog> selectDeviceCheckLogList(DeviceCheckLog deviceCheckLog)
	{
	    return deviceCheckLogMapper.selectDeviceCheckLogList(deviceCheckLog);
	}
	
    /**
     * 新增遥测设备检查
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 结果
     */
	@Override
	public int insertDeviceCheckLog(DeviceCheckLog deviceCheckLog)
	{
	    return deviceCheckLogMapper.insertDeviceCheckLog(deviceCheckLog);
	}
	
	/**
     * 修改遥测设备检查
     * 
     * @param deviceCheckLog 遥测设备检查信息
     * @return 结果
     */
	@Override
	public int updateDeviceCheckLog(DeviceCheckLog deviceCheckLog)
	{
	    return deviceCheckLogMapper.updateDeviceCheckLog(deviceCheckLog);
	}

	/**
     * 删除遥测设备检查对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceCheckLogByIds(String ids)
	{
		return deviceCheckLogMapper.deleteDeviceCheckLogByIds(Convert.toStrArray(ids));
	}
	
}
