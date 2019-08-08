package com.ruoyi.yaogan.service;

import com.ruoyi.yaogan.domain.VehicleInfo;

import java.util.List;

/**
 * 车辆数据 服务层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface IVehicleInfoService 
{
	/**
     * 查询车辆数据信息
     * 
     * @param iD 车辆数据ID
     * @return 车辆数据信息
     */
	public VehicleInfo selectVehicleInfoById(Integer iD);
	
	/**
     * 查询车辆数据列表
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 车辆数据集合
     */
	public List<VehicleInfo> selectVehicleInfoList(VehicleInfo vehicleInfo);
	
	/**
     * 新增车辆数据
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 结果
     */
	public int insertVehicleInfo(VehicleInfo vehicleInfo);
	
	/**
     * 修改车辆数据
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 结果
     */
	public int updateVehicleInfo(VehicleInfo vehicleInfo);
		
	/**
     * 删除车辆数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleInfoByIds(String ids);
	
}
