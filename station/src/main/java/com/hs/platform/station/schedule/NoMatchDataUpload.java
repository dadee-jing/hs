package com.hs.platform.station.schedule;

import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.util.WeightAndLWHContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class NoMatchDataUpload {

    private static ConcurrentLinkedQueue<WeightAndLwhEntity> weightDataQueue = new ConcurrentLinkedQueue<>();
    private static Logger LOGGER = LoggerFactory.getLogger(NoMatchDataUpload.class);

    /**
     * 上传匹配超时的数据
     */
    @Scheduled(fixedRate = 5000)
    public void uploadNoMatchData() {
        doUploadNoMatchData();
    }

    /**
     * 从队列中删除元素的同时可能有新的数据插入
     * 使用非线程安全队列会造成CurrentModificationException
     */
    private void doUploadNoMatchData() {
        if (weightDataQueue != null && weightDataQueue.size() > 0) {
            LOGGER.info("to uploadNoMatchData size " + weightDataQueue.size());
            Iterator<WeightAndLwhEntity> iterator = weightDataQueue.iterator();
            while (iterator.hasNext()) {
                WeightAndLwhEntity entity = iterator.next();
                //判断此对象是否超时
                long currentTime = System.currentTimeMillis();
                long timeout = entity.getTimeoutMillseconds();
                //如果未超时，不做处理
                if (currentTime > timeout) {
                    //超时,处理数据
                    LOGGER.info("queue time out size:" + weightDataQueue.size() + ",plate:" +
                            entity.getPlate() + "," + entity.getTruckNumber());
                    WeightAndLWHContainer.clearAndInsertDB(entity);
                    iterator.remove();
                }
            }
            LOGGER.info("end uploadNoMatchData size:" + weightDataQueue.size());
        }
    }

    public static void addEntity(WeightAndLwhEntity entity) {
        weightDataQueue.add(entity);
    }

}
