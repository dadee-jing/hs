package com.hs.rs.persistence.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机动车轨迹表 rs_vehicle_trajectory
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "rs_vehicle_trajectory")
public class VehicleTrajectory {

    @Id
    @Column(name = "AutoId")
    private Long autoId;
    /**
     * 轨迹信息编号
     */
    private String gjxxbh;
    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * 监测点位日志号
     */
    private String jcdwrzh;
    /**
     * 车道编号
     */
    private String cdbh;
    /**
     * 通过日期
     */

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
     private Date tstime;
    /**
     * 车辆速度
     */
    private BigDecimal clsd;
    /**
     * 号牌号码
     */
    private String hphm;
    /**
     * 车身颜色
     */
    private String csys;
    /**
     * 号牌种类
     */
    private String hpzl;
    /**
     * 识别置信度
     */
    private Integer sbzxd;

    @Column(name="UpLoadStatus")
    private Integer upLoadStatus;
}
