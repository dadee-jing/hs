package com.hs.platform.station.schedule;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Date;

@Component
public class DeleteFileTask {

    private static Logger log = LoggerFactory.getLogger(DeleteFileTask.class);

    @Scheduled(cron = "0 0 0/5 * * ?")
    private void deleteTimeOutFile(){
        //每5小时删除一次创建时间大于x小时的文件
        //D:/Camera/Picture/20191107/20191107_1410/1405123_1_side.jpg
        log.info("deleteTimeOutFile start");
        String rootPath = "D:/Camera/Picture/";
        int timeout = 12;
        try {
            File rootFile = new File(rootPath);
            File fileList[] = rootFile.listFiles();
            String nowDay = DateFormatUtils.format(new Date(), "yyyyMMdd");
            String nowHour = DateFormatUtils.format(new Date(), "yyyyMMddHH");
            //天数目录，没有子目录的删掉。二级时间目录，超过x小时的删掉
            for (File file : fileList) {
                String dayPath = rootPath + file.getName();
                File dayFile = new File(dayPath);
                File dayFileList[] = dayFile.listFiles();
                if (dayFileList.length == 0) {
                    delAllFile(new File(dayPath));
                    log.info("delete dayFile:" + dayPath);
                }
                else{
                    for (File secondFile : dayFileList) {
                        String secondFileName = secondFile.getName();
                        String fileHour = secondFileName.substring(0, secondFileName.length() - 2)
                                .replaceAll("_", "");
                        //超过x小时的删除
                        if (Integer.valueOf(nowHour) - Integer.valueOf(fileHour) > timeout) {
                            delAllFile(new File(dayPath + "/" + secondFileName));
                            log.info("delete secondFile:" + dayPath + "/" + secondFileName);
                        }
                    }

                }
            }

        }catch (Exception e){
            log.error("deleteTimeOutFile fail" ,e);
        }
        log.info("deleteTimeOutFile end");
    }

    /**
     * 删除文件或文件夹下的所有文件
     * @param directory
     */
    public static void delAllFile(File directory) {
        if (!directory.isDirectory()) {
            directory.delete();
        } else {
            File[] files = directory.listFiles();
            // 空文件夹
            if (files.length == 0) {
                directory.delete();
                return;
            }
            // 删除子文件夹和子文件
            for (File file : files) {
                if (file.isDirectory()) {
                    delAllFile(file);
                } else {
                    file.delete();
                }
            }
            // 删除文件夹本身
            directory.delete();
        }
    }
}
