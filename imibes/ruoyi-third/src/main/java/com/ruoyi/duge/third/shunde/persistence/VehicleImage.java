package com.ruoyi.duge.third.shunde.persistence;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImage {

    private Long id;
    private String imgId;
    private String recordId;
    private Integer imgType;
    private String imageFileName;
    private String imageFilePath;
    private Date createDate;
    private Date modifyDate;
    private String memo;
}
