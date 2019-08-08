package com.hs.rs.service;

import com.hs.rs.model.DTO.*;
import com.hs.rs.persistence.entity.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class TransformService {

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
        target.setCsyyxq(DateFormatUtils.format(source.getCsyyxq(), "YYYY-MM-DD HH:mm:ss"));
        target.setQtcsyyxq(DateFormatUtils.format(source.getQtcsyyxq(), "YYYY-MM-DD HH:mm:ss"));
        target.setYdjyxq(DateFormatUtils.format(source.getYdjyxq(), "YYYY-MM-DD HH:mm:ss"));
        target.setSxxtyxq(DateFormatUtils.format(source.getSxxtyxq(), "YYYY-MM-DD HH:mm:ss"));
        target.setPdjyxq(DateFormatUtils.format(source.getPdjyxq(), "YYYY-MM-DD HH:mm:ss"));
        target.setQxzyxq(DateFormatUtils.format(source.getQxzyxq(), "YYYY-MM-DD HH:mm:ss"));
        return target;
    }

    public MonitorDataDto transMonitorDataDto(MonitorDataLog source) {
        MonitorDataDto target = new MonitorDataDto();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public StationDto transStationDto(Station source) {
        StationDto target = new StationDto();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public TrafficFlowDto transTrafficFlowDto(TrafficFlow source) {
        TrafficFlowDto target = new TrafficFlowDto();
        BeanUtils.copyProperties(source, target);
        target.setLlbh(source.getJlbh());
        target.setCjsd(String.valueOf(source.getCjsd()));
        target.setTtrq(DateFormatUtils.format(source.getTjrq(), "yyyy-MM-dd HH:mm:ss"));
        target.setTxcls(String.valueOf(source.getTxcls()));
        target.setPjsd(String.valueOf(source.getPjsd()));
        target.setPjpdcd(String.valueOf(source.getPjpdcd()));
        target.setWxxkcs("0");
        target.setZxkcs("0");
        target.setDxkcs("0");
        target.setXxhcs("0");
        target.setZxhcs("0");
        target.setZxhcs1("0");
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
//        target.setTgsj(DateFormatUtils.format(source.getTgsj(), "YYYY-MM-DD HH:mm:ss"));
        target.setTgsj("0");

        target.setClsd(df.format(source.getClsd()));
        target.setSbzxd(String.valueOf(source.getSbzxd()));
        return target;
    }
}
