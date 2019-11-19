package com.hs.platform.station.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站点表 duge_station_info
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Data
public class StationInfo {

	/** 主键 */
	private Integer id;
	/** 站点名称 */
	private String name;
	/** 缩写 */
	private String abbreviation;
	/** 纬度 */
	private String latitude;
	/** 经度 */
	private String longitude;
	/** 负责人 */
	private Integer userId;
	/** 公路局名称 */
	private String bureau;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区 */
	private String county;
	/** 详细地址 */
	private String address;
	/** 车辆限速 */
	private Double speedLimit;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	private String longitude_WGS84;

	private String latitude_WGS84;

	private String longitude_GCJ02;

	private String latitude_GCJ02;

	private String longitude_BD09;

	private String latitude_BD09;

	private Long hourOverCount;

	private Integer index;

	/** ip */
	private String ip;
	/** 端口 */
	private Integer port;
	/** 连通状态 */
	private Integer state;

	private String remarkInfo;

}
