package com.hs.platform.station.led;

import com.lumen.ledcenter3.protocol.ExtSendUtil;

public class LedComponent {

    public static void main(String[] args) {
        ExtSendUtil extSendUtil = new ExtSendUtil();
        extSendUtil.initNetwork("192.168.110", 5200, "255.255.255.0");
        extSendUtil.sendText(0, "你好", 0, 12,
                10, 10, 10, 10, 10);
    }
}
