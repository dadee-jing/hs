package com.ruoyi.duge.third.guangdong.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GDVehicleDataRequest {

    private String dataID;

    private String indexCode;

    private String checkID;

    private String plateNo;

    private String plateColor;

    private String plateType;

    private String vehicleColor;

    private String vehicleType;

    private Double vehicleSpeed;

    private Integer laneNo;

    private String isOverWeight;

    private Integer axleNum;

    private String isDirect;

    private Double overWeight;

    private Integer vehicleWeight;

    private Integer limitWeight;

    private String checkTime;

    private String passCode;

    private String dataIsCredible;

    private String drivingDirection;

    private String isDangerous;

    private String axleModel;

    private Integer tyreNums;

    private Double loadRate;

    private Integer vehicleHeight;

    private Integer vehicleWidth;

    private Integer vehicleLong;

    private String vehicleBrand;

    private Double lengthoverLimitedRate;

    private Double widthoverLimitedRate;

    private Double heightoverLimitedRate;

    private Double lengthoverLimited;

    private Double widthoverLimited;

    private Double heightoverLimited;

    private List<Axle> axleArray;

    private List<Image> imageArray;

    private List<Video> videoArray;

    private List<Spare> spareArray;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Axle {
        private Integer axleDistance;
        private Integer axleNo;
        private Double axleSpeed;
        private Integer axleInfo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Spare {
        private String spareValue;
        private String spareName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Video {
        private String videoName;
        private String videoURL;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        private String imageName;
        private String imageURL;
        private String imageFeature;
    }
}
