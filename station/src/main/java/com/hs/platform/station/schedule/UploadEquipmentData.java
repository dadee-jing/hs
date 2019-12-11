package com.hs.platform.station.schedule;

import com.google.gson.GsonBuilder;
import com.hs.platform.station.db.EquipmentService;
import com.hs.platform.station.entity.DeviceHealthState;
import com.hs.platform.station.entity.DeviceInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hs.platform.station.Constants.upload_equipment_state_url;
import static com.hs.platform.station.util.ImageDownloadUtil.station_id;

@Service
public class UploadEquipmentData {
    private static final Logger logger = LoggerFactory.getLogger(UploadEquipmentData.class);
    private final String url = upload_equipment_state_url;
    private final Integer stationId = station_id;
    private List<DeviceHealthState> deviceHealthStateList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时上传设备状态
     */
    @Scheduled(cron="${upload_equipment_status_cron}")
    public void timingUploadStatus() throws Exception {
        logger.info("uploaded equipment data start");
        // 获取所有最新设备信息
        List<DeviceInfo> deviceInfoList = getEquipmentList();
        // 处理设备信息
        processingData(deviceInfoList);
        // 上传设备状态信息
        post(url, deviceHealthStateList);
        logger.info("uploaded equipment data end size:" + deviceInfoList.size());
    }

    /**
     * 获取指定站点所有设备信息
     * @return 设备信息
     */
    public List<DeviceInfo> getEquipmentList() {
        return EquipmentService.getEquipmentList(stationId);
    }

    /**
     * 处理post请求.
     * @param url 请求路径
     * @param params 参数
     * @return json
     */
    public String post(String url, List<DeviceHealthState> params) throws Exception {
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
        String paramsStr = new GsonBuilder().serializeNulls().create().toJson(params);
        // 将参数给post方法
        httpPost.setEntity(new StringEntity(paramsStr, "utf-8"));
        // 执行post方法
        response = httpclient.execute(httpPost);
        // 处理服务器端响应
        if (response.getStatusLine().getStatusCode() == 200) {
            content = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        return content;
    }

    /**
     * 处理设备信息
     * @param equipmentList
     */
    public void processingData(List<DeviceInfo> equipmentList) throws Exception {
        deviceHealthStateList = new ArrayList<>();
        DeviceHealthState DeviceHealthState = null;
        // 处理设备状态信息
        for (DeviceInfo equipment : equipmentList) {
            DeviceHealthState = new DeviceHealthState();
            DeviceHealthState.setStationCode(equipment.getStationCode());
            DeviceHealthState.setStationName(equipment.getStationName());
            DeviceHealthState.setDeviceId(equipment.getId());
            DeviceHealthState.setDeviceName(equipment.getDeviceName());
            //DeviceHealthState.setDeviceStatus(checkStatus(equipment.getIpAddress()));
            // 先全部返回1
            DeviceHealthState.setDeviceStatus(1);
            deviceHealthStateList.add(DeviceHealthState);
        }
        // 检测站点设备状态
        boolean allOk = deviceHealthStateList.stream().allMatch(deviceHealthState -> deviceHealthState.getDeviceStatus() == 1);
        // 生成检测时间
        String checkDate = dateFormat.format(new Date());
        // 遍历设置字段属性
        deviceHealthStateList.forEach(deviceHealthState ->{
            deviceHealthState.setReportDate(checkDate);
            deviceHealthState.setStationStatus(allOk ? 1 : 0);
        });

    }

    /**
     * Ping IP地址检测状态
     * @param ipAddress 设备IP
     * @return 检测结果（0异常 1正常）
     * @throws Exception
     */
    public int checkStatus(String ipAddress) throws Exception {
        int timeOut = 3000;  // 超时时间
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        // 日志处理
        if (status) {
            // 连通成功
            logger.debug(ipAddress + ":连通成功");
        } else {
            // 通讯失败
            logger.error(ipAddress + ":通讯失败");
        }
        return status ? 1 : 0;
    }
}
