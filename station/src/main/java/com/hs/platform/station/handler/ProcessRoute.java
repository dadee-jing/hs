package com.hs.platform.station.handler;

import com.hs.platform.station.db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessRoute {

    private static Logger LOGGER = LoggerFactory.getLogger(ProcessRoute.class);

    public static ProcessResult processRequest(int action, String requestJson, String sequenceTag) {
        ProcessResult result = new ProcessResult();
        int replyAction = 0;
        String applySuccessJson = "{\"Result\":0}";
        String applyFailJson = "{\"Result\":1}";
        String resultJson = applyFailJson;
        if (action == 0x01) {//登录
            replyAction = 0x10000001;
            resultJson = applySuccessJson;
        }
        if (action == 0x02) {//称重信息
            replyAction = 0x10000002;
            try {
                WeightService.insert(requestJson, sequenceTag);
                resultJson = applySuccessJson;
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                resultJson = applyFailJson;
            }
        }
        if (action == 0x03) {//设备信息
            replyAction = 0x10000003;
            try {
                DeviceService.insert(requestJson, sequenceTag);
                resultJson = applySuccessJson;
            } catch (Exception e) {
                resultJson = applyFailJson;
            }
        }
        if (action == 0x04) {//获取中心基本表版本信息请求
            replyAction = 0x10000004;
            resultJson = BasicTableService.getTableListByTime(requestJson, sequenceTag);
        }
        if (action == 0x05) {//获取轴数限重信息请求包体
            replyAction = 0x10000005;
            resultJson = AlexTableService.getAlexByTime(requestJson, sequenceTag);
        }
        if (action == 0x0A) {//心跳包
            replyAction = 0x1000000A;
            resultJson = applySuccessJson;
        }
        if (action == 0x0B) {//获取车型限重数据
            replyAction = 0x1000000B;
            resultJson = VehicleTableService.getVehicleListByTime(requestJson, sequenceTag);
        }
        if (action == 0x0C) {//获取限重模式
            replyAction = 0x1000000C;
            resultJson = WeightTypeService.getWeightTypeListByTime(requestJson, sequenceTag);
        }
        if (action == 0x07) {//获取车型限重数据
            replyAction = 0x10000007;
            resultJson = LedTableService.getLedInfoListByTime(requestJson, sequenceTag);
        }
        if (action == 0x08) {//长链接传输文件
            replyAction = 0x10000008;
            ImageStorageService.imageStorage(requestJson, sequenceTag);
            resultJson = applySuccessJson;
        }
        result.setReplyAction(replyAction);
        result.setResultJson(resultJson);
        return result;
    }

}
