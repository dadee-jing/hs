package com.ruoyi.duge.third.trafficPolice.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 保底删除三天前交警的图片
 */
@Component
public class TrafficPoliceClear {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
    SimpleDateFormat sdfPeccancy = new SimpleDateFormat("yyyy-MM-dd");
    @Scheduled(cron = "0 0 1 * * ?")
    private void scheduled(){
        deletePasscar3DaysAgo();
        deletePeccancy3DaysAgo();
    }
    private void deletePasscar3DaysAgo(){
        calendar.add(Calendar.DATE, -3);
        String passcarPath="/sharedata/ftp/passcar/"+sdf.format(calendar.getTime())+"/"+sdfDay.format(calendar.getTime());
        File file=new File(passcarPath);
        delFile(file);
    }
    private void deletePeccancy3DaysAgo(){
        calendar.add(Calendar.DATE, -3);
        String peccancyPath="/sharedata/ftp/peccancybak/"+sdfPeccancy.format(calendar.getTime());
        File file=new File(peccancyPath);
        delFile(file);
    }
    private boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }
}