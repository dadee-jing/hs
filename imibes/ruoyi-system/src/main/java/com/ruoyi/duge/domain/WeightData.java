package com.ruoyi.duge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.annotations.Insert;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 重量数据表 weight_data
 *
 * @author ruoyi
 * @date 2018-11-20
 */
public class WeightData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 超限等级
     */
    private Integer limitOverLevel;
    /**
     * 1=轴数限重；2=车型限重
     */
    private Integer limitMode;
    /**
     * 车型
     */
    private String vehicleType;
    /**
     * 称重序号
     */
    private Integer weightingId;
    /**
     * 称重时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date weightingDate;
    /**
     * 车道号
     */
    private Integer lane;
    /**
     * 行驶方向
     */
    private String direction;
    /**
     * 车牌号
     */
    private String truckNumber;
    /**
     * 车牌颜色
     */
    private String truckCorlor;
    /**
     * 车速
     */
    private BigDecimal speed;
    /**
     * 轴数
     */
    private Integer axleCount;
    /**
     * 轴型序列
     */
    private String axleType;
    /**
     * 总重
     */
    private BigDecimal weight;
    /**
     * 限重
     */
    private BigDecimal limitWeight;
    /**
     * 超重
     */
    private BigDecimal overWeight;
    /**
     *
     */
    private BigDecimal axleWeight1;
    /**
     *
     */
    private BigDecimal axleWeight2;
    /**
     *
     */
    private BigDecimal axleWeight3;
    /**
     *
     */
    private BigDecimal axleWeight4;
    /**
     *
     */
    private BigDecimal axleWeight5;
    /**
     *
     */
    private BigDecimal axleWeight6;
    /**
     *
     */
    private BigDecimal axleWeight7;
    /**
     *
     */
    private BigDecimal axleWeight8;
    /**
     * 唯一序列号
     */
    private String sequenceTag;
    /**
     * 车头图片地址（秤上）
     */
    private String ftpHead;
    /**
     * 车轴图片地址（秤上）
     */
    private String ftpAxle;
    /**
     * 车尾图片地址（秤上）
     */
    private String ftpTail;
    /**
     * 秤前车头图片地址
     */
    private String ftpPriorHead;
    /**
     * 车牌特征图
     */
    private String ftpPlate;
    /**
     * 车头视频文件地址
     */
    private String ftpFullView;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 站点ID
     */
    private Long stationId;
    /**
     * 超限采集时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date lwhDate;
    /**
     * 超限车牌号
     */
    private String plate;
    /**
     * 车宽
     */
    private String width;
    /**
     * 车高
     */
    private String height;
    /**
     * 车长
     */
    private String length;
    /**
     * 中间车道号
     */
    private String laneMid;
    /**
     * 小车道号
     */
    private String laneMin;
    /**
     * 大车道号
     */
    private String laneMax;
    /**
     * 通过时间
     */
    private String passTime;
    /**
     * 上传标记
     */
    private Integer uploadTag;

    private Integer uploadYhl;

    private Integer uploadSj;

    private Integer uploadJj;

    /**
     *上传市局站点Id
     */
    private Integer siteId;

    /**
     * 标记图片、视频数据是否删除 0：未删  1：已删
     * @return
     */
    private Integer markDel;
    //车道编号

    private Integer laneId;

    public Integer getLaneId() {
        return laneId;
    }

    public void setLaneId(Integer laneId) {
        this.laneId = laneId;
    }

    public Integer getMarkDel() {
        return markDel;
    }

    public void setMarkDel(Integer markDel) {
        this.markDel = markDel;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLimitOverLevel(Integer limitOverLevel) {
        this.limitOverLevel = limitOverLevel;
    }

    public Integer getLimitOverLevel() {
        return limitOverLevel;
    }

    public void setLimitMode(Integer limitMode) {
        this.limitMode = limitMode;
    }

    public Integer getLimitMode() {
        return limitMode;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setWeightingId(Integer weightingId) {
        this.weightingId = weightingId;
    }

    public Integer getWeightingId() {
        return weightingId;
    }

    public void setWeightingDate(Date weightingDate) {
        this.weightingDate = weightingDate;
    }

    public Date getWeightingDate() {
        return weightingDate;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public Integer getLane() {
        return lane;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckCorlor(String truckCorlor) {
        this.truckCorlor = truckCorlor;
    }

    public String getTruckCorlor() {
        return truckCorlor;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setAxleCount(Integer axleCount) {
        this.axleCount = axleCount;
    }

    public Integer getAxleCount() {
        return axleCount;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setLimitWeight(BigDecimal limitWeight) {
        this.limitWeight = limitWeight;
    }

    public BigDecimal getLimitWeight() {
        return limitWeight;
    }

    public void setOverWeight(BigDecimal overWeight) {
        this.overWeight = overWeight;
    }

    public BigDecimal getOverWeight() {
        return overWeight;
    }

    public void setAxleWeight1(BigDecimal axleWeight1) {
        this.axleWeight1 = axleWeight1;
    }

    public BigDecimal getAxleWeight1() {
        return axleWeight1;
    }

    public void setAxleWeight2(BigDecimal axleWeight2) {
        this.axleWeight2 = axleWeight2;
    }

    public BigDecimal getAxleWeight2() {
        return axleWeight2;
    }

    public void setAxleWeight3(BigDecimal axleWeight3) {
        this.axleWeight3 = axleWeight3;
    }

    public BigDecimal getAxleWeight3() {
        return axleWeight3;
    }

    public void setAxleWeight4(BigDecimal axleWeight4) {
        this.axleWeight4 = axleWeight4;
    }

    public BigDecimal getAxleWeight4() {
        return axleWeight4;
    }

    public void setAxleWeight5(BigDecimal axleWeight5) {
        this.axleWeight5 = axleWeight5;
    }

    public BigDecimal getAxleWeight5() {
        return axleWeight5;
    }

    public void setAxleWeight6(BigDecimal axleWeight6) {
        this.axleWeight6 = axleWeight6;
    }

    public BigDecimal getAxleWeight6() {
        return axleWeight6;
    }

    public void setAxleWeight7(BigDecimal axleWeight7) {
        this.axleWeight7 = axleWeight7;
    }

    public BigDecimal getAxleWeight7() {
        return axleWeight7;
    }

    public void setAxleWeight8(BigDecimal axleWeight8) {
        this.axleWeight8 = axleWeight8;
    }

    public BigDecimal getAxleWeight8() {
        return axleWeight8;
    }

    public void setSequenceTag(String sequenceTag) {
        this.sequenceTag = sequenceTag;
    }

    public String getSequenceTag() {
        return sequenceTag;
    }

    public void setFtpHead(String ftpHead) {
        this.ftpHead = ftpHead;
    }

    public String getFtpHead() {
        return ftpHead;
    }

    public void setFtpAxle(String ftpAxle) {
        this.ftpAxle = ftpAxle;
    }

    public String getFtpAxle() {
        return ftpAxle;
    }

    public void setFtpTail(String ftpTail) {
        this.ftpTail = ftpTail;
    }

    public String getFtpTail() {
        return ftpTail;
    }

    public void setFtpPriorHead(String ftpPriorHead) {
        this.ftpPriorHead = ftpPriorHead;
    }

    public String getFtpPriorHead() {
        return ftpPriorHead;
    }

    public void setFtpPlate(String ftpPlate) {
        this.ftpPlate = ftpPlate;
    }

    public String getFtpPlate() {
        return ftpPlate;
    }

    public void setFtpFullView(String ftpFullView) {
        this.ftpFullView = ftpFullView;
    }

    public String getFtpFullView() {
        return ftpFullView;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setLwhDate(Date lwhDate) {
        this.lwhDate = lwhDate;
    }

    public Date getLwhDate() {
        return lwhDate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setLaneMid(String laneMid) {
        this.laneMid = laneMid;
    }

    public String getLaneMid() {
        return laneMid;
    }

    public void setLaneMin(String laneMin) {
        this.laneMin = laneMin;
    }

    public String getLaneMin() {
        return laneMin;
    }

    public void setLaneMax(String laneMax) {
        this.laneMax = laneMax;
    }

    public String getLaneMax() {
        return laneMax;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setUploadTag(Integer uploadTag) {
        this.uploadTag = uploadTag;
    }

    public Integer getUploadTag() {
        return uploadTag;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUploadYhl() {
        return uploadYhl;
    }

    public void setUploadYhl(Integer uploadYhl) {
        this.uploadYhl = uploadYhl;
    }

    public Integer getUploadSj() {
        return uploadSj;
    }

    public void setUploadSj(Integer uploadSj) {
        this.uploadSj = uploadSj;
    }

    public Integer getUploadJj() {
        return uploadJj;
    }

    public void setUploadJj(Integer uploadJj) {
        this.uploadJj = uploadJj;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("limitOverLevel", getLimitOverLevel())
                .append("limitMode", getLimitMode())
                .append("vehicleType", getVehicleType())
                .append("weightingId", getWeightingId())
                .append("weightingDate", getWeightingDate())
                .append("lane", getLane())
                .append("direction", getDirection())
                .append("truckNumber", getTruckNumber())
                .append("truckCorlor", getTruckCorlor())
                .append("speed", getSpeed())
                .append("axleCount", getAxleCount())
                .append("axleType", getAxleType())
                .append("weight", getWeight())
                .append("limitWeight", getLimitWeight())
                .append("overWeight", getOverWeight())
                .append("axleWeight1", getAxleWeight1())
                .append("axleWeight2", getAxleWeight2())
                .append("axleWeight3", getAxleWeight3())
                .append("axleWeight4", getAxleWeight4())
                .append("axleWeight5", getAxleWeight5())
                .append("axleWeight6", getAxleWeight6())
                .append("axleWeight7", getAxleWeight7())
                .append("axleWeight8", getAxleWeight8())
                .append("sequenceTag", getSequenceTag())
                .append("ftpHead", getFtpHead())
                .append("ftpAxle", getFtpAxle())
                .append("ftpTail", getFtpTail())
                .append("ftpPriorHead", getFtpPriorHead())
                .append("ftpPlate", getFtpPlate())
                .append("ftpFullView", getFtpFullView())
                .append("stationName", getStationName())
                .append("deviceCode", getDeviceCode())
                .append("stationId", getStationId())
                .append("lwhDate", getLwhDate())
                .append("plate", getPlate())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("length", getLength())
                .append("laneMid", getLaneMid())
                .append("laneMin", getLaneMin())
                .append("laneMax", getLaneMax())
                .append("passTime", getPassTime())
                .append("uploadTag", getUploadTag())
                .toString();
    }
}
