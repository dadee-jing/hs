package com.ruoyi.duge.third.trafficPolice.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 保底删除三天前交警的图片
 */
@Component
public class TrafficPoliceClear {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Scheduled(cron = "0 0 1 * * ?")
    private void deletePasscar3DaysAgo(){
        calendar.add(Calendar.DATE, -3);
        String passcarPath="/sharedata/ftp/passcarbak/"+sdf.format(calendar.getTime());
        new File(passcarPath).delete();
        String peccancyPath="/sharedata/ftp/peccancybak/"+sdf.format(calendar.getTime());
        new File(peccancyPath).delete();
    }
}
