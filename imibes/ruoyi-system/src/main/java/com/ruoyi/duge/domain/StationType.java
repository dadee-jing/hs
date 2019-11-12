package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 站点类型表 station_type
 * 
 * @author zn
 * @date 2019-11-1
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StationType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer typeId;
	/** 父类型id */
	private Integer parentId;
	/** 祖类型id */
	private String ancestors;
	/** 类型名称 */
	private String typeName;
	/** 排序 */
	private String orderNum;

	private Timestamp createTime;

	private String createBy;
	private String status;
	private String delFlag;
}
