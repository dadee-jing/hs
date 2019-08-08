package com.hs.rs.persistence.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 交通流量表 rs_traffic_flow
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rs_traffic_flow")
@Entity
public class TrafficFlow {

    @Id
    @Column(name = "AutoID")
    private Long id;

    private String jlbh;
    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * 监测点位日志号
     */
    private String jcdwrzh;
    /**
     * 所属道路
     */
    private String ssdl;
    /**
     * 流量分类
     */
    private String llfl;
    /**
     * 统计时长
     */
    private String tjsc;
    /**
     * 采集时段
     */
    private Integer cjsd;


    private String cjxh;
    /**
     * 统计日期
     */
    private Date tjrq;
    /**
     * 车道序号
     */
    private String cdxh;
    /**
     * 为小型客车数
     */
    private Integer wxxkcs;
    /**
     * 中型客车数
     */
    private Integer zxkcs;
    /**
     * 大型客车数
     */
    private Integer dxkcs;
    /**
     * 小型货车数
     */
    private Integer xxhcs;
    /**
     * 中型货车数
     */
    private Integer zxhcs;
    /**
     * 重型货车数
     */
    private Integer zxhcs1;
    /**
     * 通行车辆数
     */
    private Integer txcls;
    /**
     * 平均速度
     */
    private Integer pjsd;
    /**
     * 平均排队长度
     */
    private Integer pjpdcd;

    @Column(name="UpLoadStatus")
    private Integer upLoadStatus;
}
