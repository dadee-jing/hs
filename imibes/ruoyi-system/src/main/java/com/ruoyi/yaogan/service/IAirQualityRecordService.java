package com.ruoyi.yaogan.service;

import com.ruoyi.yaogan.domain.AirQualityRecord;

import java.util.List;

/**
 * 点位环境空气质量记录 服务层
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public interface IAirQualityRecordService 
{
	/**
     * 查询点位环境空气质量记录信息
     * 
     * @param jLSJ 点位环境空气质量记录ID
     * @return 点位环境空气质量记录信息
     */
	public AirQualityRecord selectAirQualityRecordById(String jLSJ);
	
	/**
     * 查询点位环境空气质量记录列表
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 点位环境空气质量记录集合
     */
	public List<AirQualityRecord> selectAirQualityRecordList(AirQualityRecord airQualityRecord);
	
	/**
     * 新增点位环境空气质量记录
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 结果
     */
	public int insertAirQualityRecord(AirQualityRecord airQualityRecord);
	
	/**
     * 修改点位环境空气质量记录
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 结果
     */
	public int updateAirQualityRecord(AirQualityRecord airQualityRecord);
		
	/**
     * 删除点位环境空气质量记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAirQualityRecordByIds(String ids);
	
}
