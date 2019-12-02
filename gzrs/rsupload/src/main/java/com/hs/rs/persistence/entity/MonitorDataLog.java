package com.hs.rs.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 遥感监测数据表 rs_monitor_data_log
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
@Table(name = "rs_monitor_data_log")
public class MonitorDataLog {
    /**
     * 记录编号
     */
    @Id
    private String jlbh;
    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * 遥测线编号
     */
    private String ycxbh;
    /**
     * 监测点位日志号
     */
    private String jcdwrzh;

    private String jcryxm;
    /**
     * 车道序号
     */
    private String cdxh;
    /**
     * 监测时间
     */
    private String jcsj;
    /**
     * 地点经度
     */
    private String ddjd;
    /**
     * 地点维度
     */
    private String ddwd;
    /**
     * 车道坡度
     */
    private String cdpd;
    /**
     * 判定结果
     */
    private String pdjg;
    /**
     * 号牌号码
     */
    private String hphm;
    /**
     *
     */
    private String cpys;
    /**
     * 号牌种类
     */
    private String hpzl;
    /**
     * 燃料种类
     */
    private String rlzl;
    /**
     * CO2结果
     */
    private String co2jg;
    /**
     * CO/CO2比率
     */
    private String coco2;
    /**
     * HC/CO2比率
     */
    private String hcco2;
    /**
     * NO/CO2比率
     */
    private String noco2;
    /**
     * CO结果
     */
    private String cojg;
    /**
     * HC结果
     */
    private String hcjg;
    /**
     * NO结果
     */
    private String nojg;
    /**
     * 不透光度结果
     */
    private String btgdjg;
    /**
     * 林格曼黑度
     */
    private String lgmhd;
    /**
     * CO限制
     */
    private String coxz;
    /**
     * NO限值
     */
    private String noxz;
    /**
     * 不透光度限值
     */
    private String btgdxz;
    /**
     * 黑度限值
     */
    private String hdxz;
    /**
     * 车辆速度
     */
    private String clsd;
    /**
     * 车辆加速度
     */
    private String cljsd;
    /**
     * VSP
     */
    private String vsp;
    /**
     * 风速
     */
    private String fs;
    /**
     * 风向
     */
    private String fx;
    /**
     * 环境温度
     */
    private String hjwd;
    /**
     * 湿度
     */
    private String sd;
    /**
     * 大气压
     */
    private String dqy;
    /**
     * 轨迹信息编号
     */
    private String gjxxbh;
    /**
     * 图像1文件名
     */
    private String tp1;
    /**
     * 图像2文件名
     */
    private String tp2;
    /**
     * 视频1文件名
     */
    private String sp1;

    @Column(name="UpLoadStatus")
    private Integer upLoadStatus;
}
