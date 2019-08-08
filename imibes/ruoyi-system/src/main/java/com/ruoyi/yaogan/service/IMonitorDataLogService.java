package com.ruoyi.yaogan.service;

import com.ruoyi.yaogan.domain.MonitorDataLog;

import java.util.List;

/**
 * 遥感监测数据 服务层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface IMonitorDataLogService 
{
	/**
     * 查询遥感监测数据信息
     * 
     * @param jLBH 遥感监测数据ID
     * @return 遥感监测数据信息
     */
	public MonitorDataLog selectMonitorDataLogById(String jLBH);
	
	/**
     * 查询遥感监测数据列表
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 遥感监测数据集合
     */
	public List<MonitorDataLog> selectMonitorDataLogList(MonitorDataLog monitorDataLog);
	
	/**
     * 新增遥感监测数据
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 结果
     */
	public int insertMonitorDataLog(MonitorDataLog monitorDataLog);
	
	/**
     * 修改遥感监测数据
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 结果
     */
	public int updateMonitorDataLog(MonitorDataLog monitorDataLog);
		
	/**
     * 删除遥感监测数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMonitorDataLogByIds(String ids);
	
}
