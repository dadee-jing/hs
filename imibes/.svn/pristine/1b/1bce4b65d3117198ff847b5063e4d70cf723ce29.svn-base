package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.Station;
import com.ruoyi.yaogan.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点位 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class StationServiceImpl implements IStationService 
{
	@Autowired
	private StationMapper stationMapper;

	/**
     * 查询点位信息
     * 
     * @param dWBH 点位ID
     * @return 点位信息
     */
    @Override
	public Station selectStationById(String dWBH)
	{
	    return stationMapper.selectStationById(dWBH);
	}
	
	/**
     * 查询点位列表
     * 
     * @param station 点位信息
     * @return 点位集合
     */
	@Override
	public List<Station> selectStationList(Station station)
	{
	    return stationMapper.selectStationList(station);
	}
	
    /**
     * 新增点位
     * 
     * @param station 点位信息
     * @return 结果
     */
	@Override
	public int insertStation(Station station)
	{
	    return stationMapper.insertStation(station);
	}
	
	/**
     * 修改点位
     * 
     * @param station 点位信息
     * @return 结果
     */
	@Override
	public int updateStation(Station station)
	{
	    return stationMapper.updateStation(station);
	}

	/**
     * 删除点位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStationByIds(String ids)
	{
		return stationMapper.deleteStationByIds(Convert.toStrArray(ids));
	}
	
}
