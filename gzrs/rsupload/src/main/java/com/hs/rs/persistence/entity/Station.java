package com.hs.rs.persistence.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 点位表 rs_station
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
@Table(name = "rs_station")
public class Station {

    /**
     * 点位编号
     */
    @Id
    private String dwbh;
    /**
     * 点位名称
     */
    private String dwmc;
    /**
     * 点位类型
     */
    private String dwlx;
    /**
     * 运行日期
     */
    private Date yxrq;
    /**
     * 点位状态
     */
    private String dwzt;
    /**
     * 点位地址
     */
    private String dwdz;
    /**
     * 地点经度
     */
    private String ddjd;
    /**
     * 地点维度
     */
    private String ddwd;
    /**
     * 车流方向
     */
    private String clfx;
    /**
     * 车道数量
     */
    private String cdsl;
    /**
     * 车道坡度
     */
    private String cdpd;
    /**
     * 遥测线数
     */
    private String ycxs;
    /**
     * 号牌号码
     */
    private String hphm;
    /**
     * 装载车型号
     */
    private String clxh;

    private String sssp;

    @Column(name="UpLoadStatus")
    private Integer upLoadStatus;
}
