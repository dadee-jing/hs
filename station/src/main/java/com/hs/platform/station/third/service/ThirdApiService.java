package com.hs.platform.station.third.service;

import com.hs.platform.station.third.model.BaseEquipmentStatusRequest;
import com.hs.platform.station.third.model.BaseThirdApiResponse;
import com.hs.platform.station.third.model.BaseVehicleDataRequest;

public interface ThirdApiService {

    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request);

    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request);
}
