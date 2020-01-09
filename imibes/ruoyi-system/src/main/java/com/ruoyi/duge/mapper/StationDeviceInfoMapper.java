package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.DeviceEnum;
import com.ruoyi.duge.domain.StationDeviceInfo;
import com.ruoyi.duge.domain.StationDeviceInfoVo;
import com.ruoyi.duge.domain.StationInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站点 数据层
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
public interface StationDeviceInfoMapper {


	List<StationDeviceInfoVo> selectStationDeviceInfoList(StationDeviceInfo stationDeviceInfo);

	List<StationDeviceInfoVo> selectStationDeviceListByStationId(@Param("stationId") Integer stationId);

	List<DeviceEnum> selectDeviceEnumList(@Param("type") Integer type);

	int insertStationDeviceInfo(StationDeviceInfo stationDeviceInfo);

	int insertStationDeviceInfoVo(StationDeviceInfoVo stationDeviceInfoVo);

	StationDeviceInfo selectDeviceById(@Param("id") Integer id);

	int updateStationDeviceInfo(StationDeviceInfo stationDeviceInfo);

	int deleteStationDeviceInfoById(@Param("id") Integer id);

    int selectStationIdByDeviceId(@Param("id") Integer id);

	Integer getDeviceEnumIdByName(@Param("value") String value,@Param("type")  int type);

	void insertDeviceEnum(DeviceEnum deviceEnum);

	StationDeviceInfo selectDeviceByStationAndNameId(@Param("stationId")Integer stationId, @Param("deviceNameId")Integer deviceNameId);
}