package com.hs.rs.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 遥测设备自检表 rs_station_check_log
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationCheckLog {

    /**
     * ID
     */
    private Integer id;
    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * 遥测线编号
     */
    private String ycxbh;
    /**
     * 自检开始时间
     */
    private Date zjkssj;
    /**
     * 是否通过 Y/N
     */
    private String sftg;
    /**
     * 备注
     */
    private String bz;

    private Integer upLoadStatus;
}
