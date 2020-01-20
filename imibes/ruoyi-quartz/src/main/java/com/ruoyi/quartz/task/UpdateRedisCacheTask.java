package com.ruoyi.quartz.task;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.mapper.WeightDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component("updateRedisCacheTask")
public class UpdateRedisCacheTask {

    private static Logger LOGGER = LoggerFactory.getLogger(UpdateRedisCacheTask.class);

    @Autowired
    private WeightDataMapper weightDataMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //超限排行缓存
    public void updateOverLoadRankingCache(){
        List<String> days = new ArrayList<>();
        days.add("1");
        days.add("7");
        days.add("30");
        List<String> cars = new ArrayList<>();
        ValueOperations<String, PageInfo<HashMap>> operations = redisTemplate.opsForValue();
        ValueOperations<String, PageInfo<WeightData>> operationsRecord = redisTemplate.opsForValue();
        //车辆缓存
        for(int i = 0;i < days.size(); i++){
            String startDay = days.get(i);
            String key = "_" + startDay + "_car_1";
            PageHelper.startPage(1,18);
            List<HashMap> carList = weightDataMapper.overLoadCarList("",startDay);
            if(carList.size() > 0){
                cars.add(i,carList.get(0).get("plate").toString());
            }
            PageInfo<HashMap> pageList = new PageInfo<HashMap>(carList,4);
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                LOGGER.info("删除缓存中的key " + key);
            }
            operations.set(key, pageList, 5, TimeUnit.HOURS);
            LOGGER.info("写入缓存：" + key);
        }
        //第一条记录缓存
        for(int i = 0;i < cars.size(); i++){
            String plate = cars.get(i);
            String startDay = days.get(i);
            String key = plate + "_" + startDay + "_record_1";
            PageHelper.startPage(1,18);
            List<WeightData> list = weightDataMapper.selectOverLoadRecordByPlate(plate,startDay);
            PageInfo<WeightData> pageList = new PageInfo<WeightData>(list,4);
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                LOGGER.info("删除缓存中的key " + key);
            }
            operationsRecord.set(key, pageList, 5, TimeUnit.HOURS);
            LOGGER.info("写入缓存：" + key);
        }

    }

}
