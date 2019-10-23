package com.hs.platform.station.upload_foshan.service.impl;

import com.hs.platform.station.upload_foshan.domaim.WeightData;
import com.hs.platform.station.upload_foshan.mapper.WeightDataMapper;
import com.hs.platform.station.upload_foshan.service.IWeightDataMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import java.util.List;
import java.util.Map;

/**
 * 重量数据 服务层实现
 *
 * @author ruoyi
 * @date 2018-11-20
 */
@Service
public class WeightDataMapperServiceImpl implements IWeightDataMapperService {
    @Autowired
    private WeightDataMapper dataMapper;

    /**
     * 查询重量数据信息
     *
     * @param id 重量数据ID
     * @return 重量数据信息
     */
    @Override
    public WeightData selectDataById(Long id) {
        return dataMapper.selectDataById(id);
    }

    public WeightData selectLast() {
        return dataMapper.selectLast();
    }

    /**
     * 查询重量数据列表
     *
     * @param data 重量数据信息
     * @return 重量数据集合
     */
    @Override
    public List<WeightData> selectDataList(WeightData data) {
        return dataMapper.selectDataList(data);
    }

    @Override
    public List<WeightData> selectNotUploadDataList() {
        return dataMapper.selectNotUpload();
    }


    /**
     * 新增重量数据
     *
     * @param data 重量数据信息
     * @return 结果
     */
    @Override
    public int insertData(WeightData data) {
        return dataMapper.insertData(data);
    }

    /**
     * 修改重量数据
     *
     * @param data 重量数据信息
     * @return 结果
     */
    @Override
    public int updateData(WeightData data) {
        return dataMapper.updateData(data);
    }


//    @Override
//    public int deleteDataByIds(String ids) {
//        return dataMapper.deleteDataByIds(Convert.toStrArray(ids));
//    }

    @Override
    public Map selectStationStatistics(WeightData data){
        return dataMapper.selectStationStatistics(data);
    }

    @Override
    public List<WeightData> selectNotUploadSj() {
        return dataMapper.selectNotUploadSj();
    }

}
