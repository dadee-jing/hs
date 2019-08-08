package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站点维护记录表 station_maintenance_record
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
public class MaintenanceRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 站点ID */
	private Integer stationId;

	private String stationName;
	/** 保养日期 */
	private Date maintenanceDate;
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

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setStationId(Integer stationId) 
	{
		this.stationId = stationId;
	}

	public Integer getStationId() 
	{
		return stationId;
	}
	public void setMaintenanceDate(Date maintenanceDate) 
	{
		this.maintenanceDate = maintenanceDate;
	}

	public Date getMaintenanceDate() 
	{
		return maintenanceDate;
	}
	public void setSiteCondition(String siteCondition) 
	{
		this.siteCondition = siteCondition;
	}

	public String getSiteCondition() 
	{
		return siteCondition;
	}
	public void setOperationLog(String operationLog) 
	{
		this.operationLog = operationLog;
	}

	public String getOperationLog() 
	{
		return operationLog;
	}
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	public String getOperator() 
	{
		return operator;
	}
	public void setOperatorPhone(String operatorPhone) 
	{
		this.operatorPhone = operatorPhone;
	}

	public String getOperatorPhone() 
	{
		return operatorPhone;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stationId", getStationId())
            .append("maintenanceDate", getMaintenanceDate())
            .append("siteCondition", getSiteCondition())
            .append("operationLog", getOperationLog())
            .append("operator", getOperator())
            .append("operatorPhone", getOperatorPhone())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
