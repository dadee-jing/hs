package com.ruoyi.duge.third.trafficPolice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XStreamAlias("root")
public class Root {

    @XStreamAlias("tvmr")
    private Tvmr tvmr;
}
