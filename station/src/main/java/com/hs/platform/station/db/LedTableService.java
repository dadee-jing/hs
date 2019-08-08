package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.LedTableInfo;
import com.hs.platform.station.util.DbUtil;
import com.hs.platform.station.util.InnerTableParam;

import java.util.List;

public class LedTableService {

    public static String getLedInfoListByTime(String json, String sequenceTag) {

        String sql = "SELECT led_id, ip_address, led_content, led_color, led_index, led_start_time, led_end_time FROM led_table_info WHERE led_start_time <= ? AND led_end_time >= ?";
        Gson gson = new Gson();
        InnerTableParam innerTable = gson.fromJson(json, InnerTableParam.class);
        List<LedTableInfo> resultList = DbUtil.getLedInfoListByTime(sql, innerTable.getDate());
        String jsonContent = gson.toJson(resultList);

        System.out.println("jsonContent:" + jsonContent);
        return jsonContent;

    }


}
