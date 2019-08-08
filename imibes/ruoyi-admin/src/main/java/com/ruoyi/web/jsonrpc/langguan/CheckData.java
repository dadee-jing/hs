package com.ruoyi.web.jsonrpc.langguan;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckData {

    private String checkid;
    private String cnumberplate;
    private String cplatetype;
    private String cartype;
    private String confidencelevel;
    private String ringleman;
    private String smokedirection;
    private Long checkdate;
    private String bayonetid;
    private String pointid;
    private String roaddirection;
    private String checkversion;
    private String checkresult;
    private String tp1;
    private String tp2;
    private String tp3;
    private String tp4;
    private String tp5;
    private String sp1;
    private String light;
    private String temperature;
    private String humidity;
    private String windspeed;
    private String winddirection;
    private String rainfallcondition;
    private String atmosphere;
}
