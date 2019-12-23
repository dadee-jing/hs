package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 站点表 duge_station_info
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Data
public class StationDeviceInfoVo {

	private Integer id;
	private Integer stationId;
	private Integer deviceTypeId;
	private Integer deviceNameId;
	private String ipAddress;
	private String port;
	private Integer state;
	private Timestamp createTime;

	private String stationName;
	private String deviceTypeName;
	private String deviceName;
	private String snCode;

}
