package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.VehicleTrajectory;

import java.util.List;

/**
 * 机动车轨迹 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface VehicleTrajectoryMapper 
{
	/**
     * 查询机动车轨迹信息
     * 
     * @param gJXXBH 机动车轨迹ID
     * @return 机动车轨迹信息
     */
	public VehicleTrajectory selectVehicleTrajectoryById(String gJXXBH);
	
	/**
     * 查询机动车轨迹列表
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 机动车轨迹集合
     */
	public List<VehicleTrajectory> selectVehicleTrajectoryList(VehicleTrajectory vehicleTrajectory);
	
	/**
     * 新增机动车轨迹
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 结果
     */
	public int insertVehicleTrajectory(VehicleTrajectory vehicleTrajectory);
	
	/**
     * 修改机动车轨迹
     * 
     * @param vehicleTrajectory 机动车轨迹信息
     * @return 结果
     */
	public int updateVehicleTrajectory(VehicleTrajectory vehicleTrajectory);
	
	/**
     * 删除机动车轨迹
     * 
     * @param gJXXBH 机动车轨迹ID
     * @return 结果
     */
	public int deleteVehicleTrajectoryById(String gJXXBH);
	
	/**
     * 批量删除机动车轨迹
     * 
     * @param gJXXBHs 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleTrajectoryByIds(String[] gJXXBHs);
	
}