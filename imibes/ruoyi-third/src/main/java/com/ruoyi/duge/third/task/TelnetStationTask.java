package com.ruoyi.duge.third.task;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.service.IStationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

@Component("ryTask")
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
            String latesTime = stationInfoService.getStationLatesCarRecordTime(stationId);
            if(StringUtils.isNotEmpty(latesTime)){
                stationInfo.setRemarkInfo(latesTime);
            }
            try {
                InetSocketAddress address = new InetSocketAddress(stationInfo.getIp(),stationInfo.getPort());
                server.connect(address, 5000);
                stationInfo.setState(1);
                stationInfoService.updateStationInfo(stationInfo);
                System.out.println("telnet -- " + stationInfo.getIp() + ":" + stationInfo.getPort() + "，成功");
            } catch (Exception e) {
                stationInfo.setState(0);
                stationInfoService.updateStationInfo(stationInfo);
                System.out.println("telnet -- " + stationInfo.getIp() + ":" + stationInfo.getPort() + "，失败");
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ryNoParams() {
        System.out.println("test");
    }
}
