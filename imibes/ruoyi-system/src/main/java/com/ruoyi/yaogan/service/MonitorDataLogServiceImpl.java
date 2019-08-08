package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.MonitorDataLog;
import com.ruoyi.yaogan.mapper.MonitorDataLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥感监测数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class MonitorDataLogServiceImpl implements IMonitorDataLogService 
{
	@Autowired
	private MonitorDataLogMapper monitorDataLogMapper;

	/**
     * 查询遥感监测数据信息
     * 
     * @param jLBH 遥感监测数据ID
     * @return 遥感监测数据信息
     */
    @Override
	public MonitorDataLog selectMonitorDataLogById(String jLBH)
	{
	    return monitorDataLogMapper.selectMonitorDataLogById(jLBH);
	}
	
	/**
     * 查询遥感监测数据列表
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 遥感监测数据集合
     */
	@Override
	public List<MonitorDataLog> selectMonitorDataLogList(MonitorDataLog monitorDataLog)
	{
	    return monitorDataLogMapper.selectMonitorDataLogList(monitorDataLog);
	}
	
    /**
     * 新增遥感监测数据
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 结果
     */
	@Override
	public int insertMonitorDataLog(MonitorDataLog monitorDataLog)
	{
	    return monitorDataLogMapper.insertMonitorDataLog(monitorDataLog);
	}
	
	/**
     * 修改遥感监测数据
     * 
     * @param monitorDataLog 遥感监测数据信息
     * @return 结果
     */
	@Override
	public int updateMonitorDataLog(MonitorDataLog monitorDataLog)
	{
	    return monitorDataLogMapper.updateMonitorDataLog(monitorDataLog);
	}

	/**
     * 删除遥感监测数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMonitorDataLogByIds(String ids)
	{
		return monitorDataLogMapper.deleteMonitorDataLogByIds(Convert.toStrArray(ids));
	}
	
}
