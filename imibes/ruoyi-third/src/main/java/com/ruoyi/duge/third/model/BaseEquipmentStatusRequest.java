package com.ruoyi.duge.third.model;

import com.ruoyi.duge.domain.DeviceData;
import com.ruoyi.duge.domain.DeviceHealthState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseEquipmentStatusRequest {

    private DeviceData deviceData;

    private DeviceHealthState deviceHealthState;
}
