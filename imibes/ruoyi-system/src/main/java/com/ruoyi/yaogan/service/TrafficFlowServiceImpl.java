package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.TrafficFlow;
import com.ruoyi.yaogan.mapper.TrafficFlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交通流量 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class TrafficFlowServiceImpl implements ITrafficFlowService 
{
	@Autowired
	private TrafficFlowMapper trafficFlowMapper;

	/**
     * 查询交通流量信息
     * 
     * @param dWBH 交通流量ID
     * @return 交通流量信息
     */
    @Override
	public TrafficFlow selectTrafficFlowById(String dWBH)
	{
	    return trafficFlowMapper.selectTrafficFlowById(dWBH);
	}
	
	/**
     * 查询交通流量列表
     * 
     * @param trafficFlow 交通流量信息
     * @return 交通流量集合
     */
	@Override
	public List<TrafficFlow> selectTrafficFlowList(TrafficFlow trafficFlow)
	{
	    return trafficFlowMapper.selectTrafficFlowList(trafficFlow);
	}
	
    /**
     * 新增交通流量
     * 
     * @param trafficFlow 交通流量信息
     * @return 结果
     */
	@Override
	public int insertTrafficFlow(TrafficFlow trafficFlow)
	{
	    return trafficFlowMapper.insertTrafficFlow(trafficFlow);
	}
	
	/**
     * 修改交通流量
     * 
     * @param trafficFlow 交通流量信息
     * @return 结果
     */
	@Override
	public int updateTrafficFlow(TrafficFlow trafficFlow)
	{
	    return trafficFlowMapper.updateTrafficFlow(trafficFlow);
	}

	/**
     * 删除交通流量对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTrafficFlowByIds(String ids)
	{
		return trafficFlowMapper.deleteTrafficFlowByIds(Convert.toStrArray(ids));
	}
	
}
