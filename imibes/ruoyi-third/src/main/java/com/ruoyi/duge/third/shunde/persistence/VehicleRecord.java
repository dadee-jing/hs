package com.ruoyi.duge.third.shunde.persistence;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRecord {

    private Long id;
    private String recordId; //序列号
    private Integer stationId; //站点编号
    private String plateNo; //车牌号
    private Integer plateColor; //车牌颜色
    private Double vehicleSpeed; //车速
    private Integer laneNo; //中间车道号
    private Integer axleNum;//轴数
    private Double vehicleWeight; //车重
    private Double vehicleHeight; //车高
    private Double vehicleWidth; //车宽
    private Double vehicleLength; //车长
    private Boolean overSpeed; //是否超速
    private Boolean overWeight; //是否超重
    private Boolean overHeight; //是否超高
    private Boolean overWidth; //是否超宽
    private Boolean overLength; //是否超长
    private Boolean overLine; //是否变道
    private String overSpeedRadio; //超速所占百分比
    private String overWeightRadio; //超重所占百分比
    private String overHeightRadio; //超高所占百分比
    private String overWidthRadio; //超宽所占百分比
    private String overLengthRadio; //超长所占百分比
    private Date checkDate; // 检查日期
    private Date createDate; // 创建日期
    private Date modifyDate; // 更新日期
    private String memo; //备忘录
}
