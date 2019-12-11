package com.ruoyi.yaogan.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 点位环境空气质量记录表 rs_air_quality_record
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public class AirQualityRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 记录时间YYYYMMDD24HHMMSS */
	private String jLSJ;
	/** 点位编号*/
	private String dWBH;
	/**  PM2.5*/
	private BigDecimal pM25;
	/**  PM10*/
	private BigDecimal pM10;
	/**  CO*/
	private BigDecimal cO;
	/**  SO2*/
	private BigDecimal sO2;
	/** O3 */
	private BigDecimal o3;
	/** NO2 */
	private BigDecimal nO2;

	public void setJLSJ(String jLSJ) 
	{
		this.jLSJ = jLSJ;
	}

	public String getJLSJ() 
	{
		return jLSJ;
	}
	public void setDWBH(String dWBH) 
	{
		this.dWBH = dWBH;
	}

	public String getDWBH() 
	{
		return dWBH;
	}
	public void setPM25(BigDecimal pM25) 
	{
		this.pM25 = pM25;
	}

	public BigDecimal getPM25() 
	{
		return pM25;
	}
	public void setPM10(BigDecimal pM10) 
	{
		this.pM10 = pM10;
	}

	public BigDecimal getPM10() 
	{
		return pM10;
	}
	public void setCO(BigDecimal cO) 
	{
		this.cO = cO;
	}

	public BigDecimal getCO() 
	{
		return cO;
	}
	public void setSO2(BigDecimal sO2) 
	{
		this.sO2 = sO2;
	}

	public BigDecimal getSO2() 
	{
		return sO2;
	}
	public void setO3(BigDecimal o3) 
	{
		this.o3 = o3;
	}

	public BigDecimal getO3() 
	{
		return o3;
	}
	public void setNO2(BigDecimal nO2) 
	{
		this.nO2 = nO2;
	}

	public BigDecimal getNO2() 
	{
		return nO2;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jLSJ", getJLSJ())
            .append("dWBH", getDWBH())
            .append("pM25", getPM25())
            .append("pM10", getPM10())
            .append("cO", getCO())
            .append("sO2", getSO2())
            .append("o3", getO3())
            .append("nO2", getNO2())
            .toString();
    }
}
