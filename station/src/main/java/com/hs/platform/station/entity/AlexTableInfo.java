package com.hs.platform.station.entity;

import java.math.BigDecimal;

public class AlexTableInfo {

    private int Id;
    private int AlexNum;
    private BigDecimal Limit;
    private String StartTime;
    private String EndTime;

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAlexNum() {
        return AlexNum;
    }

    public void setAlexNum(int alexNum) {
        AlexNum = alexNum;
    }

    public BigDecimal getLimit() {
        return Limit;
    }

    public void setLimit(BigDecimal limit) {
        Limit = limit;
    }


}
