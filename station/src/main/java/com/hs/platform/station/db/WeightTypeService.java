package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.WeightTypeTableInfo;
import com.hs.platform.station.util.DbUtil;
import com.hs.platform.station.util.InnerTableParam;

import java.util.List;

public class WeightTypeService {
    public static String getWeightTypeListByTime(String json, String sequenceTag) {

        String sql = "SELECT weight_type FROM weight_type_table_info WHERE weight_type_date <=?";
        Gson gson = new Gson();
        InnerTableParam innerTable = gson.fromJson(json, InnerTableParam.class);
        List<WeightTypeTableInfo> resultList = DbUtil.getWeightTypeListByTime(sql, innerTable.getDate());
        String jsonContent = gson.toJson(resultList.get(0), WeightTypeTableInfo.class);
        return jsonContent;
    }
}
