package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.MobileStation;
import com.ruoyi.yaogan.mapper.MobileStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动式点位运行记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class MobileStationServiceImpl implements IMobileStationService 
{
	@Autowired
	private MobileStationMapper mobileStationMapper;

	/**
     * 查询移动式点位运行记录信息
     * 
     * @param iD 移动式点位运行记录ID
     * @return 移动式点位运行记录信息
     */
    @Override
	public MobileStation selectMobileStationById(Integer iD)
	{
	    return mobileStationMapper.selectMobileStationById(iD);
	}
	
	/**
     * 查询移动式点位运行记录列表
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 移动式点位运行记录集合
     */
	@Override
	public List<MobileStation> selectMobileStationList(MobileStation mobileStation)
	{
	    return mobileStationMapper.selectMobileStationList(mobileStation);
	}
	
    /**
     * 新增移动式点位运行记录
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 结果
     */
	@Override
	public int insertMobileStation(MobileStation mobileStation)
	{
	    return mobileStationMapper.insertMobileStation(mobileStation);
	}
	
	/**
     * 修改移动式点位运行记录
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 结果
     */
	@Override
	public int updateMobileStation(MobileStation mobileStation)
	{
	    return mobileStationMapper.updateMobileStation(mobileStation);
	}

	/**
     * 删除移动式点位运行记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMobileStationByIds(String ids)
	{
		return mobileStationMapper.deleteMobileStationByIds(Convert.toStrArray(ids));
	}
	
}
