package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.VehicleTrajectory;
import com.ruoyi.yaogan.mapper.VehicleTrajectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机动车轨迹 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class VehicleTrajectoryServiceImpl implements IVehicleTrajectoryService 
{
	@Autowired
	private VehicleTrajectoryMapper vehicleTrajectoryMapper;

	/**
     * 查询机动车轨迹信息
     * 
     * @param gJXXBH 机动车轨迹ID
     * @return 机动车轨迹信息
     */
    @Override
	public VehicleTrajectory selectVehicleTrajectoryById(String gJXXBH)
	{
	    return vehicleTrajectoryMapper.selectVehicleTrajectoryById(gJXXBH);
	}
	
	/**
     * 查询机动车轨迹列表
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 机动车轨迹集合
     */
	@Override
	public List<VehicleTrajectory> selectVehicleTrajectoryList(VehicleTrajectory vehicleTrajectory)
	{
	    return vehicleTrajectoryMapper.selectVehicleTrajectoryList(vehicleTrajectory);
	}
	
    /**
     * 新增机动车轨迹
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 结果
     */
	@Override
	public int insertVehicleTrajectory(VehicleTrajectory vehicleTrajectory)
	{
	    return vehicleTrajectoryMapper.insertVehicleTrajectory(vehicleTrajectory);
	}
	
	/**
     * 修改机动车轨迹
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 结果
     */
	@Override
	public int updateVehicleTrajectory(VehicleTrajectory vehicleTrajectory)
	{
	    return vehicleTrajectoryMapper.updateVehicleTrajectory(vehicleTrajectory);
	}

	/**
     * 删除机动车轨迹对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVehicleTrajectoryByIds(String ids)
	{
		return vehicleTrajectoryMapper.deleteVehicleTrajectoryByIds(Convert.toStrArray(ids));
	}
	
}
