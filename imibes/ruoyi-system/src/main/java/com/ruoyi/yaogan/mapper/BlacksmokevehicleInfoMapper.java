package com.ruoyi.yaogan.mapper;

import com.ruoyi.yaogan.domain.BlacksmokevehicleInfo;

import java.util.List;

/**
 * 点位环境空气质量记录 数据层
 *
 * @author ruoyi
 * @date 2019-04-08
 */
public interface BlacksmokevehicleInfoMapper
{
    /**
     * 查询点位环境空气质量记录信息
     *
     * @param jLSJ 点位环境空气质量记录ID
     * @return 点位环境空气质量记录信息
     */
    public BlacksmokevehicleInfo selectBlacksmokevehicleInfoById(String jLSJ);

    /**
     * 查询点位环境空气质量记录列表
     *
     * @param blacksmokevehicleInfo 点位环境空气质量记录信息
     * @return 点位环境空气质量记录集合
     */
    public List<BlacksmokevehicleInfo> selectBlacksmokevehicleInfoList(BlacksmokevehicleInfo blacksmokevehicleInfo);

    /**
     * 新增点位环境空气质量记录
     *
     * @param blacksmokevehicleInfo 点位环境空气质量记录信息
     * @return 结果
     */
    public int insertBlacksmokevehicleInfo(BlacksmokevehicleInfo blacksmokevehicleInfo);

    /**
     * 修改点位环境空气质量记录
     *
     * @param blacksmokevehicleInfo 点位环境空气质量记录信息
     * @return 结果
     */
    public int updateBlacksmokevehicleInfo(BlacksmokevehicleInfo blacksmokevehicleInfo);

    /**
     * 删除点位环境空气质量记录
     *
     * @param jLSJ 点位环境空气质量记录ID
     * @return 结果
     */
    public int deleteBlacksmokevehicleInfoById(Long id);

    /**
     * 批量删除点位环境空气质量记录
     *
     * @param jLSJs 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlacksmokevehicleInfoByIds(Long[] ids);


    public Long selecBlacksmokeCount(BlacksmokevehicleInfo blacksmokevehicleInfo);
}