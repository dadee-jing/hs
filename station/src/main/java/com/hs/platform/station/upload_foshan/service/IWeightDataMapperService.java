package com.hs.platform.station.upload_foshan.service;




import com.hs.platform.station.upload_foshan.domaim.WeightData;

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
	public WeightData selectDataById(Long id);

	public WeightData selectLast();
	
	/**
     * 查询重量数据列表
     * 
     * @param data 重量数据信息
     * @return 重量数据集合
     */
	public List<WeightData> selectDataList(WeightData data);

	public List<WeightData> selectNotUploadDataList();
	
	/**
     * 新增重量数据
     * 
     * @param data 重量数据信息
     * @return 结果
     */
	public int insertData(WeightData data);
	
	/**
     * 修改重量数据
     * 
     * @param data 重量数据信息
     * @return 结果
     */
	public int updateData(WeightData data);
		
	/**
     * 删除重量数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//	public int deleteDataByIds(String ids);

	public Map selectStationStatistics(WeightData data);

	List<WeightData> selectNotUploadSj();
}
