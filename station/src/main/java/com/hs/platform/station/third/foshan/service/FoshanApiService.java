package com.hs.platform.station.third.foshan.service;

import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.entity.ConfigData;
import com.hs.platform.station.third.foshan.socket.FoshanMessage;
import com.hs.platform.station.third.foshan.socket.SendMsgClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class FoshanApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoshanApiService.class);
    private final SendMsgClient sendMsgClient;
    private final ConfigDataRepository configDataRepository;
    private static LinkedBlockingQueue<FoshanMessage> shiJuQueue = new LinkedBlockingQueue<>(500);
    public static Boolean uploadShiJu = false;
    public static LongAdder totalCount = new LongAdder();
    public static LongAdder sendCount = new LongAdder();
    public static LongAdder sendFailCount = new LongAdder();
    public static LongAdder sendSuccessCount = new LongAdder();
    public static LongAdder successCount = new LongAdder();
    public static LongAdder failCount = new LongAdder();
    public static LongAdder receiveCount = new LongAdder();
    public static ConcurrentHashMap<String, String> waitingResponseMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> okPlateMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> failPlateMap = new ConcurrentHashMap<>();
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition cond = lock.newCondition();


    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient, ConfigDataRepository configDataRepository) {
        this.sendMsgClient = sendMsgClient;
        this.configDataRepository = configDataRepository;
    }

    public static void addEntity(FoshanMessage entity) {
        totalCount.increment();
        if (!shiJuQueue.offer(entity)) {
            LOGGER.error("shiJuQueue full");
            shiJuQueue.poll();
            shiJuQueue.poll();
            shiJuQueue.poll();
            shiJuQueue.offer(entity);
        }
        //log.info("FoshanMessage add size:" + shiJuQueue.size());
    }

    @Scheduled(fixedRate = 5000)
    public void submitShiJuData() {
        if ("1".equals(getDbConfigValue("do_foshan_scheduled"))) {
            uploadShiJu = true;
            FoshanMessage foshanMessage = shiJuQueue.poll();
            while (null != foshanMessage) {
                sendMsgClient.sendMessage(foshanMessage);
                LOGGER.info("send "+ foshanMessage.getPlate());
                boolean ok;
                // 重试3次
                if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
                    sendMsgClient.sendMessage(foshanMessage);
                    if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
                        sendMsgClient.sendMessage(foshanMessage);
                        ok = getResponse(foshanMessage.getPlate(), 1000 * 10);
                    }
                }
                if (ok) {
                    successCount.increment();
                } else {
                    failCount.increment();
                    failPlateMap.put(foshanMessage.getPlate(), 1);
                }
                foshanMessage = shiJuQueue.poll();
                LOGGER.info("queueSize:" + totalCount.longValue() + ",sendCount:" + sendCount.longValue()
                        + ",sendFailCount:" + sendFailCount.longValue() + ",sendSuccessCount:" + sendSuccessCount.longValue()
                        + ",receiveCount:" + receiveCount.longValue() + ",successCount:" + successCount.longValue()
                        + ",failCount:" + failCount.longValue());
            }
        } else {
            uploadShiJu = false;
            shiJuQueue.clear();
        }
    }

    /**
     * 规定时间内等待相应车牌反馈
     * @param plate
     * @param timeout
     * @return
     */
    private boolean getResponse(String plate, long timeout) {

        timeout = TimeUnit.MILLISECONDS.toNanos(timeout);
        boolean ok = false;
        try {
            lock.lockInterruptibly();
            for (; ; ) {
                if (timeout <= 0) {
                    return false;
                }
                try {
                    timeout = cond.awaitNanos(timeout);
                } catch (InterruptedException ie) {
                    cond.signal();
                    throw ie;
                }
                if (okPlateMap.remove(plate) != null) {
                    ok = true;
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (Exception e) {
            LOGGER.error("lock error", e);
        } finally {
            lock.unlock();
        }
        return ok;
    }

    private String getDbConfigValue(String key) {
        try {
            ConfigData configData = configDataRepository.findFirstByKey(key);
            if (null != configData) {
                return configData.getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
