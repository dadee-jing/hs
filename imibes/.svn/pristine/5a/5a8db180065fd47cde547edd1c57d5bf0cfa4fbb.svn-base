package com.ruoyi.duge.mapper;

import com.ruoyi.duge.domain.DeviceHealthState;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 设备健康状态 数据层
 */
public interface DeviceHealthStateMapper {

    // 根据站点编码查询站点所有设备编号
    @Select("SELECT device_id FROM yihualu_device_health_state WHERE station_code = #{stationCode} GROUP BY device_id")
    List<Integer> findDeviceIdListByStationCode(String stationCode);

    // 根据站点编号集合查询最新设备状态信息
    @Select("<script>" +
               "SELECT * FROM yihualu_device_health_state dgs " +
                   "WHERE device_id IN" +
                       "<foreach item='item' index='index' collection='deviceIdList' open='(' separator=',' close=')'> #{item} </foreach> " +
                    "AND " +
                        "NOT EXISTS(SELECT 1 FROM yihualu_device_health_state WHERE device_id = dgs.device_id AND report_date > dgs.report_date) " +
            "</script>")
    @Results(id = "deviceHealthStateMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "stationCode", column = "station_code"),
            @Result(property = "stationName", column = "station_name"),
            @Result(property = "stationStatus", column = "station_status"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "deviceName", column = "device_name"),
            @Result(property = "deviceStatus", column = "device_status"),
            @Result(property = "errorCode", column = "error_code"),
            @Result(property = "errorMsg", column = "error_msg"),
            @Result(property = "reportDate", column = "report_date"),
    })
    List<DeviceHealthState> findNewestDeviceStateByDeviceIdList(@Param("deviceIdList")List<Integer> deviceIdList);

}
