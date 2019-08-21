package com.hs.platform.station.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备表
 */
@Getter
@Setter
@ToString
public class DeviceInfo{

    private Integer id; // 设备ID
    private Integer stationId; // 站点ID
    private String stationCode; // 站点编码
    private String stationName; // 站点名称
    private String deviceName; // 设备名称
    private String ipAddress; // 设备IP
    private String port; // 设备端口号
}
