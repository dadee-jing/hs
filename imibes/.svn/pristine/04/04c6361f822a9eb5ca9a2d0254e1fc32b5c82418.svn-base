package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.DeviceHealthState;

import java.util.List;

/**
 * 设备健康状态 服务层
 */
public interface IDeviceHealthStateService {

    // 根据站点编码查询站点所有设备编号
    List<Integer> findDeviceIdListByStationCode(String stationCode);

    // 根据设备编号集合查询最新的设备状态信息
    List<DeviceHealthState> findNewestDeviceStateByDeviceIdList(List<Integer> deviceIdList);

}
