package com.ruoyi.duge.third.trafficPolice.overspeed;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.third.trafficPolice.utils.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PollFiles {
    @Autowired
    IStationInfoService stationInfoService;
    @Autowired
    OverSpeed  overSpeed;
    private static String path = "/sharedata/ftp/share";
    private static SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmssSSS" );
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Scheduled(cron="${trafficPoliceOverSpeed.scheduled}")
    public void excute() throws ParseException, UnsupportedEncodingException {
            // 实例化File对象
            File file = new File(path);
            // 判断该File对象是否是文件夹
            if (file.isDirectory()) {
                // 获取该文件夹下所有的文件及文件夹
                File[] files = file.listFiles();
                // 遍历
                if(files.length>0) {
                    for (File f : files) {
                        String filename = f.getName();
                        overSpeed.setFileName(filename);
                        String[] args = filename.split("-");
                        if (args.length<=1){
                            f.delete();
                            continue;
                        }
                        overSpeed.setStationId(args[0]);
                        Date date=sdf.parse(args[1]);
                        overSpeed.setShootDate(date);
                        overSpeed.setTruckNumber(args[2]);
                        overSpeed.setSpeed(args[3]);
                        overSpeed.setCode(args[4]);
                        overSpeed.setColorStr(args[5]);
                        System.out.println("增加文件的名称 :" + filename);
                        System.out.println("站点ID :" + args[0]);
                        System.out.println("日期 :" + sdf1.format(date));
                        System.out.println("车牌 :" + args[2]);
                        System.out.println("车速 :" + args[3]);
                        System.out.println("违法代码 :" + args[4]);
                        System.out.println("车牌颜色 :" + args[5]);
                        System.out.println("车道号 :" + args[6]);
                        StationInfo stationInfo = stationInfoService.selectStationInfoById(Integer.parseInt(args[0]),args[6]);
                        overSpeed.setAddress(stationInfo.getAddress());
                        overSpeed.setSpeedLimit(stationInfo.getSpeedLimit());
                        if(IOUtil.OverSpeedImages(overSpeed)){
                        f.delete();}
                    }
                }
            } else {
                System.out.println(file.getAbsolutePath());
            }
    }
}
