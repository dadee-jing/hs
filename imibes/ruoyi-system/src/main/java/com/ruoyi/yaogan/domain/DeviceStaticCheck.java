package com.ruoyi.yaogan.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 遥测设备静态检查过程数据表 rs_device_static_check
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public class DeviceStaticCheck extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer iD;
	/** 点位编号 */
	private String jZBH;
	/** 遥测线编号 */
	private String yCXBH;
	/** 检查开始时间 */
	private Date jCKSSJ;
	/** 标气类别 */
	private String bQLB;
	/** CO2标准值 */
	private BigDecimal cO2BZZ;
	/** CO标准值 */
	private BigDecimal cOBZZ;
	/** NO标准值 */
	private BigDecimal nOBZZ;
	/** CO2测量值 */
	private BigDecimal cO2CLZ;
	/** CO测量值 */
	private BigDecimal cOCLZ;
	/** NO测量值 */
	private BigDecimal nOCLZ;

	public void setID(Integer iD) 
	{
		this.iD = iD;
	}

	public Integer getID() 
	{
		return iD;
	}
	public void setJZBH(String jZBH) 
	{
		this.jZBH = jZBH;
	}

	public String getJZBH() 
	{
		return jZBH;
	}
	public void setYCXBH(String yCXBH) 
	{
		this.yCXBH = yCXBH;
	}

	public String getYCXBH() 
	{
		return yCXBH;
	}
	public void setJCKSSJ(Date jCKSSJ) 
	{
		this.jCKSSJ = jCKSSJ;
	}

	public Date getJCKSSJ() 
	{
		return jCKSSJ;
	}
	public void setBQLB(String bQLB) 
	{
		this.bQLB = bQLB;
	}

	public String getBQLB() 
	{
		return bQLB;
	}
	public void setCO2BZZ(BigDecimal cO2BZZ) 
	{
		this.cO2BZZ = cO2BZZ;
	}

	public BigDecimal getCO2BZZ() 
	{
		return cO2BZZ;
	}
	public void setCOBZZ(BigDecimal cOBZZ) 
	{
		this.cOBZZ = cOBZZ;
	}

	public BigDecimal getCOBZZ() 
	{
		return cOBZZ;
	}
	public void setNOBZZ(BigDecimal nOBZZ) 
	{
		this.nOBZZ = nOBZZ;
	}

	public BigDecimal getNOBZZ() 
	{
		return nOBZZ;
	}
	public void setCO2CLZ(BigDecimal cO2CLZ) 
	{
		this.cO2CLZ = cO2CLZ;
	}

	public BigDecimal getCO2CLZ() 
	{
		return cO2CLZ;
	}
	public void setCOCLZ(BigDecimal cOCLZ) 
	{
		this.cOCLZ = cOCLZ;
	}

	public BigDecimal getCOCLZ() 
	{
		return cOCLZ;
	}
	public void setNOCLZ(BigDecimal nOCLZ) 
	{
		this.nOCLZ = nOCLZ;
	}

	public BigDecimal getNOCLZ() 
	{
		return nOCLZ;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iD", getID())
            .append("jZBH", getJZBH())
            .append("yCXBH", getYCXBH())
            .append("jCKSSJ", getJCKSSJ())
            .append("bQLB", getBQLB())
            .append("cO2BZZ", getCO2BZZ())
            .append("cOBZZ", getCOBZZ())
            .append("nOBZZ", getNOBZZ())
            .append("cO2CLZ", getCO2CLZ())
            .append("cOCLZ", getCOCLZ())
            .append("nOCLZ", getNOCLZ())
            .toString();
    }
}
