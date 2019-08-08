package com.ruoyi.duge.third.trafficPolice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XStreamAlias("tvmr")
public class Tvmr {

    @XStreamAlias("version")
    private String version;

    @XStreamAlias("DataList")
    private List<Data> dataList;
}
