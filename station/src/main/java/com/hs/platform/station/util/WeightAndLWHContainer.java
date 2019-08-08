package com.hs.platform.station.util;

import com.hs.platform.station.Constants;
import com.hs.platform.station.entity.WeightAndLwhEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hs.platform.station.util.DbUtil.getWeightLimitMap;

public class WeightAndLWHContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(WeightAndLWHContainer.class);

    private static final ConcurrentHashMap<String, WeightAndLwhEntity> mapContainer = new ConcurrentHashMap<>();

    private static Map<Integer, Long> weightLimitMap = getWeightLimitMap();

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

    /**
     * 称重和外廓接收到数据后都会分别调用这个方法
     * @param currentEntity
     */
    public static void processData(WeightAndLwhEntity currentEntity) {
        // 根据状态判断是由超重或超限调用(新流向-超重 0, 杜格-超限 1)
        int processStatus = currentEntity.getProcessStatus();
        String carNumber = processStatus == 0 ? currentEntity.getTruckNumber() : currentEntity.getPlate();
        long timeout = System.currentTimeMillis() + 30000;
        // 若30秒后仍然不能补全外廓数据或称重数据，回被定时任务清理，写入异常数据表
        currentEntity.setTimeoutMillseconds(timeout);
        // 查询是否为第一部分数据
        WeightAndLwhEntity previousEntity = mapContainer.putIfAbsent(carNumber, currentEntity);
        // 如果是第一次，不做任何操作
        if (null == previousEntity) {
            //插入内存队列，如果后续匹配不到，会删除内存中的数据并且插入异常数据到数据库
            NoMatchUtil.addEntity(currentEntity);

        } else {
            // 第二次插入，补全另一边数据数据，入库
            Integer stationId = Constants.station_id;
            int previousProcessStatus = previousEntity.getProcessStatus();
            if (previousProcessStatus == processStatus) {
                //出现同一辆车的长宽高数据或称重数据出现多次，正常情况下是不会有的
            } else {
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
                    previousEntity.setSpeed(Speed);
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
                    previousEntity.setProcessStatus(2);

                }
                if (1 == processStatus) {
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
                    previousEntity.setProcessStatus(2);
                }
                // 删除内存中的数据
                mapContainer.remove(carNumber);
                DbUtil.insertWeightAndLWH(previousEntity);

                LOGGER.info("insert DB :" + previousEntity.getPlate() + "-" + previousEntity.getLength() + "-" + previousEntity.getWeight() + "-" + previousEntity.getHeight());
            }
        }

    }

}
