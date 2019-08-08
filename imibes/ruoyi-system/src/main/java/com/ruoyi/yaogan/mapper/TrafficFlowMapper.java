package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.TrafficFlow;

import java.util.List;

/**
 * 交通流量 数据层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface TrafficFlowMapper 
{
	/**
     * 查询交通流量信息
     * 
     * @param dWBH 交通流量ID
     * @return 交通流量信息
     */
	public TrafficFlow selectTrafficFlowById(String dWBH);
	
	/**
     * 查询交通流量列表
     * 
     * @param trafficFlow 交通流量信息
     * @return 交通流量集合
     */
	public List<TrafficFlow> selectTrafficFlowList(TrafficFlow trafficFlow);
	
	/**
     * 新增交通流量
     * 
     * @param trafficFlow 交通流量信息
     * @return 结果
     */
	public int insertTrafficFlow(TrafficFlow trafficFlow);
	
	/**
     * 修改交通流量
     * 
     * @param trafficFlow 交通流量信息
     * @return 结果
     */
	public int updateTrafficFlow(TrafficFlow trafficFlow);
	
	/**
     * 删除交通流量
     * 
     * @param dWBH 交通流量ID
     * @return 结果
     */
	public int deleteTrafficFlowById(String dWBH);
	
	/**
     * 批量删除交通流量
     * 
     * @param dWBHs 需要删除的数据ID
     * @return 结果
     */
	public int deleteTrafficFlowByIds(String[] dWBHs);
	
}