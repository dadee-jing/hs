package com.hs.platform.station.upload_foshan.model;

import com.hs.platform.station.upload_foshan.domaim.DeviceData;
import com.hs.platform.station.upload_foshan.domaim.DeviceHealthState;
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
