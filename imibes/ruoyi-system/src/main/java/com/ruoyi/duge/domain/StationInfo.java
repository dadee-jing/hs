package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
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


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setAbbreviation(String abbreviation) 
	{
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() 
	{
		return abbreviation;
	}
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLatitude() 
	{
		return latitude;
	}
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	public String getLongitude() 
	{
		return longitude;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setBureau(String bureau) 
	{
		this.bureau = bureau;
	}

	public String getBureau() 
	{
		return bureau;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setCounty(String county) 
	{
		this.county = county;
	}

	public String getCounty() 
	{
		return county;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public Double getSpeedLimit() { return speedLimit; }
	public void setSpeedLimit(Double speedLimit) { this.speedLimit = speedLimit; }

	public String getAddress() 
	{
		return address;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public String getLongitude_WGS84() {
		return longitude_WGS84;
	}

	public void setLongitude_WGS84(String longitude_WGS84) {
		this.longitude_WGS84 = longitude_WGS84;
	}

	public String getLatitude_WGS84() {
		return latitude_WGS84;
	}

	public void setLatitude_WGS84(String latitude_WGS84) {
		this.latitude_WGS84 = latitude_WGS84;
	}

	public String getLongitude_GCJ02() {
		return longitude_GCJ02;
	}

	public void setLongitude_GCJ02(String longitude_GCJ02) {
		this.longitude_GCJ02 = longitude_GCJ02;
	}

	public String getLatitude_GCJ02() {
		return latitude_GCJ02;
	}

	public void setLatitude_GCJ02(String latitude_GCJ02) {
		this.latitude_GCJ02 = latitude_GCJ02;
	}

	public String getLongitude_BD09() {
		return longitude_BD09;
	}

	public void setLongitude_BD09(String longitude_BD09) {
		this.longitude_BD09 = longitude_BD09;
	}

	public String getLatitude_BD09() {
		return latitude_BD09;
	}

	public void setLatitude_BD09(String latitude_BD09) {
		this.latitude_BD09 = latitude_BD09;
	}

	public Long getHourOverCount() {
		return hourOverCount;
	}

	public void setHourOverCount(Long hourOverCount) {
		this.hourOverCount = hourOverCount;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("abbreviation", getAbbreviation())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("userId", getUserId())
            .append("bureau", getBureau())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("address", getAddress())
			.append("speedLimit",getSpeedLimit())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
