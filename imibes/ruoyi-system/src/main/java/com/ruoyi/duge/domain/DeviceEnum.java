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
 * 站点表 device_enum
 * 
 * @author ruoyi
 * @date 2018-11-22
 */
@Data
public class DeviceEnum extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String key;
	private String value;
	private Integer type;

}
