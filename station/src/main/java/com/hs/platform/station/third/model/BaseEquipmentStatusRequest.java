package com.hs.platform.station.third.model;

import com.hs.platform.station.entity.DeviceHealthState;
import com.hs.platform.station.third.domain.DeviceData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseEquipmentStatusRequest {

    private DeviceData deviceData;

    private DeviceHealthState deviceHealthState;
}
