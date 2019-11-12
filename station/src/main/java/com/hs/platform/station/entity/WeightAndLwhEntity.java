package com.hs.platform.station.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
    private int uploadTag;//上传到服务器，0待上传,1已上传或不上传
    private String sidePath;//本地侧拍路径
    private boolean pathTag = false;

    public int getUploadTag() {
        return uploadTag;
    }

    public void setUploadTag(int uploadTag) {
        this.uploadTag = uploadTag;
    }

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    public long getTimeoutMillseconds() {
        return timeoutMillseconds;
    }

    public void setTimeoutMillseconds(long timeoutMillseconds) {
        this.timeoutMillseconds = timeoutMillseconds;
    }

    public String getSequenceTag() {
        return sequenceTag;
    }

    public void setSequenceTag(String sequenceTag) {
        this.sequenceTag = sequenceTag;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public int getLimitMode() {
        return LimitMode;
    }

    public void setLimitMode(int limitMode) {
        LimitMode = limitMode;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public int getWeightingId() {
        return WeightingId;
    }

    public void setWeightingId(int weightingId) {
        WeightingId = weightingId;
    }

    public Timestamp getWeightingDate() {
        return WeightingDate;
    }

    public void setWeightingDate(Timestamp weightingDate) {
        WeightingDate = weightingDate;
    }

    public int getLane() {
        return Lane;
    }

    public void setLane(int lane) {
        Lane = lane;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getTruckNumber() {
        return TruckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        TruckNumber = truckNumber;
    }

    public String getTruckCorlor() {
        return TruckCorlor;
    }

    public void setTruckCorlor(String truckCorlor) {
        TruckCorlor = truckCorlor;
    }

    public BigDecimal getSpeed() {
        return Speed;
    }

    public void setSpeed(BigDecimal speed) {
        Speed = speed;
    }

    public int getAxleCount() {
        return AxleCount;
    }

    public void setAxleCount(int axleCount) {
        AxleCount = axleCount;
    }

    public String getAxleType() {
        return AxleType;
    }

    public void setAxleType(String axleType) {
        AxleType = axleType;
    }

    public BigDecimal getWeight() {
        return Weight;
    }

    public void setWeight(BigDecimal weight) {
        Weight = weight;
    }

    public BigDecimal getLimitWeight() {
        return LimitWeight;
    }

    public void setLimitWeight(BigDecimal limitWeight) {
        LimitWeight = limitWeight;
    }

    public BigDecimal getOverWeight() {
        return OverWeight;
    }

    public void setOverWeight(BigDecimal overWeight) {
        OverWeight = overWeight;
    }

    public BigDecimal getAxleWeight1() {
        return AxleWeight1;
    }

    public void setAxleWeight1(BigDecimal axleWeight1) {
        AxleWeight1 = axleWeight1;
    }

    public BigDecimal getAxleWeight2() {
        return AxleWeight2;
    }

    public void setAxleWeight2(BigDecimal axleWeight2) {
        AxleWeight2 = axleWeight2;
    }

    public BigDecimal getAxleWeight3() {
        return AxleWeight3;
    }

    public void setAxleWeight3(BigDecimal axleWeight3) {
        AxleWeight3 = axleWeight3;
    }

    public BigDecimal getAxleWeight4() {
        return AxleWeight4;
    }

    public void setAxleWeight4(BigDecimal axleWeight4) {
        AxleWeight4 = axleWeight4;
    }

    public BigDecimal getAxleWeight5() {
        return AxleWeight5;
    }

    public void setAxleWeight5(BigDecimal axleWeight5) {
        AxleWeight5 = axleWeight5;
    }

    public BigDecimal getAxleWeight6() {
        return AxleWeight6;
    }

    public void setAxleWeight6(BigDecimal axleWeight6) {
        AxleWeight6 = axleWeight6;
    }

    public BigDecimal getAxleWeight7() {
        return AxleWeight7;
    }

    public void setAxleWeight7(BigDecimal axleWeight7) {
        AxleWeight7 = axleWeight7;
    }

    public BigDecimal getAxleWeight8() {
        return AxleWeight8;
    }

    public void setAxleWeight8(BigDecimal axleWeight8) {
        AxleWeight8 = axleWeight8;
    }

    public String getFtpHead() {
        return FtpHead;
    }

    public void setFtpHead(String ftpHead) {
        FtpHead = ftpHead;
    }

    public String getFtpAxle() {
        return FtpAxle;
    }

    public void setFtpAxle(String ftpAxle) {
        FtpAxle = ftpAxle;
    }

    public String getFtpTail() {
        return FtpTail;
    }

    public void setFtpTail(String ftpTail) {
        FtpTail = ftpTail;
    }

    public String getFtpPriorHead() {
        return FtpPriorHead;
    }

    public void setFtpPriorHead(String ftpPriorHead) {
        FtpPriorHead = ftpPriorHead;
    }

    public String getFtpPlate() {
        return FtpPlate;
    }

    public void setFtpPlate(String ftpPlate) {
        FtpPlate = ftpPlate;
    }

    public String getFtpFullView() {
        return FtpFullView;
    }

    public void setFtpFullView(String ftpFullView) {
        FtpFullView = ftpFullView;
    }

    public Timestamp getLwhDate() {
        return lwhDate;
    }

    public void setLwhDate(Timestamp lwhDate) {
        this.lwhDate = lwhDate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLaneMid() {
        return laneMid;
    }

    public void setLaneMid(String laneMid) {
        this.laneMid = laneMid;
    }

    public String getLaneMin() {
        return laneMin;
    }

    public void setLaneMin(String laneMin) {
        this.laneMin = laneMin;
    }

    public String getLaneMax() {
        return laneMax;
    }

    public void setLaneMax(String laneMax) {
        this.laneMax = laneMax;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public boolean isWeightTag() {
        return weightTag;
    }

    public void setWeightTag(boolean weightTag) {
        this.weightTag = weightTag;
    }

    public boolean isSizeTag() {
        return sizeTag;
    }

    public void setSizeTag(boolean sizeTag) {
        this.sizeTag = sizeTag;
    }

    public boolean isSpeedTag() {
        return speedTag;
    }

    public void setSpeedTag(boolean speedTag) {
        this.speedTag = speedTag;
    }

    public String getSidePath() {
        return sidePath;
    }

    public void setSidePath(String sidePath) {
        this.sidePath = sidePath;
    }

    public boolean isPathTag() {
        return pathTag;
    }

    public void setPathTag(boolean pathTag) {
        this.pathTag = pathTag;
    }
}
