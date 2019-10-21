package com.hs.platform.station.db;

import com.google.gson.Gson;
import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.entity.WeightEntity;
import com.hs.platform.station.util.WeightAndLWHContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WeightService {

    private static Logger LOGGER = LoggerFactory.getLogger(WeightService.class);

    public static void insert(String json, String sequenceTag) {
        LOGGER.info("[newlx_weight_message]: " + json);
        Gson gson = new Gson();
        WeightEntity weightBean = gson.fromJson(json, WeightEntity.class);
        // 转化成组合entity
        WeightAndLwhEntity entity = new WeightAndLwhEntity();

        entity.setProcessStatus(0);
        entity.setSequenceTag(sequenceTag);
        // 填充字段到组合的entity
        int LimitMode = weightBean.getLimitMode();
        String VehicleType = weightBean.getVehicleType();
        int WeightingId = weightBean.getWeightingId();
        Timestamp WeightingDate = weightBean.getWeightingDate();
        int Lane = weightBean.getLane();
        String Direction = weightBean.getDirection();
        String TruckNumber = weightBean.getTruckNumber();
        String TruckCorlor = weightBean.getTruckCorlor();
        BigDecimal Speed = weightBean.getSpeed();
        int AxleCount = weightBean.getAxleCount();
        String AxleType = weightBean.getAxleType();
        BigDecimal Weight = weightBean.getWeight();
        BigDecimal LimitWeight = weightBean.getLimitWeight();
        BigDecimal OverWeight = weightBean.getOverWeight();
        BigDecimal AxleWeight1 = weightBean.getAxleWeight1();
        BigDecimal AxleWeight2 = weightBean.getAxleWeight2();
        BigDecimal AxleWeight3 = weightBean.getAxleWeight3();
        BigDecimal AxleWeight4 = weightBean.getAxleWeight4();
        BigDecimal AxleWeight5 = weightBean.getAxleWeight5();
        BigDecimal AxleWeight6 = weightBean.getAxleWeight6();
        BigDecimal AxleWeight7 = weightBean.getAxleWeight7();
        BigDecimal AxleWeight8 = weightBean.getAxleWeight8();
        String FtpHead = weightBean.getFtpHead();
        String FtpAxle = weightBean.getFtpAxle();
        String FtpTail = weightBean.getFtpTail();
        String FtpPriorHead = weightBean.getFtpPriorHead();
        String FtpPlate = weightBean.getFtpPlate();
        String FtpFullView = weightBean.getFtpFullView();


        entity.setLimitMode(LimitMode);
        entity.setVehicleType(VehicleType);
        entity.setWeightingId(WeightingId);
        entity.setWeightingDate(WeightingDate);
        entity.setLane(Lane);
        entity.setDirection(Direction);
        entity.setTruckNumber(TruckNumber);
        entity.setTruckCorlor(TruckCorlor);
        entity.setSpeed(Speed);
        entity.setAxleCount(AxleCount);
        entity.setAxleType(AxleType);
        entity.setWeight(Weight);
        entity.setLimitWeight(LimitWeight);
        entity.setOverWeight(OverWeight);
        entity.setAxleWeight1(AxleWeight1);
        entity.setAxleWeight2(AxleWeight2);
        entity.setAxleWeight3(AxleWeight3);
        entity.setAxleWeight4(AxleWeight4);
        entity.setAxleWeight5(AxleWeight5);
        entity.setAxleWeight6(AxleWeight6);
        entity.setAxleWeight7(AxleWeight7);
        entity.setAxleWeight8(AxleWeight8);
        entity.setFtpHead(FtpHead);
        entity.setFtpAxle(FtpAxle);
        entity.setFtpTail(FtpTail);
        entity.setFtpPriorHead(FtpPriorHead);
        entity.setFtpPlate(FtpPlate);
        entity.setFtpFullView(FtpFullView);
        entity.setWeightTag(true);
        WeightAndLWHContainer.processData(entity);
    }

}
