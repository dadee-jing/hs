package com.hs.platform.station.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 设备健康状态表
 */
@Getter
@Setter
@ToString
public class DeviceHealthState implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id; // 主键
    private String stationCode; // 站点编码
    private String stationName; // 站点名称
    private Integer stationStatus; // 站点设备状态（0:异常 1:正常;规则:有任何一个站点部件异常 整个站点判定为异常）
    private Integer deviceId; // 设备ID
    private String deviceName; // 设备名称
    private Integer deviceStatus; // 设备状态(0异常 1正常)
    private String errorCode; // 错误代码
    private String errorMsg; // 错误信息
    private String reportDate; // 检测时间
}
