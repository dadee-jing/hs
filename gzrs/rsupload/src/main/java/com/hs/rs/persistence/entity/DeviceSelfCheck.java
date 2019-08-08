package com.hs.rs.persistence.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 遥测设备自检过程数据表 rs_device_self_check
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
public class DeviceSelfCheck extends BaseEntity
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
	/** CO2标准值 */
	private BigDecimal cO2BZZ;
	/** CO标准值 */
	private BigDecimal cOBZZ;
	/** 1,3-丁二烯标准值 */
	private BigDecimal dEXBZZ;
	/** 丙烷标准值 */
	private BigDecimal bWBZZ;
	/** NO标准值 */
	private BigDecimal nOBZZ;
	/** CO2测量值 */
	private BigDecimal cO2CLZ;
	/** CO测量值 */
	private BigDecimal cOCLZ;
	/** 1,3-丁二烯测量值 */
	private BigDecimal dEXCLZ;
	/** 丙烷测量值 */
	private BigDecimal bWCLZ;
	/** NO测量值 */
	private BigDecimal nOCLZ;
	/** 烟度片1标准值 */
	private BigDecimal yDP1BZZ;
	/** 烟度片2标准值 */
	private BigDecimal yDP2BZZ;
	/** 烟度片3标准值 */
	private BigDecimal yDP3BZZ;
	/** 烟度片4标准值 */
	private BigDecimal yDP4BZZ;
	/** 烟度片5标准值 */
	private BigDecimal yDP5BZZ;
	/** 烟度片1测量值 */
	private BigDecimal yDP1CLZ;
	/** 烟度片2测量值 */
	private BigDecimal yDP2CLZ;
	/** 烟度片3测量值 */
	private BigDecimal yDP3CLZ;
	/** 烟度片4测量值 */
	private BigDecimal yDP4CLZ;
	/** 烟度片5测量值 */
	private BigDecimal yDP5CLZ;

	private Integer upLoadStatus;
}
