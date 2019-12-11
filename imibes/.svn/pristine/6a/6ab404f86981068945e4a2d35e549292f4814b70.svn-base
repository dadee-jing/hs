package com.ruoyi.duge.service.impl;

import com.ruoyi.duge.domain.DeviceHealthState;
import com.ruoyi.duge.mapper.DeviceHealthStateMapper;
import com.ruoyi.duge.service.IDeviceHealthStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 设备健康状态 服务层实现
 */
@Service
@Transactional
public class DeviceHealthStateServiceImpl implements IDeviceHealthStateService {
    @Autowired
    DeviceHealthStateMapper deviceHealthStateMapper;

    // 根据站点编码查询站点所有设备编号
    public List<Integer> findDeviceIdListByStationCode(String stationCode){
        return deviceHealthStateMapper.findDeviceIdListByStationCode(stationCode);
    }

    // 根据设备编号集合查询最新的设备状态信息
    public List<DeviceHealthState> findNewestDeviceStateByDeviceIdList(List<Integer> deviceIdList){
        return deviceHealthStateMapper.findNewestDeviceStateByDeviceIdList(deviceIdList);
    }

}
