package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 站点表 duge_station_info
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Data
public class StationDeviceInfo{

	private Integer id;
	private Integer stationId;
	private Integer deviceTypeId;
	private Integer deviceNameId;
	private String ipAddress;
	private String port;
	private Integer state;
	private Timestamp createTime;
	private String remark;
}
