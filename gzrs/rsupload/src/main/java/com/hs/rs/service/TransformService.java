package com.hs.rs.service;

import com.hs.rs.model.DTO.*;
import com.hs.rs.persistence.entity.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransformService {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat myFmt2=new SimpleDateFormat("yyMMddHHmmss");
    public BlacksmokevehicleDto transBlacksmokevehicleDto(BlacksmokevehicleInfo source) {
        BlacksmokevehicleDto target = new BlacksmokevehicleDto();
        BeanUtils.copyProperties(source, target);
        //target.setLgmhd(String.valueOf(source.getLgmhd()));
        target.setLgmhd("100");
        target.setJcsj(DateFormatUtils.format(source.getJcsj(), "YYYY-MM-DD HH:mm:ss"));
        return target;
    }


    public LineDto transLineDto(Line source) {
        LineDto target = new LineDto();
        BeanUtils.copyProperties(source, target);
        target.setCsyyxq(format.format(source.getCsyyxq()));
        target.setQtcsyyxq(format.format(source.getQtcsyyxq()));
        target.setYdjyxq(format.format(source.getYdjyxq()));
        target.setSxxtyxq(format.format(source.getSxxtyxq()));
        target.setPdjyxq(format.format(source.getPdjyxq()));
        target.setQxzyxq(format.format(source.getQxzyxq()));
        return target;
    }

    public MonitorDataDto transMonitorDataDto(MonitorDataLog source) {
        MonitorDataDto target = new MonitorDataDto();
        target.setJcsj(source.getJcsj());
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public StationDto transStationDto(Station source) {
        StationDto target = new StationDto();
        BeanUtils.copyProperties(source, target);
        target.setYxrq(format.format(source.getYxrq()));
        return target;
    }

    public TrafficFlowDto transTrafficFlowDto(TrafficFlow source) {
        TrafficFlowDto target = new TrafficFlowDto();
        BeanUtils.copyProperties(source, target);
        target.setLlbh(myFmt2.format(new Date()));
        target.setDwbh(source.getDwbh());
        target.setSsdl(source.getSsdl());
        target.setLlfl(source.getLlfl());
        target.setTjsc(source.getTjsc());
        target.setCjsd(source.getCjsd().toString());
        target.setCjxh(source.getCjxh());
        target.setTtrq(format.format(source.getTjrq()));
        target.setCdxh(source.getCdxh());
        target.setPjsd(source.getPjsd().toString());
        target.setPjpdcd(source.getPjpdcd().toString());
        return target;
    }
    public VehicleInfoDto transVehicleInfoDto(VehicleInfo source) {
        VehicleInfoDto target = new VehicleInfoDto();
        BeanUtils.copyProperties(source, target);
        return target;
    }
    public VehicleTrajectoryDto transVehicleTrajectoryDto(VehicleTrajectory source) {
        VehicleTrajectoryDto target = new VehicleTrajectoryDto();
        BeanUtils.copyProperties(source, target);
        DecimalFormat df = new DecimalFormat("0.00");
        target.setTgsj(format.format(source.getTstime()));
        target.setClsd(df.format(source.getClsd()));
        target.setSbzxd(String.valueOf(source.getSbzxd()));
        return target;
    }
}
