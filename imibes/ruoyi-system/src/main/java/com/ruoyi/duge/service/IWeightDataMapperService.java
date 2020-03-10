package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.CarOut;
import com.ruoyi.duge.domain.WeightData;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

	public com.ruoyi.duge.domain.WeightData selectLast(com.ruoyi.duge.domain.WeightData data);
	
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
	List<WeightData> selectIllegalAnd2YearAgo();
	List<WeightData> selectTruckNumberByTime();
	Integer selectByTruckNumber(Date weightingDate ,String truckNumber);
	List<WeightData> selectTruckNumberOver90Date();
	List<WeightData> selectByTruckNumberOver90Date(Date weightingDate ,String truckNum);
	List<WeightData> selectBefore40Days();
	public int insertIntoWeightDataBefore40Days(WeightData weightData);

	/**
	 * 查询过车统计
	 */
	List<CarOut> selectCarOutNumber(@Param("stationId")int stationId,@Param("startDate")Date startDate,
									@Param("endDate")Date endDate
									);


	/**
	 * 查询过车超重统计
	 */
	List<CarOut> selectCarOverWeight(@Param("stationId")int stationId,@Param("startDate")Date startDate,
									@Param("endDate")Date endDate
									);

	void updateWeightDataBefore40Days(WeightData weightData);
	/**
	 * 查询是否存在
	 */
    boolean checkIsExist(Long id);

    List<CarOut> stationDaily(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	List<CarOut> stationDailyOverWeight(@Param("date") Date date);

	//统计易华录
	List<CarOut> sendYihualu(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
