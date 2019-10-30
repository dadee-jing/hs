package com.hs.platform.station.util;

import com.hs.platform.station.entity.WeightAndLwhEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NoMatchUtil {

    private static LinkedList<WeightAndLwhEntity> queue = new LinkedList<>();
    private static Logger LOGGER = LoggerFactory.getLogger(NoMatchUtil.class);

    /**
     * 定期清理 queue 里遗留的，没有匹配成功的数据。(queue需要称重和外廓数据都存储才会正常出列)
     */
    public static void notMatchInsertDB() {
        Runnable clearRunnable = () -> {
            // LocalDateTime localDateTime = LocalDateTime.now();
            // System.out.println("清理线程执行：" + localDateTime);
            // System.out.println("queue size：" + queue.size());
            if(queue != null && queue.size() != 0){
                WeightAndLwhEntity entity = queue.poll();
                if (null != entity) {
                    //判断此对象是否超时
                    long currentTime = System.currentTimeMillis();
                    long timeout = entity.getTimeoutMillseconds();
                    if (currentTime < timeout) {//如果未超时，重新入队
                        queue.add(entity);
                    } else {
                        LOGGER.info("queue time out size:" + queue.size() + ",plate:" + entity.getPlate() +
                                "," + entity.getTruckNumber());
                        //超时,删除 内存中的对象，并将异常数据插入数据库的表
                        WeightAndLWHContainer.clearAndInsertDB(entity);
                    }
                }
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(clearRunnable, 3, 2, TimeUnit.SECONDS);
    }

    public static void addEntity(WeightAndLwhEntity entity) {
        queue.addLast(entity);
    }
}
