package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StationInfoWithDeviceInfoList extends StationInfo {

	private List<StationDeviceInfoVo> deviceList;

}
