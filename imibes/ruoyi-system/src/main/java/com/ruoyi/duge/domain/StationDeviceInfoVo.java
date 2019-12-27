package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 站点表 duge_station_info
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StationDeviceInfoVo extends StationDeviceInfo{

	private String stationName;
	private String deviceTypeName;
	private String deviceName;
}
