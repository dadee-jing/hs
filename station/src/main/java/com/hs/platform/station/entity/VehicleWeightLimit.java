package com.hs.platform.station.entity;

public class VehicleWeightLimit {

    private Long id;
    private Integer alexCount;
    private Long weightLimit;

    public VehicleWeightLimit() {
    }

    public VehicleWeightLimit(Long id, Integer alexCount, Long weightLimit) {
        this.id = id;
        this.alexCount = alexCount;
        this.weightLimit = weightLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAlexCount() {
        return alexCount;
    }

    public void setAlexCount(Integer alexCount) {
        this.alexCount = alexCount;
    }

    public Long getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Long weightLimit) {
        this.weightLimit = weightLimit;
    }
}
