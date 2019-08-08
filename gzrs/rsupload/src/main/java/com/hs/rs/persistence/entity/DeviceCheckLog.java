package com.hs.rs.persistence.entity;


import lombok.*;

import java.util.Date;

/**
 * 遥测设备检查表 rs_device_check_log
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCheckLog {

    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * 遥测线编号
     */
    private String ycxbh;
    /**
     * 检查类型
     */
    private Integer jclx;
    /**
     * 检查开始时间
     */
    private Date jckssj;
    /**
     * 是否通过
     */
    private String sftg;
    /**
     * 备注
     */
    private String bZ;
    /**
     * 检查单位
     */
    private String jcdw;
    /**
     * 检查人员
     */
    private String jcry;

    private Integer upLoadStatus;
}
