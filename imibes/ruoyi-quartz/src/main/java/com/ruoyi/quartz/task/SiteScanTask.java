package com.ruoyi.quartz.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.httphelper.HttpCustomClient;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.service.IWeightDataMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("siteScanTask")
public class SiteScanTask {

    private final HttpCustomClient httpCustomClient;
    private final ObjectMapper objectMapper;
    private final IWeightDataMapperService weightDataMapperService;
    private final IConfigDataService configDataService;
    private static final Logger log = LoggerFactory.getLogger(SiteScanTask.class);
    private Map<String, String> header;
    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    @Autowired
    public SiteScanTask(IWeightDataMapperService weightDataMapperService,
                        IConfigDataService configDataService,
                        HttpCustomClient httpCustomClient,
                        ObjectMapper objectMapper) {
        this.weightDataMapperService = weightDataMapperService;
        this.configDataService = configDataService;
        this.httpCustomClient = httpCustomClient;
        this.header = new HashMap<>();
        this.header.put("content-type", CONTENT_TYPE);
        this.objectMapper = objectMapper;
    }

    @Transactional(rollbackFor = NullPointerException.class)
    public void scanVehicleData() {
        log.info("inner transport data start");
        String url = configDataService.getConfigValue("center_url");
        List<WeightData> weightDataList = weightDataMapperService.selectNotUploadDataList();
        if (null != weightDataList) {
            weightDataList.forEach(weightData -> {
                boolean successTag = true;

                Long id = weightData.getId();
                try {
                    weightData.setId(null);
                    weightData.setUploadTag(0);
                    String jsonStr = objectMapper.writeValueAsString(weightData);
                    String response = httpCustomClient.doPost(url, header, jsonStr);
                    AjaxResult innerResponse = objectMapper.readValue(response, AjaxResult.class);
                    if (null == innerResponse.get("code") ||
                            0 != (Integer) (innerResponse.get("code"))) {
                        successTag = false;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    successTag = false;
                }
                weightData.setId(id);

                weightData.setUploadTag(successTag ? 1 : 2);
                weightDataMapperService.updateData(weightData);
            });
        }
        log.info("scan vehicle data end");
    }
}
