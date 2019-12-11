package com.ruoyi.duge.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.duge.domain.ConfigData;
import com.ruoyi.duge.mapper.ConfigDataMapper;
import com.ruoyi.duge.service.IConfigDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备 服务层实现
 *
 * @author ruoyi
 * @date 2018-12-09
 */
@Service
public class ConfigDataServiceImpl implements IConfigDataService {
    @Autowired
    private ConfigDataMapper dataMapper;

    /**
     * 查询设备信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    @Override
    public ConfigData selectDataById(Long id) {
        return dataMapper.selectDataById(id);
    }

    /**
     * 查询设备列表
     *
     * @param data 设备信息
     * @return 设备集合
     */
    @Override
    public List<ConfigData> selectDataList(ConfigData data) {
        return dataMapper.selectDataList(data);
    }

    @Override
    public String getConfigValue(String key) {
        return dataMapper.getConfigValue(key);
    }

    /**
     * 新增设备
     *
     * @param data 设备信息
     * @return 结果
     */
    @Override
    public int insertData(ConfigData data) {
        return dataMapper.insertData(data);
    }

    /**
     * 修改设备
     *
     * @param data 设备信息
     * @return 结果
     */
    @Override
    public int updateData(ConfigData data) {
        return dataMapper.updateData(data);
    }

    /**
     * 删除设备对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDataByIds(String ids) {
        return dataMapper.deleteDataByIds(Convert.toStrArray(ids));
    }

}
