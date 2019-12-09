package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.service.IStationInfoService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("telnetStationTask")
public class TelnetStationTask {

    @Autowired
    private IStationInfoService stationInfoService;

    /**
     * 测试站点的ip和端口是否可用
     */
    public void TelnetStations() {
        List<StationInfo> stationInfoList = stationInfoService.selectStationStateInfo();
        for (StationInfo stationInfo : stationInfoList) {
            Socket server = new Socket();
            int stationId = stationInfo.getId();
            String latestTime = stationInfoService.getStationLatesCarRecordTime(stationId);
            String remarkInfo = "";
            if (StringUtils.isNotBlank(latestTime)) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String nowHour = DateFormatUtils.format(new Date(), "yyyyMMddHH");
                    String latesTimeHour = DateFormatUtils.format(simpleDateFormat.parse(latestTime), "yyyyMMddHH");
                    //三小时没有数据
                    if (Integer.parseInt(nowHour) - Integer.parseInt(latesTimeHour) > 3) {
                        remarkInfo = "<span style='color:red'> " + latestTime + "</span>";
                    } else {
                        remarkInfo = "<span style='color:green'> " + latestTime + "</span>";
                    }
                    stationInfo.setRemarkInfo(remarkInfo);
                    InetSocketAddress address = new InetSocketAddress(stationInfo.getIp(), stationInfo.getPort());
                    server.connect(address, 5000);
                    stationInfo.setState(1);
                    stationInfoService.updateStationInfo(stationInfo);
                    System.out.println("telnet -- " + stationInfo.getIp() + ":" + stationInfo.getPort() + "，成功");
                } catch (Exception e) {
                    stationInfo.setState(0);
                    stationInfoService.updateStationInfo(stationInfo);
                    System.out.println("telnet -- " + stationInfo.getIp() + ":" + stationInfo.getPort() + "，失败");
                    e.printStackTrace();
                } finally {
                    try {
                        if(server != null){
                            server.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void ryNoParams() {
        System.out.println("test");
    }
}
