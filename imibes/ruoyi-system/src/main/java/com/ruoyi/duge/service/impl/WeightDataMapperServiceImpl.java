package com.ruoyi.duge.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.duge.domain.CarOut;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IWeightDataMapperService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private com.ruoyi.duge.mapper.WeightDataMapper dataMapper;

    /**
     * 查询重量数据信息
     *
     * @param id 重量数据ID
     * @return 重量数据信息
     */
    @Override
    public com.ruoyi.duge.domain.WeightData selectDataById(Long id) {
        return dataMapper.selectDataById(id);
    }

    public com.ruoyi.duge.domain.WeightData selectLast() {
        return dataMapper.selectLast();
    }

    /**
     * 查询重量数据列表
     *
     * @param data 重量数据信息
     * @return 重量数据集合
     */
    @Override
    public List<com.ruoyi.duge.domain.WeightData> selectDataList(com.ruoyi.duge.domain.WeightData data) {
        return dataMapper.selectDataList(data);
    }

    @Override
    public List<com.ruoyi.duge.domain.WeightData> selectNotUploadDataList() {
        return dataMapper.selectNotUpload();
    }


    /**
     * 新增重量数据
     *
     * @param data 重量数据信息
     * @return 结果
     */
    @Override
    public int insertData(com.ruoyi.duge.domain.WeightData data) {
        return dataMapper.insertData(data);
    }

    /**
     * 修改重量数据
     *
     * @param data 重量数据信息
     * @return 结果
     */
    @Override
    public int updateData(com.ruoyi.duge.domain.WeightData data) {
        return dataMapper.updateData(data);
    }

    /**
     * 删除重量数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDataByIds(String ids) {
        return dataMapper.deleteDataByIds(Convert.toStrArray(ids));
    }

    @Override
    public Map selectStationStatistics(com.ruoyi.duge.domain.WeightData data){
        return dataMapper.selectStationStatistics(data);
    }

    @Override
    public List<WeightData> selectNotUploadJj() {
        return dataMapper.selectNotUploadJj();
    }

    @Override
    public List<WeightData> selectIllegalAnd2YearAgo() {
        return dataMapper.selectIllegalAnd2YearAgo();
    }

    @Override
    public List<WeightData> selectNotUploadSj() {
        return dataMapper.selectNotUploadSj();
    }



    @Override
    public List<WeightData> selectTruckNumberByTime() {
        return dataMapper.selectTruckNumberByTime();
    }

    @Override
    public Integer selectByTruckNumber(Date weightingDate, String truckNumber) {
        return dataMapper.selectByTruckNumber(weightingDate,truckNumber);
    }
    @Override
    public List<WeightData> selectTruckNumberOver90Date() {
        return dataMapper.selectTruckNumberOver90Date();
    }

    @Override
    public List<WeightData> selectByTruckNumberOver90Date(Date weightingDate,String truckNum) {
        return dataMapper.selectByTruckNumberOver90Date(weightingDate,truckNum);
    }

     @Override
    public List<WeightData> selectBefore40Days() {
        return dataMapper.selectBefore40Days();
    }

    @Override
    public int insertIntoWeightDataBefore40Days(WeightData weightData) {
      return   dataMapper.insertIntoWeightDataBefore40Days(weightData);
    }

    @Override
    public void updateWeightDataBefore40Days(WeightData weightData) {
        dataMapper.updateWeightDataBefore40Days(weightData);
    }

    @Override
    public boolean checkIsExist(Long id) {
        if(dataMapper.selectWeightDataBefore40DaysById(id).size()>0){
         return true;
        }else {
         return false;
        }
    }

    @Override
	public List<CarOut> selectCarOutNumber(@Param("stationId")int stationId,@Param("startDate")Date startDate,
                                           @Param("endDate")Date endDate) {
		// TODO Auto-generated method stub
		return dataMapper.selectCarOutNumber(stationId,startDate,endDate);
	}

    /**
     * 查询过车超重统计
     */
    @Override
    public List<CarOut> selectCarOverWeight(@Param("stationId")int stationId,@Param("startDate")Date startDate,
                                           @Param("endDate")Date endDate) {
        // TODO Auto-generated method stub
        return dataMapper.selectCarOverWeight(stationId,startDate,endDate);
    }

}
