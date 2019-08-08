package com.hs.platform.station.schedule;


import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.dao.FtpServerRepository;
import com.hs.platform.station.persistence.local.dao.WeightDataRepository;
import com.hs.platform.station.persistence.local.entity.ConfigData;
import com.hs.platform.station.persistence.local.entity.WeightData;
import com.hs.platform.station.persistence.remote.dao.RemoteWeightDataRepository;
import com.hs.platform.station.persistence.remote.entity.RemoteWeightDataData;
import com.hs.platform.station.util.ImageDownloadUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UploadData {

    private static final Logger logger = LoggerFactory.getLogger(UploadData.class);

    private final ConfigDataRepository configDataRepository;
    private final FtpServerRepository ftpServerRepository;
    private final WeightDataRepository weightDataRepository;
    private final RemoteWeightDataRepository remoteWeightDataRepository;


    @Autowired
    public UploadData(ConfigDataRepository configDataRepository,
                      FtpServerRepository ftpServerRepository,
                      WeightDataRepository weightDataRepository,
                      RemoteWeightDataRepository remoteWeightDataRepository) {
        this.configDataRepository = configDataRepository;
        this.ftpServerRepository = ftpServerRepository;
        this.weightDataRepository = weightDataRepository;
        this.remoteWeightDataRepository = remoteWeightDataRepository;
    }

    /**
     * 本地库存储到汇聚中心库
     */
    @Scheduled(cron = "${upload_task_cron}")
    public void uploadDbData() {
        if ("1".equals(getDbConfigValue("do_upload_tag"))) {
            doUploadDbData();
        }
    }

    public void doUploadDbData() {
        //logger.info("uploading db-data to data center begin");
        // 下载20秒前数据，保证图片视频收集
        Date readyDate = new Date(new Date().getTime() - 1000 * NumberUtils.toInt(getDbConfigValue("data_upload_delay"), 30));
        List<WeightData> weightDataList = weightDataRepository.findByUploadTagIsNotAndWeightingDateBeforeOrderByUploadTagAscIdAsc(1, readyDate);
        if (null != weightDataList) {
            weightDataList.forEach(weightData -> {
                boolean successTag = true;
                try {
                    RemoteWeightDataData remoteWeightDataData = new RemoteWeightDataData();
                    BeanUtils.copyProperties(remoteWeightDataData, weightData);
                    remoteWeightDataData.setId(null);
                    remoteWeightDataData.setUploadTag(0);
                    // 文本数据
                    remoteWeightDataRepository.save(remoteWeightDataData);
                    // 异步提交文件
                    ImageDownloadUtil.submitDownloadTask(weightData);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    successTag = false;
                }
                weightData.setUploadTag(successTag ? 1 : 2);
                weightDataRepository.save(weightData);
            });
        }
        //logger.info("uploading db-data to data center end");
    }

    private String getDbConfigValue(String key) {
        ConfigData configData = configDataRepository.findFirstByKey(key);
        if (null != configData) {
            return configData.getValue();
        } else {
            return null;
        }
    }
}
