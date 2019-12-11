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
    private final FoshanAsyncService foshanAsyncService;
    private static LinkedBlockingQueue<FoshanMessage> shiJuQueue = new LinkedBlockingQueue<>(5000);
    public static Boolean uploadShiJu = false;
    public static LongAdder totalCount = new LongAdder();//总记录数
    public static LongAdder queueCount = new LongAdder();//发送队列总数
    public static LongAdder sendCount = new LongAdder();//执行发送的次数
    //public static LongAdder sendFailCount = new LongAdder();
    public static LongAdder sendSuccessCount = new LongAdder();//发送成功的次数
    public static LongAdder successCount = new LongAdder();//发送时间内收到成功回调的次数
    public static LongAdder failCount = new LongAdder();//没收到回调次数
    public static LongAdder receiveCount = new LongAdder();//收到5020次数
    public static ConcurrentHashMap<String, String> waitingResponseMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> okPlateMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> failPlateMap = new ConcurrentHashMap<>();
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition cond = lock.newCondition();

    @Autowired
    public FoshanApiService(SendMsgClient sendMsgClient, ConfigDataRepository configDataRepository,
                            FoshanAsyncService foshanAsyncService) {
        this.sendMsgClient = sendMsgClient;
        this.configDataRepository = configDataRepository;
        this.foshanAsyncService = foshanAsyncService;
    }

    public static void addEntity(FoshanMessage entity) {
        totalCount.increment();
        queueCount.increment();
        if (!shiJuQueue.offer(entity)) {
            LOGGER.error("shiJuQueue full");
            shiJuQueue.poll();
            shiJuQueue.poll();
            shiJuQueue.poll();
            shiJuQueue.offer(entity);
            queueCount.add(-3);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void submitShiJuData() {
        if ("1".equals(getDbConfigValue("do_foshan_scheduled"))) {
            uploadShiJu = true;
            FoshanMessage foshanMessage = shiJuQueue.poll();
            while (null != foshanMessage) {
                LOGGER.info("send "+ foshanMessage.getPlate());
                sendMsgClient.sendMessage(foshanMessage);
                reSendFail(foshanMessage);
                //异步重发和回调
                //foshanAsyncService.reSendASync(sendMsgClient,foshanMessage);
                foshanMessage = shiJuQueue.poll();
                LOGGER.info("queueSize:" + queueCount.longValue() + ",sendCount:" + sendCount.longValue()
                        + ",sendSuccessCount:" + sendSuccessCount.longValue() + ",receiveCount:"
                        + receiveCount.longValue() + ",successCount:" + successCount.longValue()
                        + ",failCount:" + failCount.longValue()+ ",totalCount:" + totalCount.longValue());
            }
        } else {
            uploadShiJu = false;
            shiJuQueue.clear();
        }
    }

    private void reSendFail(FoshanMessage foshanMessage) {
        boolean ok;
        if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
            sendMsgClient.sendMessage(foshanMessage);
            if (!(ok = getResponse(foshanMessage.getPlate(), 1000 * 10))) {
                //sendMsgClient.sendMessage(foshanMessage);
                //ok = getResponse(foshanMessage.getPlate(), 1000 * 10);
            }
        }
        if (ok) {
            successCount.increment();
        } else {
            failCount.increment();
        }
    }


    private boolean getResponse(String plate, long timeout) {
        timeout = TimeUnit.MILLISECONDS.toNanos(timeout);
        boolean ok = false;
        try {
            lock.lockInterruptibly();
            for (; ; ) {
                if (okPlateMap.remove(plate) != null) {
                    ok = true;
                    break;
                }
                if (timeout <= 0) {
                    failPlateMap.put(plate, 1);
                    return false;
                }
                try {
                    timeout = cond.awaitNanos(timeout);
                } catch (InterruptedException ie) {
                    cond.signal();
                    failPlateMap.put(plate, 1);
                    throw ie;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
