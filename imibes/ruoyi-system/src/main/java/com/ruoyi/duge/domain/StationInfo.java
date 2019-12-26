package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	private Integer speedLimit;
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
	/** 卡口编号 */
	private String kakoCode;
	/** 类型编号 */
	private Integer typeId;

	private Long parentId;
	/** 站点类型 */
	private StationType stationType;
	/** 最近过车时间 */
	private String recentTime;
	/** 安装时间 */
	private String installTime;

	@Override
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
