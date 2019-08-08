package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.Station;

import java.util.List;

/**
 * 点位 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface StationMapper 
{
	/**
     * 查询点位信息
     * 
     * @param dWBH 点位ID
     * @return 点位信息
     */
	public Station selectStationById(String dWBH);
	
	/**
     * 查询点位列表
     * 
     * @param station 点位信息
     * @return 点位集合
     */
	public List<Station> selectStationList(Station station);
	
	/**
     * 新增点位
     * 
     * @param station 点位信息
     * @return 结果
     */
	public int insertStation(Station station);
	
	/**
     * 修改点位
     * 
     * @param station 点位信息
     * @return 结果
     */
	public int updateStation(Station station);
	
	/**
     * 删除点位
     * 
     * @param dWBH 点位ID
     * @return 结果
     */
	public int deleteStationById(String dWBH);
	
	/**
     * 批量删除点位
     * 
     * @param dWBHs 需要删除的数据ID
     * @return 结果
     */
	public int deleteStationByIds(String[] dWBHs);
	
}