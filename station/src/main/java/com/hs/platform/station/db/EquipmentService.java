package com.hs.platform.station.db;

import com.hs.platform.station.entity.DeviceInfo;
import com.hs.platform.station.util.DbUtil;

import java.util.List;

public class EquipmentService {

    public static List<DeviceInfo> getEquipmentList(Integer stationId) {
        String sql = "SELECT sdi.id,sdi.station_id,dsi.station_code,dsi.name AS station_name,dn.device_name,sdi.ipAddress,sdi.port " +
                        "FROM station_device_info sdi JOIN duge_station_info dsi ON sdi.station_id = dsi.id " +
                            "JOIN device_name dn ON sdi.deviceName_id = dn.id " +
                                "WHERE sdi.station_id = ?";
        List<DeviceInfo> resultList = DbUtil.getEquipmentList(sql,stationId);
        // 判断数据是否为空 如为空则抛出异常不再执行往后的操作
        if (null != resultList && !resultList.isEmpty()) {
            return resultList;
        }else{
            throw new RuntimeException("判断对象属性为空异常");
        }
    }
}
