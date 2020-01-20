package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

/**
 * 站点类型表 station_type
 * 
 * @author zn
 * @date 2019-11-1
 */

@Data
public class StationType extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 部门ID */
	private Long typeId;

	/** 父部门ID */
	private Long parentId;

	/** 祖级列表 */
	private String ancestors;

	/** 部门名称 */
	private String typeName;

	/** 显示顺序 */
	private String orderNum;

	/** 负责人 */
	private String leader;

	/** 部门状态:0正常,1停用 */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	/** 父部门名称 */
	private String parentName;

	/** 经度 */
	private String longitude;

	/** 纬度 */
	private String latitude;

}
