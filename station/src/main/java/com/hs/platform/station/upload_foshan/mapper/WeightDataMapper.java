package com.hs.platform.station.upload_foshan.mapper;

import com.hs.platform.station.upload_foshan.domaim.WeightData;

import java.util.List;
import java.util.Map;

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
    public WeightData selectDataById(Long id);

    public WeightData selectLast();
    /**
     * 查询重量数据列表
     *
     * @param data 重量数据信息
     * @return 重量数据集合
     */
    public List<WeightData> selectDataList(WeightData data);

    public List<WeightData> selectNotUpload();

    public List<WeightData> selectNotUploadYhl();

    public List<WeightData> selectNotUploadSj();

    public List<WeightData> selectNotUploadJj();

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

    public Map selectStationStatistics(WeightData data);

    public List<Map<String, Object>> selectCountHourList(WeightData data);

    public Double selectSpeedAvg(WeightData data);
}