package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.AirQualityRecord;
import com.ruoyi.yaogan.mapper.AirQualityRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点位环境空气质量记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class AirQualityRecordServiceImpl implements IAirQualityRecordService 
{
	@Autowired
	private AirQualityRecordMapper airQualityRecordMapper;

	/**
     * 查询点位环境空气质量记录信息
     * 
     * @param jLSJ 点位环境空气质量记录ID
     * @return 点位环境空气质量记录信息
     */
    @Override
	public AirQualityRecord selectAirQualityRecordById(String jLSJ)
	{
	    return airQualityRecordMapper.selectAirQualityRecordById(jLSJ);
	}
	
	/**
     * 查询点位环境空气质量记录列表
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 点位环境空气质量记录集合
     */
	@Override
	public List<AirQualityRecord> selectAirQualityRecordList(AirQualityRecord airQualityRecord)
	{
	    return airQualityRecordMapper.selectAirQualityRecordList(airQualityRecord);
	}
	
    /**
     * 新增点位环境空气质量记录
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 结果
     */
	@Override
	public int insertAirQualityRecord(AirQualityRecord airQualityRecord)
	{
	    return airQualityRecordMapper.insertAirQualityRecord(airQualityRecord);
	}
	
	/**
     * 修改点位环境空气质量记录
     * 
     * @param airQualityRecord 点位环境空气质量记录信息
     * @return 结果
     */
	@Override
	public int updateAirQualityRecord(AirQualityRecord airQualityRecord)
	{
	    return airQualityRecordMapper.updateAirQualityRecord(airQualityRecord);
	}

	/**
     * 删除点位环境空气质量记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAirQualityRecordByIds(String ids)
	{
		return airQualityRecordMapper.deleteAirQualityRecordByIds(Convert.toStrArray(ids));
	}
	
}
