package com.ruoyi.duge.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.duge.domain.MaintenanceRecord;
import com.ruoyi.duge.mapper.MaintenanceRecordMapper;
import com.ruoyi.duge.service.IMaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点维护记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
@Service
public class MaintenanceRecordServiceImpl implements IMaintenanceRecordService
{
	@Autowired
	private MaintenanceRecordMapper maintenanceRecordMapper;

	/**
     * 查询站点维护记录信息
     * 
     * @param id 站点维护记录ID
     * @return 站点维护记录信息
     */
    @Override
	public MaintenanceRecord selectMaintenanceRecordById(Integer id)
	{
	    return maintenanceRecordMapper.selectMaintenanceRecordById(id);
	}
	
	/**
     * 查询站点维护记录列表
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 站点维护记录集合
     */
	@Override
	public List<MaintenanceRecord> selectMaintenanceRecordList(MaintenanceRecord maintenanceRecord)
	{
	    return maintenanceRecordMapper.selectMaintenanceRecordList(maintenanceRecord);
	}
	
    /**
     * 新增站点维护记录
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 结果
     */
	@Override
	public int insertMaintenanceRecord(MaintenanceRecord maintenanceRecord)
	{
	    return maintenanceRecordMapper.insertMaintenanceRecord(maintenanceRecord);
	}
	
	/**
     * 修改站点维护记录
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 结果
     */
	@Override
	public int updateMaintenanceRecord(MaintenanceRecord maintenanceRecord)
	{
	    return maintenanceRecordMapper.updateMaintenanceRecord(maintenanceRecord);
	}

	/**
     * 删除站点维护记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMaintenanceRecordByIds(String ids)
	{
		return maintenanceRecordMapper.deleteMaintenanceRecordByIds(Convert.toStrArray(ids));
	}
	
}
