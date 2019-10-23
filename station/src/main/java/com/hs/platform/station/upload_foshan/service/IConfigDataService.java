package com.hs.platform.station.upload_foshan.service;


import com.hs.platform.station.upload_foshan.domaim.ConfigData;

import java.util.List;

/**
 * 设备 服务层
 *
 * @author ruoyi
 * @date 2018-12-09
 */
public interface IConfigDataService {
    /**
     * 查询设备信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    public ConfigData selectDataById(Long id);

    /**
     * 查询设备列表
     *
     * @param data 设备信息
     * @return 设备集合
     */
    public List<ConfigData> selectDataList(ConfigData data);

    public String getConfigValue(String key);

    /**
     * 新增设备
     *
     * @param data 设备信息
     * @return 结果
     */
    public int insertData(ConfigData data);

    /**
     * 修改设备
     *
     * @param data 设备信息
     * @return 结果
     */
    public int updateData(ConfigData data);

//    /**
//     * 删除设备信息
//     *
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//    public int deleteDataByIds(String ids);

}
