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
    private String station_code; // 站点编码
    private String station_name; // 站点名称
    private Integer station_status; // 站点设备状态（0:异常 1:正常;规则:有任何一个站点部件异常 整个站点判定为异常）
    private Integer device_id; // 设备ID
    private String device_name; // 设备名称
    private Integer device_status; // 设备状态(0异常 1正常)
    private String error_code; // 错误代码
    private String error_msg; // 错误信息
    private String report_date; // 检测时间
}
