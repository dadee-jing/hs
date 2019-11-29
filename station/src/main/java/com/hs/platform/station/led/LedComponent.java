package com.hs.platform.station.led;

import com.lumen.ledcenter3.protocol.ExtSendUtil;
import com.lumen.ledcenter3.protocol.ShowEffect;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hs.platform.station.Constants.*;
import static com.hs.platform.station.util.ImageDownloadUtil.led_ip;

public class LedComponent {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static ExtSendUtil extSendUtil = new ExtSendUtil();
    // 网络参数
    private static final String ip = led_ip;
    private static final String idcode = led_idcode;
    private static final int port = led_port;
    // Led样式设置
    private static final Integer color = led_text_color;
    private static final Integer fontSize = led_text_fontSize;
    private static final Integer speed = led_text_speed;
    private static final Integer stayTime = led_text_stayTime;
    private static final Integer alignmentHori = led_text_alignmentHori;
    private static final Integer alignmentVert = led_text_alignmentVert;
    private static int nWndNo;

    // 初始化加载网络参数
    static{
        try {
            extSendUtil.initNetwork(ip, port, idcode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * LED发送消息
     * @param message 消息
     */
    public static void showMessageLed(String message) {
        executorService.execute(() ->
                extSendUtil.sendText(nWndNo, message, color, fontSize, speed,
                        ShowEffect.Instant.getEffect(), stayTime, alignmentHori, alignmentVert)
        );
    }

}
