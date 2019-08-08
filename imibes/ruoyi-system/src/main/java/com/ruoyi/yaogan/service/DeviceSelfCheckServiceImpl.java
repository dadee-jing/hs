package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.DeviceSelfCheck;
import com.ruoyi.yaogan.mapper.DeviceSelfCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥测设备自检过程数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class DeviceSelfCheckServiceImpl implements IDeviceSelfCheckService 
{
	@Autowired
	private DeviceSelfCheckMapper deviceSelfCheckMapper;

	/**
     * 查询遥测设备自检过程数据信息
     * 
     * @param iD 遥测设备自检过程数据ID
     * @return 遥测设备自检过程数据信息
     */
    @Override
	public DeviceSelfCheck selectDeviceSelfCheckById(Integer iD)
	{
	    return deviceSelfCheckMapper.selectDeviceSelfCheckById(iD);
	}
	
	/**
     * 查询遥测设备自检过程数据列表
     * 
     * @param deviceSelfCheck 遥测设备自检过程数据信息
     * @return 遥测设备自检过程数据集合
     */
	@Override
	public List<DeviceSelfCheck> selectDeviceSelfCheckList(DeviceSelfCheck deviceSelfCheck)
	{
	    return deviceSelfCheckMapper.selectDeviceSelfCheckList(deviceSelfCheck);
	}
	
    /**
     * 新增遥测设备自检过程数据
     * 
     * @param deviceSelfCheck 遥测设备自检过程数据信息
     * @return 结果
     */
	@Override
	public int insertDeviceSelfCheck(DeviceSelfCheck deviceSelfCheck)
	{
	    return deviceSelfCheckMapper.insertDeviceSelfCheck(deviceSelfCheck);
	}
	
	/**
     * 修改遥测设备自检过程数据
     * 
     * @param deviceSelfCheck 遥测设备自检过程数据信息
     * @return 结果
     */
	@Override
	public int updateDeviceSelfCheck(DeviceSelfCheck deviceSelfCheck)
	{
	    return deviceSelfCheckMapper.updateDeviceSelfCheck(deviceSelfCheck);
	}

	/**
     * 删除遥测设备自检过程数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceSelfCheckByIds(String ids)
	{
		return deviceSelfCheckMapper.deleteDeviceSelfCheckByIds(Convert.toStrArray(ids));
	}
	
}
