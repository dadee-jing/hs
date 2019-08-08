package com.hs.platform.station.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static String timestamp2DateString(Timestamp param) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tsStr = sdf.format(param.getTime());
        return tsStr;
    }

    public static String timestamp2TimeString(Timestamp param) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        tsStr = sdf.format(param.getTime());
        return tsStr;
    }

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();//当前的毫秒
        System.out.println(currentTime);
        long timeOutTime = currentTime + 10000;
        System.out.println(timeOutTime);
    }
}
