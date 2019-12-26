package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 */
@Data
public class StationInfoWithDeviceInfoList extends StationInfo {

	private List<StationDeviceInfoVo> deviceList;

}
