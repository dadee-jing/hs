package com.hs.platform.station.entity;

import java.sql.Timestamp;

public class DeviceEntity {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    private int Id;
    private Timestamp Date;
    private String DeviceType;
    private String IpAddress;
    private int State;


}
