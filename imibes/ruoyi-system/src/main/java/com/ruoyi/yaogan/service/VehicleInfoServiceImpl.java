package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.VehicleInfo;
import com.ruoyi.yaogan.mapper.VehicleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class VehicleInfoServiceImpl implements IVehicleInfoService 
{
	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;

	/**
     * 查询车辆数据信息
     * 
     * @param iD 车辆数据ID
     * @return 车辆数据信息
     */
    @Override
	public VehicleInfo selectVehicleInfoById(Integer iD)
	{
	    return vehicleInfoMapper.selectVehicleInfoById(iD);
	}
	
	/**
     * 查询车辆数据列表
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 车辆数据集合
     */
	@Override
	public List<VehicleInfo> selectVehicleInfoList(VehicleInfo vehicleInfo)
	{
	    return vehicleInfoMapper.selectVehicleInfoList(vehicleInfo);
	}
	
    /**
     * 新增车辆数据
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 结果
     */
	@Override
	public int insertVehicleInfo(VehicleInfo vehicleInfo)
	{
	    return vehicleInfoMapper.insertVehicleInfo(vehicleInfo);
	}
	
	/**
     * 修改车辆数据
     * 
     * @param vehicleInfo 车辆数据信息
     * @return 结果
     */
	@Override
	public int updateVehicleInfo(VehicleInfo vehicleInfo)
	{
	    return vehicleInfoMapper.updateVehicleInfo(vehicleInfo);
	}

	/**
     * 删除车辆数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVehicleInfoByIds(String ids)
	{
		return vehicleInfoMapper.deleteVehicleInfoByIds(Convert.toStrArray(ids));
	}
	
}
