package com.ruoyi.yaogan.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 遥测设备自检表 rs_station_check_log
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public class StationCheckLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer iD;
	/** 点位编号 */
	private String dWBH;
	/** 遥测线编号 */
	private String yCXBH;
	/** 自检开始时间 */
	private Date zJKSRQ;
	/** 是否通过 Y/N */
	private String sFTG;
	/** 备注 */
	private String bZ;

	public void setID(Integer iD) 
	{
		this.iD = iD;
	}

	public Integer getID() 
	{
		return iD;
	}
	public void setDWBH(String dWBH) 
	{
		this.dWBH = dWBH;
	}

	public String getDWBH() 
	{
		return dWBH;
	}
	public void setYCXBH(String yCXBH) 
	{
		this.yCXBH = yCXBH;
	}

	public String getYCXBH() 
	{
		return yCXBH;
	}
	public void setZJKSRQ(Date zJKSRQ) 
	{
		this.zJKSRQ = zJKSRQ;
	}

	public Date getZJKSRQ() 
	{
		return zJKSRQ;
	}
	public void setSFTG(String sFTG) 
	{
		this.sFTG = sFTG;
	}

	public String getSFTG() 
	{
		return sFTG;
	}
	public void setBZ(String bZ) 
	{
		this.bZ = bZ;
	}

	public String getBZ() 
	{
		return bZ;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iD", getID())
            .append("dWBH", getDWBH())
            .append("yCXBH", getYCXBH())
            .append("zJKSRQ", getZJKSRQ())
            .append("sFTG", getSFTG())
            .append("bZ", getBZ())
            .toString();
    }
}
