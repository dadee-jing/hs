package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.Line;

import java.util.List;

/**
 * 点位遥测线 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface LineMapper 
{
	/**
     * 查询点位遥测线信息
     * 
     * @param iD 点位遥测线ID
     * @return 点位遥测线信息
     */
	public Line selectLineById(Integer iD);
	
	/**
     * 查询点位遥测线列表
     * 
     * @param line 点位遥测线信息
     * @return 点位遥测线集合
     */
	public List<Line> selectLineList(Line line);
	
	/**
     * 新增点位遥测线
     * 
     * @param line 点位遥测线信息
     * @return 结果
     */
	public int insertLine(Line line);
	
	/**
     * 修改点位遥测线
     * 
     * @param line 点位遥测线信息
     * @return 结果
     */
	public int updateLine(Line line);
	
	/**
     * 删除点位遥测线
     * 
     * @param iD 点位遥测线ID
     * @return 结果
     */
	public int deleteLineById(Integer iD);
	
	/**
     * 批量删除点位遥测线
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteLineByIds(String[] iDs);
	
}