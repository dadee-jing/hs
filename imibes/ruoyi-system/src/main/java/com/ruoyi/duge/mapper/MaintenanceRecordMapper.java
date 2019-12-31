package com.ruoyi.duge.mapper;


import com.ruoyi.duge.domain.MaintenanceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站点维护记录 数据层
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
public interface MaintenanceRecordMapper 
{
	/**
     * 查询站点维护记录信息
     * 
     * @param id 站点维护记录ID
     * @return 站点维护记录信息
     */
	public MaintenanceRecord selectMaintenanceRecordById(Integer id);
	
	/**
     * 查询站点维护记录列表
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 站点维护记录集合
     */
	public List<MaintenanceRecord> selectMaintenanceRecordList(MaintenanceRecord maintenanceRecord);
	
	/**
     * 新增站点维护记录
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 结果
     */
	public int insertMaintenanceRecord(MaintenanceRecord maintenanceRecord);
	
	/**
     * 修改站点维护记录
     * 
     * @param maintenanceRecord 站点维护记录信息
     * @return 结果
     */
	public int updateMaintenanceRecord(MaintenanceRecord maintenanceRecord);
	
	/**
     * 删除站点维护记录
     * 
     * @param id 站点维护记录ID
     * @return 结果
     */
	public int deleteMaintenanceRecordById(Integer id);
	
	/**
     * 批量删除站点维护记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaintenanceRecordByIds(String[] ids);

    List<MaintenanceRecord> selectMaintenanceRecordListByStationId(@Param("stationId") Integer stationId);

    Integer getStationIdByRecordId(@Param("id") Integer id);

    Integer getTodayRecordCount();

}