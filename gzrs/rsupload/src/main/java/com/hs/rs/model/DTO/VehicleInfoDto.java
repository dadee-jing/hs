package com.hs.rs.model.DTO;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 车辆数据表 rs_vehicle_info
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInfoDto {
    /**
     * 行政区划代码
     */
    private String xzqhdm;
    /**
     * 号牌号码
     */
    private String hphm;
    /**
     * 车牌颜色
     */
    private String cpys;
    /**
     * 号牌种类
     */
    private String hpzl;
    /**
     * 车辆型号
     */
    private String clxh;
    /**
     * 生产企业
     */
    private String scqy;
    /**
     * 燃料种类
     */
    private String rlzl;
    /**
     * 使用性质
     */
    private String syxz;
    /**
     * 初次登记日期
     */
    private String ccdjrq;
    /**
     * 车辆识别代号
     */
    private String clsbdh;
    /**
     * 排放标准阶段
     */
    private String pfbzjd;
    /**
     * 上次环保定期检验日期
     */
    private String schbdqjyrq;
    /**
     * 上次环保定期检验机构
     */
    private String schbdqjyjg;
}
