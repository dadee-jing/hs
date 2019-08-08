package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationStatistics extends BaseEntity {

    private Long id;

    private Long stationId;

    private Date dateTime;

    private Integer year;

    private Integer month;

    private Integer day;

    private Long trafficFlow;

    private Long overCount;
}
