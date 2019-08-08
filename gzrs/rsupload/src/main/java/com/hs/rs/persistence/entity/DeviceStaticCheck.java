package com.hs.rs.persistence.entity;


import lombok.*;
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
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStaticCheck {
	/** 点位编号 */
	private String jzbh;
	/** 遥测线编号 */
	private String ycxbh;
	/** 检查开始时间 */
	private Date jckssj;
	/** 标气类别 */
	private String bqlb;
	/** CO2标准值 */
	private BigDecimal co2bzz;
	/** CO标准值 */
	private BigDecimal cobzz;
	/** NO标准值 */
	private BigDecimal nobzz;
	/** CO2测量值 */
	private BigDecimal co2clz;
	/** CO测量值 */
	private BigDecimal coclz;
	/** NO测量值 */
	private BigDecimal noclz;
}
