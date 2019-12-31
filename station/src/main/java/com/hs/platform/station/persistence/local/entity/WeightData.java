package com.hs.platform.station.persistence.local.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hs.platform.station.persistence.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 重量数据表 weight_data
 *
 * @author ruoyi
 * @date 2018-11-20
 */
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weight_data")
public class WeightData extends BaseEntity {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 站点名称
     */
    @Transient
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    /**
     * 侧拍标记
     */
    private Integer pathTag;
    /**
     * 本地左侧拍
     */
    private String leftSidePath;
    /**
     * 本地右侧拍
     */
    private String rightSidePath;

    /**
     * 栏板高
     */
    private String lbh;

    private String lwhScenePath;//外廓前抓


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
