package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.VehicleTableInfo;
import com.hs.platform.station.util.DbUtil;
import com.hs.platform.station.util.InnerTableParam;

import java.util.List;

public class VehicleTableService {

    public static String getVehicleListByTime(String json, String sequenceTag) {
        String sql = "SELECT vehicle_id,vehicle_type,alex_num,vehicle_limit,vehicle_starttime,vehicle_endtime FROM vehicle_table_info WHERE ? >= vehicle_starttime AND ? <= vehicle_endtime";
        Gson gson = new Gson();
        InnerTableParam innerTable = gson.fromJson(json, InnerTableParam.class);
        List<VehicleTableInfo> resultList = DbUtil.getVehicleListByTime(sql, innerTable.getDate());
        String jsonContent = gson.toJson(resultList);
        return jsonContent;

    }

}
