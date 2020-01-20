package com.hs.rs.service;

import com.hs.rs.model.DTO.*;
import com.hs.rs.persistence.entity.*;
import com.hs.rs.utils.CommonUtils;
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
        target.setJlbh(source.getJlbh().replace("B","D"));
        target.setDwbh(source.getDwbh().replace("B","D"));
        if(source.getPdjg().equals("0")){
            target.setTp1(CommonUtils.toBase64(source.getDwbh(),source.getJcsj(),source.getTp5()));
            target.setTp2(CommonUtils.toBase64(source.getDwbh(),source.getJcsj(),source.getTp2()));
        }else if (source.getPdjg().equals("1")){
            target.setTp1(CommonUtils.newPath(source.getDwbh(),source.getJcsj(),source.getTp5()));
            target.setTp2(CommonUtils.newPath(source.getDwbh(),source.getJcsj(),source.getTp2()));
        }
        target.setLgmhd(String.valueOf(source.getLgmhd()));
        target.setJcsj(format.format(source.getJcsj()));
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
        BeanUtils.copyProperties(source, target);
        target.setJcsj(format.format(source.getJcsj()));
        if(source.getRlzl().equals("Z")){
            target.setRlzl("Y");
        }
        if(source.getPdjg().equals("0")){
            System.out.println("判定结果为不通过");
            target.setTp1(CommonUtils.toBase64(source.getDwbh(),source.getJcsj(),source.getTp1()));
            target.setTp2(CommonUtils.toBase64(source.getDwbh(),source.getJcsj(),source.getTp2()));
        }else if (source.getPdjg().equals("1")){
            target.setTp1(CommonUtils.newPath(source.getDwbh(),source.getJcsj(),source.getTp1()));
            target.setTp2(CommonUtils.newPath(source.getDwbh(),source.getJcsj(),source.getTp2()));
        }else if (source.getPdjg().equals("999")){
            target.setPdjg("2");
        }
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
        if(source.getHphm().toCharArray().length>8){
            target.setHphm(source.getHphm().substring(0,7));
        }
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
