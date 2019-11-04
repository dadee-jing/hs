package com.hs.platform.station.schedule;

import com.hs.platform.station.entity.FTPReUploadInfo;
import com.hs.platform.station.util.FTPClientUtil;
import com.hs.platform.station.util.ImageDownloadUtil;
import com.hs.platform.station.util.SFTP.FileSystemServiceImpl;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ReUploadFailedData {

    @Autowired
    private FileSystemServiceImpl fileSystemService;

    private static Logger LOGGER = LoggerFactory.getLogger(ReUploadFailedData.class);
    public static LinkedList<FTPReUploadInfo> ftpReUploadQueue = new LinkedList<>();
    //改用限定大小的list
    /**
     * 重新上传新流向失败的数据
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void reUploadNewlxData() {
        if(ftpReUploadQueue != null && ftpReUploadQueue.size() != 0){
            LOGGER.info("reUploadStart:" + ftpReUploadQueue.size());
            //每次处理10条
            int i = 0;
            ImageDownloadUtil.checkNewlxFtpConnect();
            Iterator<FTPReUploadInfo> iterator = ftpReUploadQueue.iterator();
            while(iterator.hasNext()){
                FTPReUploadInfo info= iterator.next();
                int result = FTPClientUtil.reDoftpToFtp(info.getSourcePath(),info.getTargetPath(),
                        ImageDownloadUtil.newlxFtpClient, fileSystemService);
                if(result == 0){
                    iterator.remove();
                }
                i++;
                if(i > 10){
                    break;
                }
            }

            LOGGER.info("reUploadEnd:" + ftpReUploadQueue.size());
        }
    }

}
