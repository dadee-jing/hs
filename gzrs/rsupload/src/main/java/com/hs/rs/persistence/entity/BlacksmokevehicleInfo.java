package com.hs.rs.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "rs_monitor_heiyan_log")
public class BlacksmokevehicleInfo {

    @Id
    private String jlbh;

    private String dwbh;

    @Column(name = "YCXBH")
    private String jcxbh;

    private String cdxh;

    @Column(name = "TSTIME")
    private Date jcsj;

    @Column(name = "HDRESULT")
    private String pdjg;

    private String hphm;

    private String cpys;

    private String hpzl;

    private String rlzl;

    private Integer lgmhd;

    private String tp1;

    private String tp2;

    private String sp1;

    @Column(name = "UpLoadStatus")
    private Integer upLoadStatus;
}
