package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.BasicTableInfo;
import com.hs.platform.station.util.DbUtil;
import com.hs.platform.station.util.InnerTableParam;

import java.util.List;

public class BasicTableService {

    public static String getTableListByTime(String json, String sequenceTag) {

        String sql = "SELECT table_type, table_version FROM basic_table_info WHERE table_date <= ?";
        Gson gson = new Gson();
        InnerTableParam innerTable = gson.fromJson(json, InnerTableParam.class);
        List<BasicTableInfo> resultList = DbUtil.getTableListByTime(sql, innerTable.getDate());
        String jsonContent = gson.toJson(resultList);
        return jsonContent;
    }


}
