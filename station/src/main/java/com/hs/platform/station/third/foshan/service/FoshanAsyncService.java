package com.hs.platform.station.third.foshan.service;

import com.hs.platform.station.third.foshan.socket.FoshanMessage;
import com.hs.platform.station.third.foshan.socket.SendMsgClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

@Service
public class FoshanAsyncService {

    private static Logger log = LoggerFactory.getLogger(FoshanAsyncService.class);

    @Async
    public void reSendASync(SendMsgClient sendMsgClient, FoshanMessage foshanMessage) {
        boolean ok;
        if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
            sendMsgClient.sendMessage(foshanMessage);
            if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
                sendMsgClient.sendMessage(foshanMessage);
                ok = getResponse(foshanMessage.getPlate(), 1000 * 10);
            }
        }
        if (ok) {
            FoshanApiService.successCount.increment();
        } else {
            FoshanApiService.failCount.increment();
        }
    }

    private boolean getResponse(String plate, long timeout) {
        boolean ok = false;
        try {
            Thread.sleep(timeout);
            if (FoshanApiService.okPlateMap.remove(plate) != null) {
                ok = true;
            }
        } catch (Exception e) {
            log.info("async getResponse fail," + plate, e);
        }
        return ok;
    }

}
