package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.StationCheckLog;
import com.ruoyi.yaogan.mapper.StationCheckLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 遥测设备自检 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class StationCheckLogServiceImpl implements IStationCheckLogService 
{
	@Autowired
	private StationCheckLogMapper stationCheckLogMapper;

	/**
     * 查询遥测设备自检信息
     * 
     * @param iD 遥测设备自检ID
     * @return 遥测设备自检信息
     */
    @Override
	public StationCheckLog selectStationCheckLogById(Integer iD)
	{
	    return stationCheckLogMapper.selectStationCheckLogById(iD);
	}
	
	/**
     * 查询遥测设备自检列表
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 遥测设备自检集合
     */
	@Override
	public List<StationCheckLog> selectStationCheckLogList(StationCheckLog stationCheckLog)
	{
	    return stationCheckLogMapper.selectStationCheckLogList(stationCheckLog);
	}
	
    /**
     * 新增遥测设备自检
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 结果
     */
	@Override
	public int insertStationCheckLog(StationCheckLog stationCheckLog)
	{
	    return stationCheckLogMapper.insertStationCheckLog(stationCheckLog);
	}
	
	/**
     * 修改遥测设备自检
     * 
     * @param stationCheckLog 遥测设备自检信息
     * @return 结果
     */
	@Override
	public int updateStationCheckLog(StationCheckLog stationCheckLog)
	{
	    return stationCheckLogMapper.updateStationCheckLog(stationCheckLog);
	}

	/**
     * 删除遥测设备自检对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStationCheckLogByIds(String ids)
	{
		return stationCheckLogMapper.deleteStationCheckLogByIds(Convert.toStrArray(ids));
	}
	
}
