package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.StationType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站点 数据层
 *
 * @author ruoyi
 * @date 2018-11-22
 */
public interface StationTypeMapper {

    List<StationType> selectStationTypeList();

    List<StationType> selectStationTypeListNoRoot();

    StationType selectStationTypeById(@Param("typeId")Long typeId);

    StationType checkTypeNameUnique(@Param("typeName") String typeName, @Param("parentId") Long parentId);

    int insertStationType(StationType stationType);

    int updateStationType(StationType stationType);

    int selectTypeCount(Long typeId);

    int deleteStationTypeById(Long typeId);

    List<StationType> selectStationTreeDataNoRoot();

    List<StationType> selectStationTreeDataRoot();
}