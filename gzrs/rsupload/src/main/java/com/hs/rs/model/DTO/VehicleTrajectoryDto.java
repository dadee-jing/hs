package com.hs.rs.model.DTO;


import lombok.*;

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
public class VehicleTrajectoryDto {

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
    private String tgsj;
    /**
     * 车辆速度
     */
    private String clsd;
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
    private String sbzxd;
}
