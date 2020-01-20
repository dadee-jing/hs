package com.ruoyi.duge.third.trafficPolice.overspeed;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data
public class OverSpeed {
    private String stationId;//站点id
    private Date shootDate;//    a违法时间
    private String plateType;//    b号牌种类
    private String truckNumber;//    c车牌号
    private String address;//    d违法地点
    private String  code; // e违法代号
    private String speed;//    f车辆速度
    private Integer speedLimit;//    g车牌限速
    private String overRate;//    h超速比例
//    k采集机关
//    x数据来源         02 卡口
//    y设备厂家简称     泓胜
//    z图片个数         z11
    private String colorStr;
    private String fileName;
}
