package com.ruoyi.duge.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.mapper.StationInfoMapper;
import com.ruoyi.duge.service.IStationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点 服务层实现
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Service
public class StationInfoServiceImpl implements IStationInfoService 
{
	@Autowired
	private StationInfoMapper stationInfoMapper;

	/**
     * 查询站点信息
     * 
     * @param id 站点ID
     * @return 站点信息
     */
    @Override
	public StationInfo selectStationInfoById(Integer id, String laneMid)
	{
	    return stationInfoMapper.selectStationInfoById(id,laneMid);
	}
	
	/**
     * 查询站点列表
     * 
     * @param stationInfo 站点信息
     * @return 站点集合
     */
	@Override
	public List<StationInfo> selectStationInfoList(StationInfo stationInfo)
	{
	    return stationInfoMapper.selectStationInfoList(stationInfo);
	}
	
    /**
     * 新增站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     */
	@Override
	public int insertStationInfo(StationInfo stationInfo)
	{
	    return stationInfoMapper.insertStationInfo(stationInfo);
	}
	
	/**
     * 修改站点
     * 
     * @param stationInfo 站点信息
     * @return 结果
     */
	@Override
	public int updateStationInfo(StationInfo stationInfo)
	{
	    return stationInfoMapper.updateStationInfo(stationInfo);
	}

	/**
     * 删除站点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStationInfoByIds(String ids)
	{
		return stationInfoMapper.deleteStationInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<StationInfo> selectStationStateInfo() {
		return stationInfoMapper.selectStationStateInfo();
	}

	@Override
	public String getStationLatesCarRecordTime(Integer stationId){
		return stationInfoMapper.getStationLatesCarRecordTime(stationId);
	}

	@Override
	public String getRecentTime(Integer stationId){
		return stationInfoMapper.getRecentTime(stationId);
	}
	@Override
	public StationInfo selectStationInfoByIdNoLane(Integer stationId){
		return stationInfoMapper.selectStationInfoByIdNoLane(stationId);
	}
}
