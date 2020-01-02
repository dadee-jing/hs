package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.CarOut;
import com.ruoyi.duge.domain.WeightData;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 重量数据 数据层
 *
 * @author ruoyi
 * @date 2018-11-20
 */
public interface WeightDataMapper {
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

    public List<com.ruoyi.duge.domain.WeightData> selectNotUpload();

    public List<com.ruoyi.duge.domain.WeightData> selectNotUploadYhl();

    public List<com.ruoyi.duge.domain.WeightData> selectNotUploadSj();

    public List<com.ruoyi.duge.domain.WeightData> selectNotUploadJj();

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
     * 删除重量数据
     *
     * @param id 重量数据ID
     * @return 结果
     */
    public int deleteDataById(Long id);

    /**
     * 批量删除重量数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataByIds(String[] ids);

    public Map selectStationStatistics(com.ruoyi.duge.domain.WeightData data);

    public List<Map<String, Object>> selectCountHourList(com.ruoyi.duge.domain.WeightData data);

    public Double selectSpeedAvg(com.ruoyi.duge.domain.WeightData data);

    /**报警图片视频数据*/
    public List<WeightData> selectIllegalAnd2YearAgo();
    /**正常图片视频数据*/
    List<WeightData> selectTruckNumberByTime();
    Integer selectByTruckNumber(@Param("weightingDate")Date weightingDate ,@Param("truckNumber")String truckNumber);

   /**报警车辆对应其过车数据*/
    List<WeightData> selectTruckNumberOver90Date();
    List<WeightData> selectByTruckNumberOver90Date(@Param("weightingDate")Date weightingDate ,@Param("truckNumber")String truckNumber);

    List<WeightData> selectBefore40Days();
    public int insertIntoWeightDataBefore40Days(WeightData weightData);
    void updateWeightDataBefore40Days(WeightData weightData);
    /**
	 * 查询过车统计
	 */
	List<CarOut> selectCarOutNumber(@Param("stationId")int stationId,@Param("startDate")Date startDate,
                                    @Param("endDate")Date endDate);

    /**
     * 查询过车超重统计
     */

    List<CarOut> selectCarOverWeight(@Param("stationId")int stationId,@Param("startDate")Date startDate,
                                    @Param("endDate")Date endDate);

    List<WeightData> selectWeightDataBefore40DaysById(Long id);

    Integer selectLaneId(@Param("stationId")Integer stationId, @Param("lane")Integer lane);

    List<WeightData> selectByStationIdAndLane(@Param("stationId")int stationId, @Param("laneMid")int laneMid);
}