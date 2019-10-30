package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.WeightData;

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

    /**
     * 查询违法并且过时的数据
     * @return
     */
    public List<WeightData> selectIllegalAndOverDate();
}