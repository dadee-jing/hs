package com.hs.platform.station.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
@Data
public class WeightAndLwhEntity {

    // 超重
    private int LimitMode;
    private String VehicleType;
    private int WeightingId;
    private Timestamp WeightingDate;
    private int Lane;
    private String Direction;
    private String TruckNumber;
    private String TruckCorlor;
    private BigDecimal Speed;
    private int AxleCount;
    private String AxleType;
    private BigDecimal Weight;
    private BigDecimal LimitWeight;
    private BigDecimal OverWeight;
    private BigDecimal AxleWeight1;
    private BigDecimal AxleWeight2;
    private BigDecimal AxleWeight3;
    private BigDecimal AxleWeight4;
    private BigDecimal AxleWeight5;
    private BigDecimal AxleWeight6;
    private BigDecimal AxleWeight7;
    private BigDecimal AxleWeight8;
    private String FtpHead;
    private String FtpAxle;
    private String FtpTail;
    private String FtpPriorHead;
    private String FtpPlate;
    private String FtpFullView;
    private String sequenceTag;//唯一标识称重
    private String deviceCode;//设备码
    // 超限 长宽高
    private Integer stationId;
    private Timestamp lwhDate;
    private String plate;// TruckNumber
    private String width;
    private String height;
    private String length;
    private String laneMid;
    private String laneMin;
    private String laneMax;
    private String passTime;
    private int processStatus;// 处理的当前状态 0 超重，1 超限（长宽高）
    private long timeoutMillseconds;//超时时间
    private String remarkInfo;//错误数据备注
    private boolean weightTag = false;
    private boolean sizeTag = false;
    private boolean speedTag = false;
    private int uploadTag = 0;//上传到服务器，0待上传,1已上传或不上传
    private String leftSidePath = "";//本地侧拍路径
    private String rightSidePath = "";//本地侧拍路径
    private int pathTag = 0;//2-左右拍都有
    private String lbh;//栏板高
    private String lwhScenePath = "";//外廓前抓
    private String lbl;//栏板高长
    private String lbw;//栏板宽

}
