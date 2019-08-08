package com.hs.rs.model.DTO;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class TrafficFlowDto {

    private String llbh;
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
    private String cjsd;

    private String cjxh;
    /**
     * 统计日期
     */
    private String ttrq;
    /**
     * 车道序号
     */
    private String cdxh;
    /**
     * 为小型客车数
     */
    private String wxxkcs;
    /**
     * 中型客车数
     */
    private String zxkcs;
    /**
     * 大型客车数
     */
    private String dxkcs;
    /**
     * 小型货车数
     */
    private String xxhcs;
    /**
     * 中型货车数
     */
    private String zxhcs;
    /**
     * 重型货车数
     */
    private String zxhcs1;
    /**
     * 通行车辆数
     */
    private String txcls;
    /**
     * 平均速度
     */
    private String pjsd;
    /**
     * 平均排队长度
     */
    private String pjpdcd;
}
