package com.ruoyi.duge.third.foshan.socket;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoshanRspMessage {
    private String instrType;
    private String serialNo;
    private String startTag;
    private String endTag;
    private String instrId;
    private String progressId;
    private String body;
    private String tbd;
    private Integer length;
}
