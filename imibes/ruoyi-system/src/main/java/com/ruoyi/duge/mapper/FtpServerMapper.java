package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.FtpServer;

import java.util.List;

/**
 * 设备 数据层
 *
 * @author ruoyi
 * @date 2018-12-09
 */
public interface FtpServerMapper {
    /**
     * 查询设备信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    public FtpServer selectDataById(Long id);

    /**
     * 查询设备列表
     *
     * @param data 设备信息
     * @return 设备集合
     */
    public List<FtpServer> selectDataList(FtpServer data);

    /**
     * 新增设备
     *
     * @param data 设备信息
     * @return 结果
     */
    public int insertData(FtpServer data);

    /**
     * 修改设备
     *
     * @param data 设备信息
     * @return 结果
     */
    public int updateData(FtpServer data);

    /**
     * 删除设备
     *
     * @param id 设备ID
     * @return 结果
     */
    public int deleteDataById(Long id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataByIds(String[] ids);

}