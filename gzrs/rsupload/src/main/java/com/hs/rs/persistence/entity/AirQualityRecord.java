package com.hs.rs.persistence.entity;


import lombok.*;

import java.math.BigDecimal;

/**
 * 点位环境空气质量记录表 rs_air_quality_record
 *
 * @author ruoyi
 * @date 2019-04-08
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirQualityRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 记录时间YYYYMMDD24HHMMSS
     */
    private String jlsj;
    /**
     * 点位编号
     */
    private String dwbh;
    /**
     * PM2.5
     */
    private BigDecimal pm25;
    /**
     * PM10
     */
    private BigDecimal pm10;
    /**
     * CO
     */
    private BigDecimal co;
    /**
     * SO2
     */
    private BigDecimal so2;
    /**
     * O3
     */
    private BigDecimal o3;
    /**
     * NO2
     */
    private BigDecimal no3;

    private Integer upLoadStatus;
}
