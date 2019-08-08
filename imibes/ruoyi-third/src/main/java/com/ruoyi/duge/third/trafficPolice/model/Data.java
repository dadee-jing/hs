package com.ruoyi.duge.third.trafficPolice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XStreamAlias("Data")
public class Data {

    @XStreamAlias("sbbh")
    private String sbbh;

    @XStreamAlias("hpzl")
    private String hpzl;

    @XStreamAlias("hphm")
    private String hphm;

    @XStreamAlias("wfxw")
    private String wfxw;

    @XStreamAlias("wfsj")
    private String wfsj;

    @XStreamAlias("wfdd")
    private String wfdd;

    @XStreamAlias("wfdz")
    private String wfdz;

    @XStreamAlias("cdbh")
    private Integer cdbh;

    @XStreamAlias("clsd")
    private Double clsd;

    @XStreamAlias("clxs")
    private Double clxs;

    @XStreamAlias("hptztp")
    private String hptztp;

    @XStreamAlias("jsrtztp")
    private String jsrtztp;

    @XStreamAlias("zjwj1")
    private String zjwj1;

    @XStreamAlias("zjwj2")
    private String zjwj2;

    @XStreamAlias("zjwj3")
    private String zjwj3;

    @XStreamAlias("zjwj4")
    private String zjwj4;

    @XStreamAlias("splj")
    private String splj;

    @XStreamAlias("cjfs")
    private String cjfs;

    @XStreamAlias("cjjg")
    private String cjjg;

    @XStreamAlias("sfqj")
    private String sfqj;

    @XStreamAlias("kswfsj")
    private String kswfsj;

    @XStreamAlias("kswfdd")
    private String kswfdd;

    @XStreamAlias("kswfdz")
    private String kswfdz;

    @XStreamAlias("qjjl")
    private Integer qjjl;

    @XStreamAlias("qjys")
    private Integer qjys;

    @XStreamAlias("csbl")
    private Integer csbl;

    @XStreamAlias("bz")
    private String bz;

    @XStreamAlias("zpsl")
    private Integer zpsl;

    @XStreamAlias("zpwjm")
    private String zpwjm;

    @XStreamAlias("fxbh")
    private String fxbh;
}
