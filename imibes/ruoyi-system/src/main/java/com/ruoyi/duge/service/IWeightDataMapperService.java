package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.WeightData;

import java.util.List;
import java.util.Map;

/**
 * 重量数据 服务层
 * 
 * @author ruoyi
 * @date 2018-11-20
 */
public interface IWeightDataMapperService
{
	/**
     * 查询重量数据信息
     * 
     * @param id 重量数据ID
     * @return 重量数据信息
     */
	public com.ruoyi.duge.domain.WeightData selectDataById(Long id);

	public com.ruoyi.duge.domain.WeightData selectLast();
	
	/**
     * 查询重量数据列表
     * 
     * @param data 重量数据信息
     * @return 重量数据集合
     */
	public List<com.ruoyi.duge.domain.WeightData> selectDataList(com.ruoyi.duge.domain.WeightData data);

	public List<com.ruoyi.duge.domain.WeightData> selectNotUploadDataList();
	
	/**
     * 新增重量数据
     * 
     * @param data 重量数据信息
     * @return 结果
     */
	public int insertData(com.ruoyi.duge.domain.WeightData data);
	
	/**
     * 修改重量数据
     * 
     * @param data 重量数据信息
     * @return 结果
     */
	public int updateData(com.ruoyi.duge.domain.WeightData data);
		
	/**
     * 删除重量数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDataByIds(String ids);

	public Map selectStationStatistics(com.ruoyi.duge.domain.WeightData data);

	List<WeightData> selectNotUploadJj();
	List<WeightData> selectNotUploadSj();
	/**
	 * 查询违法并且过时的数据
	 * @return
	 */
	List<WeightData> selectIllegalAndOverDate();
	List<String> selectTruckNumberByTime();
	List<WeightData> selectIsIllegalByTruckNumber(String truckNumber);
	List<WeightData> selectByTruckNumber(String truckNumber);
	List<String> selectTruckNumberOver90Date();
	List<WeightData> selectByTruckNumberOver90Date(String truckNum);
}
