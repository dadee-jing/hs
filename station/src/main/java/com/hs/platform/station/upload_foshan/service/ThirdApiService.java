package com.hs.platform.station.upload_foshan.service;


import com.hs.platform.station.upload_foshan.model.BaseEquipmentStatusRequest;
import com.hs.platform.station.upload_foshan.model.BaseThirdApiResponse;
import com.hs.platform.station.upload_foshan.model.BaseVehicleDataRequest;

public interface ThirdApiService {

    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request);

    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request);
}
