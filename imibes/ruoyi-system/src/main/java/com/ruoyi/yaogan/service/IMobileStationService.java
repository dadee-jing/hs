package com.ruoyi.yaogan.service;

import com.ruoyi.yaogan.domain.MobileStation;

import java.util.List;

/**
 * 移动式点位运行记录 服务层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface IMobileStationService 
{
	/**
     * 查询移动式点位运行记录信息
     * 
     * @param iD 移动式点位运行记录ID
     * @return 移动式点位运行记录信息
     */
	public MobileStation selectMobileStationById(Integer iD);
	
	/**
     * 查询移动式点位运行记录列表
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 移动式点位运行记录集合
     */
	public List<MobileStation> selectMobileStationList(MobileStation mobileStation);
	
	/**
     * 新增移动式点位运行记录
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 结果
     */
	public int insertMobileStation(MobileStation mobileStation);
	
	/**
     * 修改移动式点位运行记录
     * 
     * @param mobileStation 移动式点位运行记录信息
     * @return 结果
     */
	public int updateMobileStation(MobileStation mobileStation);
		
	/**
     * 删除移动式点位运行记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMobileStationByIds(String ids);
	
}
