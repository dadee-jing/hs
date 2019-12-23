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

	List<DeviceEnum> selectDeviceEnumList(@Param("type") Integer type);

	int insertStationDeviceInfo(StationDeviceInfo stationDeviceInfo);

	StationDeviceInfo selectDeviceById(@Param("id") Integer id);

	int updateStationDeviceInfo(StationDeviceInfo stationDeviceInfo);

	int deleteStationDeviceInfoById(@Param("id") Integer id);
}