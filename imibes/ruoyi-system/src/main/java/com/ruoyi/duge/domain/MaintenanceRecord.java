package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站点维护记录表 station_maintenance_record
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
@Data
@ToString
public class MaintenanceRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 站点ID */
	private Integer stationId;

	private String stationName;
	/** 保养日期 */
	private String maintenanceDate;
	/** 现场状况描述 */
	private String siteCondition;
	/** 操作记录 */
	private String operationLog;
	/** 操作人 */
	private String operator;
	/** 操作人手机号 */
	private String operatorPhone;
	/** 备注 */
	private String remark;
	/** 创建日期 */
	private Date createTime;
	/** 创建人 */
	private String createBy;
	/** 更新日期 */
	private Date updateTime;
	/** 更新人 */
	private String updateBy;
	/** 协同人 */
	private String coordinator;
	/** 协同人手机 */
	private String coordinatorPhone;

	//遗留问题
	private String problem;

	//工单号
	private String orderNumber;



}
