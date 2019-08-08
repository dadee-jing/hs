package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.DeviceEntity;
import com.hs.platform.station.util.DbUtil;

public class DeviceService {

    public static void insert(String json, String sequenceTag) {
        Gson gson = new Gson();
        DeviceEntity deviceBean = gson.fromJson(json, DeviceEntity.class);
        String sql = "insert into	device_data(check_date, device_type, check_id, ip_address, check_state) values(?,?,?,?,?)";
        DbUtil.insertDevice(sql, deviceBean, sequenceTag);
    }
}
