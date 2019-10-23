package com.hs.platform.station.upload_foshan.model;


import com.hs.platform.station.upload_foshan.domaim.WeightData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseVehicleDataRequest {

    private WeightData weightData;

    private List<WeightData> weightDataList;
}
