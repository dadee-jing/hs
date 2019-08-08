package com.hs.rs.persistence.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 点位遥测线表 rs_line
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rs_line")
@IdClass(LineMultiKeys.class)
public class Line {
    /**
     * 点位编号
     */
    @Id
    private String dwbh;
    /**
     * 遥感线编号
     */
    @Id
    private String ycxbh;
    /**
     * 车道序号
     */
    private String cdxh;
    /**
     * 检测系统型号
     */
    private String jcxtxh;
    /**
     * 检测系统名称
     */
    private String jcxtmc;
    /**
     * 检测系统编号
     */
    private String jcxtbh;
    /**
     * 检测系统制作厂
     */
    private String jcxtzzc;
    /**
     * 测速仪型号
     */
    private String csyxh;
    /**
     * 测速仪生产厂
     */
    private String csyscc;
    /**
     * 测速仪有效期
     */
    private Date csyyxq;
    /**
     * 气体测试仪型号
     */
    private String qtcsyxh;
    /**
     * 气体测试仪生产厂
     */
    private String qtcsyscc;
    /**
     * 气体测试仪有效期
     */
    private Date qtcsyyxq;
    /**
     * 烟度计型号
     */
    private String ydjxh;
    /**
     * 烟度计生产厂
     */
    private String ydjscc;
    /**
     * 烟度计有效期
     */
    private Date ydjyxq;
    /**
     * 摄像系统型号
     */
    private String sxxtxh;
    /**
     * 摄像系统生产厂
     */
    private String sxxtscc;
    /**
     * 摄像系统有效期
     */
    private Date sxxtyxq;
    /**
     * 坡度计型号
     */
    private String pdjxh;
    /**
     * 坡度计生产厂
     */
    private String pdjscc;
    /**
     * 坡度计有效期
     */
    private Date pdjyxq;
    /**
     * 气象站型号
     */
    private String qxzxh;
    /**
     * 气象站生产厂
     */
    private String qxzscc;
    /**
     * 气象站有效期
     */
    private Date qxzyxq;

    @Column(name="UpLoadStatus")
    private Integer upLoadStatus;
}
