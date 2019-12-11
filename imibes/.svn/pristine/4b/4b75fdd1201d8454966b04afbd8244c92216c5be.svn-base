package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站点审查记录表 station_audit_record
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
public class AuditRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;

	/** 站点ID */
	private Integer stationId;
	private String stationName;
	/** 保养日期 */
	private Date auditDate;
	/** 现场状况描述 */
	private String siteCondition;
	/** 审查记录 */
	private String auditionLog;
	/** 审查人单位 */
	private String auditorCompany;
	/** 审查人 */
	private String auditor;
	/** 审查人手机号 */
	private String auditorPhone;
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
	public void setAuditDate(Date auditDate) 
	{
		this.auditDate = auditDate;
	}

	public Date getAuditDate() 
	{
		return auditDate;
	}
	public void setSiteCondition(String siteCondition) 
	{
		this.siteCondition = siteCondition;
	}

	public String getSiteCondition() 
	{
		return siteCondition;
	}
	public void setAuditionLog(String auditionLog) 
	{
		this.auditionLog = auditionLog;
	}

	public String getAuditionLog() 
	{
		return auditionLog;
	}
	public void setAuditorCompany(String auditorCompany) 
	{
		this.auditorCompany = auditorCompany;
	}

	public String getAuditorCompany() 
	{
		return auditorCompany;
	}
	public void setAuditor(String auditor) 
	{
		this.auditor = auditor;
	}

	public String getAuditor() 
	{
		return auditor;
	}
	public void setAuditorPhone(String auditorPhone) 
	{
		this.auditorPhone = auditorPhone;
	}

	public String getAuditorPhone() 
	{
		return auditorPhone;
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
            .append("auditDate", getAuditDate())
            .append("siteCondition", getSiteCondition())
            .append("auditionLog", getAuditionLog())
            .append("auditorCompany", getAuditorCompany())
            .append("auditor", getAuditor())
            .append("auditorPhone", getAuditorPhone())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
