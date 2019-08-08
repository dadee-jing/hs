package com.ruoyi.quartz.task;

import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.duge.domain.DeviceData;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IDeviceDataService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.guangdong.service.GuandongApiService;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("scanTask")
public class ScanTask {

    private final IWeightDataMapperService weightDataMapperService;
    private final IDeviceDataService deviceDataService;
    private final GuandongApiService guandongApiService;

    private static final Logger log = LoggerFactory.getLogger(ScanTask.class);

    @Autowired
    public ScanTask(IWeightDataMapperService weightDataMapperService,
                    IDeviceDataService deviceDataService,
                    GuandongApiService guandongApiService) {
        this.weightDataMapperService = weightDataMapperService;
        this.deviceDataService = deviceDataService;
        this.guandongApiService = guandongApiService;
    }

    public void scanDeviceData() {
        log.info("scan device data start");

        List<DeviceData> deviceDataList = deviceDataService.selectNotUploadDataList();
        if (null != deviceDataList) {
            deviceDataList.forEach(deviceData -> {
                BaseEquipmentStatusRequest baseEquipmentStatusRequest = BaseEquipmentStatusRequest.builder()
                        .deviceData(deviceData)
                        .build();
                BaseThirdApiResponse baseThirdApiResponse =
                        guandongApiService.submitEquipmentStatus(baseEquipmentStatusRequest);
                if (BusinessStatus.SUCCESS == baseThirdApiResponse.getBusinessStatus()) {
                    deviceData.setUploadTag(1);
                } else {
                    deviceData.setUploadTag(2);
                }
                deviceDataService.updateData(deviceData);
            });
        }
        log.info("scan device data end");
    }

    public void scanVehicleData() {
        log.info("scan vehicle data start");
        List<WeightData> weightDataList = weightDataMapperService.selectNotUploadDataList();
        if (null != weightDataList) {
            weightDataList.forEach(weightData -> {
                BaseVehicleDataRequest baseVehicleDataRequest = BaseVehicleDataRequest.builder()
                        .weightData(weightData)
                        .build();
                BaseThirdApiResponse baseThirdApiResponse =
                        guandongApiService.submitVehicleData(baseVehicleDataRequest);
                if (BusinessStatus.SUCCESS == baseThirdApiResponse.getBusinessStatus()) {
                    weightData.setUploadTag(1);
                } else {
                    weightData.setUploadTag(2);
                }
                weightDataMapperService.updateData(weightData);
            });
        }
        log.info("scan vehicle data end");
    }
}
