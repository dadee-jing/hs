package com.hs.platform.station.schedule;

import com.hs.platform.station.StationApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import static com.hs.platform.station.util.DbUtil.getConfigValue;
import static com.hs.platform.station.util.WeightAndLWHContainer.lwhLatestTimeSecond;


@Component
public class ReStartProgram {

    private static Logger LOGGER = LoggerFactory.getLogger(ReStartProgram.class);
    SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String restart_p7000 = getConfigValue("restart_p7000");

    /**
     * 重启p7000
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void restartP7000() {
        if ("1".equals(restart_p7000)) {
            LOGGER.info("ReStartP7000 start");
            //超过5分钟没外廓请求重启外廓程序
            try {
                long nowSecond = System.currentTimeMillis() / 1000;
                if (nowSecond - lwhLatestTimeSecond.get() > 300) {
                    LOGGER.info("doReStartP7000," + nowSecond + "," + lwhLatestTimeSecond.get());
                    StationApplication.client.disconnect();
                    String batPath = "D:/deploy/batch/restart.bat";
                    callCmd(batPath);
                    Thread.sleep(100);
                    StationApplication.client.reactiveScheduled();
                }
            } catch (Exception e) {
                LOGGER.info("ReStartP7000 error", e);
            }
            LOGGER.info("ReStartP7000 end");
        }
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void restartStation() {
        if ("1".equals(restart_p7000)) {
            LOGGER.info("restartStation start");
            try {
                long nowSecond = System.currentTimeMillis() / 1000;
                if (nowSecond - lwhLatestTimeSecond.get() > (60 * 15)) {
                    LOGGER.info("doReStartStation," + nowSecond + "," + lwhLatestTimeSecond.get());
                    String batPath = "D:/deploy/batch/restartStation.bat";
                    callCmd(batPath);
                }
            } catch (Exception e) {
                LOGGER.info("restartStation error", e);
            }
            LOGGER.info("restartStation end");
        }
    }


    private static void callCmd(String locationCmd) {
        StringBuilder sb = new StringBuilder();
        try {
            Process child = Runtime.getRuntime().exec(locationCmd);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                LOGGER.info("callCmd fail a", e);
            }
            LOGGER.info("result:" + sb.toString());
            LOGGER.info("callCmd execute finished");
        } catch (IOException e) {
            LOGGER.info("callCmd fail b", e);
        }
    }
}
