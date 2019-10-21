package com.hs.platform.station.util;

import com.hs.platform.station.Constants;
import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.led.LedComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hs.platform.station.Constants.led_overWeight_percentage;
import static com.hs.platform.station.util.DbUtil.getWeightLimitMap;

public class WeightAndLWHContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(WeightAndLWHContainer.class);

    private static final ConcurrentHashMap<String, WeightAndLwhEntity> mapContainer = new ConcurrentHashMap<>();

    private static Map<Integer, Long> weightLimitMap = getWeightLimitMap();

    private static final double overWeightPercentage = led_overWeight_percentage;

    /**
     * 清除内存中的没有匹配的对象，并插入数据库做记录
     *
     * @param currentEntity
     */
    public static void clearAndInsertDB(WeightAndLwhEntity currentEntity) {
        int processStatus = currentEntity.getProcessStatus();
        String carNumber = processStatus == 0 ? currentEntity.getTruckNumber() : currentEntity.getPlate();
        //
        WeightAndLwhEntity previousEntity = mapContainer.get(carNumber);
        if (null != previousEntity) {
            if (previousEntity.isWeightTag() && previousEntity.isSizeTag()) {
                LOGGER.info("clearAndInsertDB--" + carNumber);
                completeEntity(previousEntity, carNumber);
            } else {
                mapContainer.remove(carNumber);
                //插入数据库做备份
                int previousStatus = previousEntity.getProcessStatus();
                String remarkInfo = "";
                if (0 == previousStatus) {
                    remarkInfo = "长宽高数据缺失。";
                } else {
                    remarkInfo = "衡器称重数据缺失。";
                }
                previousEntity.setRemarkInfo(remarkInfo);
                DbUtil.insertExceptionData(previousEntity);
            }
        }
    }

    /**
     * 称重和外廓接收到数据后都会分别调用这个方法
     *
     * @param currentEntity
     */
    public static void processData(WeightAndLwhEntity currentEntity) {
        // 根据状态判断是由超重或超限调用(新流向-超重 0, 杜格-超限 1)
        int processStatus = currentEntity.getProcessStatus();
        String carNumber = processStatus == 0 ? currentEntity.getTruckNumber() : currentEntity.getPlate();
        //无车牌的直接进入异常数据
        if(carNumber == null || carNumber.equals("") || carNumber.contains("无车牌")){
/*            String remarkInfo = "";
            if (0 == processStatus) {
                remarkInfo = "称重系统检测无车牌。";
            } else {
                remarkInfo = "长宽高系统检测无车牌。";
            }
            currentEntity.setRemarkInfo(remarkInfo);
            DbUtil.insertExceptionData(currentEntity);*/
            return;
        }
        //排除小于2t的
/*        if(processStatus == 0 && currentEntity.getWeight() != null && currentEntity.getWeight().compareTo(new BigDecimal(2)) != 1){
            if(mapContainer.contains(carNumber)){
                mapContainer.remove(carNumber);
            }
            return;
        }*/
        long timeout = System.currentTimeMillis() + 30000;
        // 若30秒后仍然不能补全外廓数据或称重数据，回被定时任务清理，写入异常数据表
        currentEntity.setTimeoutMillseconds(timeout);
        // 查询是否为第一部分数据
        WeightAndLwhEntity previousEntity = mapContainer.putIfAbsent(carNumber, currentEntity);
        LOGGER.info("processData--" + carNumber + ",processStatus--" + processStatus + ",timeout--" + timeout);
        // 如果是第一次，不做任何操作
        if (null == previousEntity) {
            //插入内存队列，如果后续匹配不到，会删除内存中的数据并且插入异常数据到数据库
            NoMatchUtil.addEntity(currentEntity);
        } else {
            // 第二次插入，补全另一边数据数据，入库
            Integer stationId = Constants.station_id;
            previousEntity.setStationId(stationId);
            // 补齐剩余字段
            if (0 == processStatus) {
                // 超重的字段补充到previousEntity
                int LimitMode = currentEntity.getLimitMode();
                String VehicleType = currentEntity.getVehicleType();
                int WeightingId = currentEntity.getWeightingId();
                Timestamp WeightingDate = currentEntity.getWeightingDate();
                int Lane = currentEntity.getLane();
                String Direction = currentEntity.getDirection();
                String TruckNumber = currentEntity.getTruckNumber();
                String TruckCorlor = currentEntity.getTruckCorlor();
                BigDecimal Speed = currentEntity.getSpeed();
                int AxleCount = currentEntity.getAxleCount();
                String AxleType = currentEntity.getAxleType();
                BigDecimal Weight = currentEntity.getWeight();
                // 限重通过数据配置，新流向不直接提供
                BigDecimal LimitWeight = null;
                BigDecimal OverWeight = BigDecimal.ZERO;
                if (AxleCount > 0 && AxleCount <= 6) {
                    LimitWeight = new BigDecimal(weightLimitMap.get(AxleCount));
                    OverWeight = Weight.subtract(LimitWeight);
                    if (OverWeight.compareTo(new BigDecimal(0d)) <= 0) {
                        OverWeight = new BigDecimal(0d);
                    } else { // 车辆实际重量大于限制重量
                        // 计算超重百分比
                        BigDecimal actualOverWeightPercentage = OverWeight.divide(LimitWeight, 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100d));
                        // 筛选超重百分比
                        if (actualOverWeightPercentage.compareTo(new BigDecimal(overWeightPercentage)) >= 0) {
                            // 发送到LED屏
                            LedComponent.showMessageLed("【" + TruckNumber + "】您已超重\n限重：" + LimitWeight + "，总重：" + Weight.setScale(2, BigDecimal.ROUND_DOWN));
                        }
                    }
                }
                BigDecimal AxleWeight1 = currentEntity.getAxleWeight1();
                BigDecimal AxleWeight2 = currentEntity.getAxleWeight2();
                BigDecimal AxleWeight3 = currentEntity.getAxleWeight3();
                BigDecimal AxleWeight4 = currentEntity.getAxleWeight4();
                BigDecimal AxleWeight5 = currentEntity.getAxleWeight5();
                BigDecimal AxleWeight6 = currentEntity.getAxleWeight6();
                BigDecimal AxleWeight7 = currentEntity.getAxleWeight7();
                BigDecimal AxleWeight8 = currentEntity.getAxleWeight8();
                String FtpHead = currentEntity.getFtpHead();
                String FtpAxle = currentEntity.getFtpAxle();
                String FtpTail = currentEntity.getFtpTail();
                String FtpPriorHead = currentEntity.getFtpPriorHead();
                String FtpPlate = currentEntity.getFtpPlate();
                String FtpFullView = currentEntity.getFtpFullView();
                previousEntity.setLimitMode(LimitMode);
                previousEntity.setSequenceTag(currentEntity.getSequenceTag());
                previousEntity.setVehicleType(VehicleType);
                previousEntity.setWeightingId(WeightingId);
                previousEntity.setWeightingDate(WeightingDate);
                previousEntity.setLane(Lane);
                previousEntity.setDirection(Direction);
                previousEntity.setTruckNumber(TruckNumber);
                previousEntity.setTruckCorlor(TruckCorlor);
                if (null == previousEntity.getSpeed()) {
                    previousEntity.setSpeed(Speed);
                }
                previousEntity.setAxleCount(AxleCount);
                previousEntity.setAxleType(AxleType);
                previousEntity.setWeight(Weight);
                previousEntity.setLimitWeight(LimitWeight);
                previousEntity.setOverWeight(OverWeight);
                previousEntity.setAxleWeight1(AxleWeight1);
                previousEntity.setAxleWeight2(AxleWeight2);
                previousEntity.setAxleWeight3(AxleWeight3);
                previousEntity.setAxleWeight4(AxleWeight4);
                previousEntity.setAxleWeight5(AxleWeight5);
                previousEntity.setAxleWeight6(AxleWeight6);
                previousEntity.setAxleWeight7(AxleWeight7);
                previousEntity.setAxleWeight8(AxleWeight8);
                previousEntity.setFtpHead(FtpHead);
                previousEntity.setFtpAxle(FtpAxle);
                previousEntity.setFtpTail(FtpTail);
                previousEntity.setFtpPriorHead(FtpPriorHead);
                previousEntity.setFtpPlate(FtpPlate);
                previousEntity.setFtpFullView(FtpFullView);
                previousEntity.setWeightTag(currentEntity.isWeightTag());
            } else if (1 == processStatus) {
                // 超限的字段补充到previousEntity
                Timestamp lwhDate = currentEntity.getLwhDate();
                String plate = currentEntity.getPlate();
                String width = currentEntity.getWidth();
                String height = currentEntity.getHeight();
                String length = currentEntity.getLength();
                String laneMid = currentEntity.getLaneMid();
                String laneMin = currentEntity.getLaneMin();
                String laneMax = currentEntity.getLaneMax();
                String passTime = currentEntity.getPassTime();
                previousEntity.setLwhDate(lwhDate);
                previousEntity.setPlate(plate);
                previousEntity.setWidth(width);
                previousEntity.setHeight(height);
                previousEntity.setLength(length);
                previousEntity.setLaneMid(laneMid);
                previousEntity.setLaneMin(laneMin);
                previousEntity.setLaneMax(laneMax);
                previousEntity.setPassTime(passTime);
                previousEntity.setSizeTag(currentEntity.isSizeTag());
            } else {
                String plate = currentEntity.getPlate();
                previousEntity.setPlate(plate);
                BigDecimal Speed = currentEntity.getSpeed();
                previousEntity.setSpeed(Speed);
                previousEntity.setSpeedTag(currentEntity.isSpeedTag());
            }
            // 删除内存中的数据
            //if (previousEntity.isSizeTag() && previousEntity.isWeightTag() && previousEntity.isSpeedTag()) {
            if (previousEntity.isSizeTag() && previousEntity.isWeightTag()) {
                LOGGER.info("mapContainer insert " + carNumber);
                completeEntity(previousEntity, carNumber);
            } else {
                LOGGER.info("mapContainer put " + carNumber);
                mapContainer.put(carNumber, previousEntity);
            }
        }
    }

    public static void completeEntity(WeightAndLwhEntity previousEntity, String carNumber) {
        mapContainer.remove(carNumber);
        DbUtil.insertWeightAndLWH(previousEntity);
        LOGGER.info("insert DB :" + previousEntity.getPlate() + "-" + previousEntity.getLength() + "-" +
                previousEntity.getWeight() + "-" + previousEntity.getHeight() + ",mapContainer:" + mapContainer.size());
    }
}
