package com.hs.platform.station.schedule;

import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.util.WeightAndLWHContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class UpdateStationState {

    private static Logger LOGGER = LoggerFactory.getLogger(UpdateStationState.class);

    /**
     * 上传站点异常状态
     */
    //@Scheduled(fixedRate = 5000)
    public void UpdateStationExceptionState() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("to UpdateStationExceptionState");
        doUpdateStationExceptionState();
        long endTime = System.currentTimeMillis();
        LOGGER.info("end uploadNoMatchData cost:" + (endTime - startTime));
    }

    private void doUpdateStationExceptionState() {

    }


}
