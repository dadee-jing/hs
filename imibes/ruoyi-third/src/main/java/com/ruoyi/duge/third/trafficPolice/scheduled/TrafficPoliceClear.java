package com.ruoyi.duge.third.trafficPolice.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
    SimpleDateFormat sdfPeccancy = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(TrafficPoliceClear.class);
    @Scheduled(cron = "${deleteTrafficPolice3DaysAgo.scheduled}")
    private void doTask(){
        deletePasscar3DaysAgo();
        deleteOverSpeed3DaysAgo();
        deletePeccancy3DaysAgo();
    }

    private void deletePasscar3DaysAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        String passcarPath="/sharedata/ftp/passcar/"+sdf.format(calendar.getTime())+"/"+sdfDay.format(calendar.getTime());
        log.info("delete the passcar file of "+passcarPath);
        File file=new File(passcarPath);
        delFile(file);
    }

    private void deleteOverSpeed3DaysAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        String peccancyczPath="/sharedata/ftp/peccancycz/"+sdf.format(calendar.getTime())+"/"+sdfDay.format(calendar.getTime());
        log.info("delete the peccancycz file of "+peccancyczPath);
        File file=new File(peccancyczPath);
        delFile(file);
    }

    private void deletePeccancy3DaysAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        String peccancyPath="/sharedata/ftp/peccancy/"+sdf.format(calendar.getTime())+"/"+sdfDay.format(calendar.getTime());
        log.info("delete the peccancy file of "+peccancyPath);
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