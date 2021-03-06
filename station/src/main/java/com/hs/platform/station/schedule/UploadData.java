package com.hs.platform.station.schedule;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.dao.WeightDataRepository;
import com.hs.platform.station.persistence.local.entity.ConfigData;
import com.hs.platform.station.persistence.local.entity.WeightData;
import com.hs.platform.station.persistence.remote.entity.RemoteWeightDataData;
import com.hs.platform.station.third.common.utils.StringUtils;
import com.hs.platform.station.util.ImageDownloadUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Component
public class UploadData {

    private static final Logger logger = LoggerFactory.getLogger(UploadData.class);
    private final ConfigDataRepository configDataRepository;
    private final WeightDataRepository weightDataRepository;
    private final ImageDownloadUtil imageDownloadUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public UploadData(ConfigDataRepository configDataRepository,
                      WeightDataRepository weightDataRepository,
                      ImageDownloadUtil imageDownloadUtil) {
        this.configDataRepository = configDataRepository;
        this.weightDataRepository = weightDataRepository;
        this.imageDownloadUtil = imageDownloadUtil;
    }

    /**
     * 本地库存储到汇聚中心库
     */
    @Scheduled(fixedRate = 5000)
    public void uploadDbData() {
        if ("1".equals(getDbConfigValue("do_upload_tag"))) {
            doUploadDbData();
        }
    }

    public void doUploadDbData() {
        // 下载20秒前数据，保证图片视频收集
        try {
/*            Date readyDate = new Date(new Date().getTime() - 1000 * NumberUtils.toInt(getDbConfigValue("data_upload_delay"), 30));
            List<WeightData> weightDataList = weightDataRepository.findTop5ByUploadTagIsNotAndWeightingDateBeforeOrderByUploadTagAscIdAsc(1, readyDate);*/
            List<WeightData> weightDataList = weightDataRepository.findTop5ByUploadTagIsNotOrderByUploadTagDescIdAsc(1);
            if (null != weightDataList && weightDataList.size() != 0) {
                long startTime = System.currentTimeMillis();
                logger.info("to upload size" + weightDataList.size());
                String remoteUploadUrl = getDbConfigValue("remote_weight_upload_url");
                if(StringUtils.isBlank(remoteUploadUrl)){
                    logger.error("远程上传车辆数据url未配置");
                }
                weightDataList.forEach(weightData -> {
                    boolean successTag = true;
                    try {
                        long startTime2 = System.currentTimeMillis();
                        RemoteWeightDataData remoteWeightDataData = new RemoteWeightDataData();
                        BeanUtils.copyProperties(remoteWeightDataData, weightData);
                        remoteWeightDataData.setId(null);
                        remoteWeightDataData.setUploadTag(0);
                        // 同步提交文件
                        HashMap<String,String> fileInfo = imageDownloadUtil.submitDownloadTask(weightData);
                        updateRemoteWeightData(fileInfo,remoteWeightDataData);
                        // 文本数据
                        successTag = post(remoteUploadUrl, remoteWeightDataData);
                        long endTime2 = System.currentTimeMillis();
                        if(successTag){
                            logger.info("upload " + weightData.getTruckNumber() + ",cost:" +(endTime2 - startTime2));
                        }else{
                            logger.info("upload fail");
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        successTag = false;
                        logger.error("upload fail " + weightData.getTruckNumber());
                    }
                    weightData.setUploadTag(successTag ? 1 : 2);
                    weightDataRepository.save(weightData);
                });
                long endTime = System.currentTimeMillis();
                logger.info("end upload size" + weightDataList.size() + ",uploadDbData cost:" + (endTime - startTime));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void updateRemoteWeightData(HashMap<String, String> fileInfo, RemoteWeightDataData remoteWeightDataData) {
        Iterator<Map.Entry<String, String>> entries = fileInfo.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            //logger.info("updateRemoteWeightData key = " + entry.getKey() + ", value = " + entry.getValue());
            if(entry.getKey().contains("scene") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpPriorHead("");
            }
            else if(entry.getKey().contains("back") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpTail("");
            }
            else if(entry.getKey().contains("left") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpHead("");
            }
            else if(entry.getKey().contains("right") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpAxle("");
            }
            else if(entry.getKey().contains("plate") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpPlate("");
            }
            else if(entry.getKey().contains("video") && "0".equals(entry.getValue())){
                remoteWeightDataData.setFtpFullView("");
            }
            else if("lwhScenePath".equals(entry.getKey())){
                remoteWeightDataData.setLwhScenePath(entry.getValue());
            }
        }
    }

    Boolean post(String url, RemoteWeightDataData weightData) {
        try {
            // 实例化httpClient
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 实例化post方法
            HttpPost httpPost = new HttpPost(url);
            // 结果
            CloseableHttpResponse response = null;
            String content = "";
            // 添加请求头
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 数据转换json格式

            // 将参数给post方法
            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(weightData), "utf-8"));
            // 执行post方法
            response = httpclient.execute(httpPost);
            // 处理服务器端响应
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            JsonNode jsonNode = objectMapper.readTree(content);
            return null != jsonNode.findValue("code") && jsonNode.findValue("code").intValue() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    private String getDbConfigValue(String key) {
        try {
            ConfigData configData = configDataRepository.findFirstByKey(key);
            if (null != configData) {
                return configData.getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
