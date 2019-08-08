package com.hs.rs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zzw on 17-2-15.
 */

@XStreamAlias("soapenv:Body")
public class Body {

    @XStreamAlias("web:blacksmokevehicle")
    private BaseElement blacksmokevehicle;

    @XStreamAlias("web:environmentalquality")
    private BaseElement environmentalquality;

    @XStreamAlias("web:mobilemonitor")
    private BaseElement mobilemonitor;

    @XStreamAlias("web:Monitoringdata")
    private BaseElement monitoringdata;

    @XStreamAlias("web:monitoringline")
    private BaseElement monitoringline;

    @XStreamAlias("web:monitoringpoint")
    private BaseElement monitoringpoint;

    @XStreamAlias("web:trafficflow")
    private BaseElement trafficflow;

    @XStreamAlias("web:Vehicle")
    private BaseElement vehicle;

    @XStreamAlias("web:Vehicletrajectory")
    private BaseElement vehicletrajectory;

    public Body() {
    }

    public BaseElement getBlacksmokevehicle() {
        return blacksmokevehicle;
    }

    public void setBlacksmokevehicle(BaseElement blacksmokevehicle) {
        this.blacksmokevehicle = blacksmokevehicle;
    }

    public BaseElement getEnvironmentalquality() {
        return environmentalquality;
    }

    public void setEnvironmentalquality(BaseElement environmentalquality) {
        this.environmentalquality = environmentalquality;
    }

    public BaseElement getMobilemonitor() {
        return mobilemonitor;
    }

    public void setMobilemonitor(BaseElement mobilemonitor) {
        this.mobilemonitor = mobilemonitor;
    }

    public BaseElement getMonitoringdata() {
        return monitoringdata;
    }

    public void setMonitoringdata(BaseElement monitoringdata) {
        this.monitoringdata = monitoringdata;
    }

    public BaseElement getMonitoringline() {
        return monitoringline;
    }

    public void setMonitoringline(BaseElement monitoringline) {
        this.monitoringline = monitoringline;
    }

    public BaseElement getMonitoringpoint() {
        return monitoringpoint;
    }

    public void setMonitoringpoint(BaseElement monitoringpoint) {
        this.monitoringpoint = monitoringpoint;
    }

    public BaseElement getTrafficflow() {
        return trafficflow;
    }

    public void setTrafficflow(BaseElement trafficflow) {
        this.trafficflow = trafficflow;
    }

    public BaseElement getVehicle() {
        return vehicle;
    }

    public void setVehicle(BaseElement vehicle) {
        this.vehicle = vehicle;
    }

    public BaseElement getVehicletrajectory() {
        return vehicletrajectory;
    }

    public void setVehicletrajectory(BaseElement vehicletrajectory) {
        this.vehicletrajectory = vehicletrajectory;
    }
}
