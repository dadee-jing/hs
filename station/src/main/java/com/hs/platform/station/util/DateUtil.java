package com.hs.platform.station.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
