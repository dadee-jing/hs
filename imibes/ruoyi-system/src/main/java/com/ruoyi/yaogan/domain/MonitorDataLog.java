package com.ruoyi.yaogan.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 遥感监测数据表 rs_monitor_data_log
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public class MonitorDataLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 记录编号 */
	private String jLBH;
	/** 点位编号 */
	private String dWBH;
	/** 遥测线编号 */
	private String yCXBH;
	/** 监测点位日志号 */
	private String jCDWRZH;
	/** 车道序号 */
	private String cDXH;
	/** 监测时间 */
	private Date jCRQ;
	/** 地点经度 */
	private BigDecimal dDJD;
	/** 地点维度 */
	private BigDecimal dDWD;
	/** 车道坡度 */
	private BigDecimal cDPD;
	/** 判定结果 */
	private String pDJG;
	/** 号牌号码 */
	private String hPHM;
	/**  */
	private String cPYS;
	/** 号牌种类 */
	private String hPZL;
	/** 燃料种类 */
	private String rLZL;
	/** CO2结果 */
	private BigDecimal cO2JG;
	/** CO/CO2比率 */
	private BigDecimal cOCO2;
	/** HC/CO2比率 */
	private BigDecimal hCCO2;
	/** NO/CO2比率 */
	private BigDecimal nOCO2;
	/** CO结果 */
	private BigDecimal cOJG;
	/** HC结果 */
	private BigDecimal hCJG;
	/** NO结果 */
	private BigDecimal nOJG;
	/** 不透光度结果 */
	private BigDecimal bTGDJG;
	/** 林格曼黑度 */
	private Integer lGMHD;
	/** CO限制 */
	private BigDecimal cOXZ;
	/** NO限值 */
	private BigDecimal nOXZ;
	/** 不透光度限值 */
	private BigDecimal bTGDXZ;
	/** 黑度限值 */
	private Integer hDXZ;
	/** 车辆速度 */
	private BigDecimal cLSD;
	/** 车辆加速度 */
	private BigDecimal cLJSD;
	/** VSP */
	private BigDecimal vSP;
	/** 风速 */
	private BigDecimal fS;
	/** 风向 */
	private String fX;
	/** 环境温度 */
	private BigDecimal hJWD;
	/** 湿度 */
	private BigDecimal sD;
	/** 大气压 */
	private BigDecimal dQY;
	/** 轨迹信息编号 */
	private String gJXXBH;
	/** 图像1文件名 */
	private String tP1;
	/** 图像2文件名 */
	private String tP2;
	/** 视频1文件名 */
	private String sP1;

	public void setJLBH(String jLBH) 
	{
		this.jLBH = jLBH;
	}

	public String getJLBH() 
	{
		return jLBH;
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
	public void setJCDWRZH(String jCDWRZH) 
	{
		this.jCDWRZH = jCDWRZH;
	}

	public String getJCDWRZH() 
	{
		return jCDWRZH;
	}
	public void setCDXH(String cDXH) 
	{
		this.cDXH = cDXH;
	}

	public String getCDXH() 
	{
		return cDXH;
	}
	public void setJCRQ(Date jCRQ) 
	{
		this.jCRQ = jCRQ;
	}

	public Date getJCRQ() 
	{
		return jCRQ;
	}
	public void setDDJD(BigDecimal dDJD) 
	{
		this.dDJD = dDJD;
	}

	public BigDecimal getDDJD() 
	{
		return dDJD;
	}
	public void setDDWD(BigDecimal dDWD) 
	{
		this.dDWD = dDWD;
	}

	public BigDecimal getDDWD() 
	{
		return dDWD;
	}
	public void setCDPD(BigDecimal cDPD) 
	{
		this.cDPD = cDPD;
	}

	public BigDecimal getCDPD() 
	{
		return cDPD;
	}
	public void setPDJG(String pDJG) 
	{
		this.pDJG = pDJG;
	}

	public String getPDJG() 
	{
		return pDJG;
	}
	public void setHPHM(String hPHM) 
	{
		this.hPHM = hPHM;
	}

	public String getHPHM() 
	{
		return hPHM;
	}
	public void setCPYS(String cPYS) 
	{
		this.cPYS = cPYS;
	}

	public String getCPYS() 
	{
		return cPYS;
	}
	public void setHPZL(String hPZL) 
	{
		this.hPZL = hPZL;
	}

	public String getHPZL() 
	{
		return hPZL;
	}
	public void setRLZL(String rLZL) 
	{
		this.rLZL = rLZL;
	}

	public String getRLZL() 
	{
		return rLZL;
	}
	public void setCO2JG(BigDecimal cO2JG) 
	{
		this.cO2JG = cO2JG;
	}

	public BigDecimal getCO2JG() 
	{
		return cO2JG;
	}
	public void setCOCO2(BigDecimal cOCO2) 
	{
		this.cOCO2 = cOCO2;
	}

	public BigDecimal getCOCO2() 
	{
		return cOCO2;
	}
	public void setHCCO2(BigDecimal hCCO2) 
	{
		this.hCCO2 = hCCO2;
	}

	public BigDecimal getHCCO2() 
	{
		return hCCO2;
	}
	public void setNOCO2(BigDecimal nOCO2) 
	{
		this.nOCO2 = nOCO2;
	}

	public BigDecimal getNOCO2() 
	{
		return nOCO2;
	}
	public void setCOJG(BigDecimal cOJG) 
	{
		this.cOJG = cOJG;
	}

	public BigDecimal getCOJG() 
	{
		return cOJG;
	}
	public void setHCJG(BigDecimal hCJG) 
	{
		this.hCJG = hCJG;
	}

	public BigDecimal getHCJG() 
	{
		return hCJG;
	}
	public void setNOJG(BigDecimal nOJG) 
	{
		this.nOJG = nOJG;
	}

	public BigDecimal getNOJG() 
	{
		return nOJG;
	}
	public void setBTGDJG(BigDecimal bTGDJG) 
	{
		this.bTGDJG = bTGDJG;
	}

	public BigDecimal getBTGDJG() 
	{
		return bTGDJG;
	}
	public void setLGMHD(Integer lGMHD) 
	{
		this.lGMHD = lGMHD;
	}

	public Integer getLGMHD() 
	{
		return lGMHD;
	}
	public void setCOXZ(BigDecimal cOXZ) 
	{
		this.cOXZ = cOXZ;
	}

	public BigDecimal getCOXZ() 
	{
		return cOXZ;
	}
	public void setNOXZ(BigDecimal nOXZ) 
	{
		this.nOXZ = nOXZ;
	}

	public BigDecimal getNOXZ() 
	{
		return nOXZ;
	}
	public void setBTGDXZ(BigDecimal bTGDXZ) 
	{
		this.bTGDXZ = bTGDXZ;
	}

	public BigDecimal getBTGDXZ() 
	{
		return bTGDXZ;
	}
	public void setHDXZ(Integer hDXZ) 
	{
		this.hDXZ = hDXZ;
	}

	public Integer getHDXZ() 
	{
		return hDXZ;
	}
	public void setCLSD(BigDecimal cLSD) 
	{
		this.cLSD = cLSD;
	}

	public BigDecimal getCLSD() 
	{
		return cLSD;
	}
	public void setCLJSD(BigDecimal cLJSD) 
	{
		this.cLJSD = cLJSD;
	}

	public BigDecimal getCLJSD() 
	{
		return cLJSD;
	}
	public void setVSP(BigDecimal vSP) 
	{
		this.vSP = vSP;
	}

	public BigDecimal getVSP() 
	{
		return vSP;
	}
	public void setFS(BigDecimal fS) 
	{
		this.fS = fS;
	}

	public BigDecimal getFS() 
	{
		return fS;
	}
	public void setFX(String fX) 
	{
		this.fX = fX;
	}

	public String getFX() 
	{
		return fX;
	}
	public void setHJWD(BigDecimal hJWD) 
	{
		this.hJWD = hJWD;
	}

	public BigDecimal getHJWD() 
	{
		return hJWD;
	}
	public void setSD(BigDecimal sD) 
	{
		this.sD = sD;
	}

	public BigDecimal getSD() 
	{
		return sD;
	}
	public void setDQY(BigDecimal dQY) 
	{
		this.dQY = dQY;
	}

	public BigDecimal getDQY() 
	{
		return dQY;
	}
	public void setGJXXBH(String gJXXBH) 
	{
		this.gJXXBH = gJXXBH;
	}

	public String getGJXXBH() 
	{
		return gJXXBH;
	}
	public void setTP1(String tP1) 
	{
		this.tP1 = tP1;
	}

	public String getTP1() 
	{
		return tP1;
	}
	public void setTP2(String tP2) 
	{
		this.tP2 = tP2;
	}

	public String getTP2() 
	{
		return tP2;
	}
	public void setSP1(String sP1) 
	{
		this.sP1 = sP1;
	}

	public String getSP1() 
	{
		return sP1;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jLBH", getJLBH())
            .append("dWBH", getDWBH())
            .append("yCXBH", getYCXBH())
            .append("jCDWRZH", getJCDWRZH())
            .append("cDXH", getCDXH())
            .append("jCRQ", getJCRQ())
            .append("dDJD", getDDJD())
            .append("dDWD", getDDWD())
            .append("cDPD", getCDPD())
            .append("pDJG", getPDJG())
            .append("hPHM", getHPHM())
            .append("cPYS", getCPYS())
            .append("hPZL", getHPZL())
            .append("rLZL", getRLZL())
            .append("cO2JG", getCO2JG())
            .append("cOCO2", getCOCO2())
            .append("hCCO2", getHCCO2())
            .append("nOCO2", getNOCO2())
            .append("cOJG", getCOJG())
            .append("hCJG", getHCJG())
            .append("nOJG", getNOJG())
            .append("bTGDJG", getBTGDJG())
            .append("lGMHD", getLGMHD())
            .append("cOXZ", getCOXZ())
            .append("nOXZ", getNOXZ())
            .append("bTGDXZ", getBTGDXZ())
            .append("hDXZ", getHDXZ())
            .append("cLSD", getCLSD())
            .append("cLJSD", getCLJSD())
            .append("vSP", getVSP())
            .append("fS", getFS())
            .append("fX", getFX())
            .append("hJWD", getHJWD())
            .append("sD", getSD())
            .append("dQY", getDQY())
            .append("gJXXBH", getGJXXBH())
            .append("tP1", getTP1())
            .append("tP2", getTP2())
            .append("sP1", getSP1())
            .toString();
    }
}
