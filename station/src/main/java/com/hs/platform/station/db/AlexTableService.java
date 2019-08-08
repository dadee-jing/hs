package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.AlexTableInfo;
import com.hs.platform.station.util.DbUtil;
import com.hs.platform.station.util.InnerTableParam;

import java.util.List;

public class AlexTableService {

    public static String getAlexByTime(String json, String sequenceTag) {
        String sql = "SELECT alex_id, alex_num, alex_limit, alex_start_time, alex_end_time FROM alex_table_info WHERE ? >= alex_start_time AND ?<= alex_end_time";
        Gson gson = new Gson();
        InnerTableParam innerTable = gson.fromJson(json, InnerTableParam.class);
        List<AlexTableInfo> resultList = DbUtil.getAlexListByTime(sql, innerTable.getDate());
        String jsonContent = gson.toJson(resultList);
        return jsonContent;

    }


}
