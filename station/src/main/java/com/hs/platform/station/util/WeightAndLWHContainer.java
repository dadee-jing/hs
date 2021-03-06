package com.hs.platform.station.util;

import com.hs.platform.station.Constants;
import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.led.LedComponent;
import com.hs.platform.station.schedule.NoMatchDataUpload;
import com.hs.platform.station.third.common.utils.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import static com.hs.platform.station.Constants.led_overWeight_percentage;
import static com.hs.platform.station.third.foshan.socket.StructUtil.getPicByStream;
import static com.hs.platform.station.util.DbUtil.*;
import static com.hs.platform.station.util.ImageDownloadUtil.station_id;

public class WeightAndLWHContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(WeightAndLWHContainer.class);
    private static final ConcurrentHashMap<String, WeightAndLwhEntity> mapContainer = new ConcurrentHashMap<>();
    private static Map<Integer, Long> weightLimitMap = getWeightLimitMap();
    private static String weightLimit = getConfigValue("weight_upload_limit");
    private static String onlyWeightUploadTag = getConfigValue("only_weight_upload");
    public static String lwhUploadFileTag = getConfigValue("lwh_upload_file");
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static AtomicLong lwhLatestTimeSecond = new AtomicLong(System.currentTimeMillis()/1000);
    public static String weightLatestTime = new Date().toString();
    private static final double overWeightPercentage = led_overWeight_percentage;

    /**
     * 清除内存中的没有匹配的对象，并插入数据库做记录
     *
     * @param currentEntity
     */
    public static void clearAndInsertDB(WeightAndLwhEntity currentEntity) {
        int processStatus = currentEntity.getProcessStatus();
        String carNumber = processStatus == 0 ? currentEntity.getTruckNumber() : currentEntity.getPlate();
        WeightAndLwhEntity previousEntity = mapContainer.get(carNumber);
        //null的话说明mapContainer匹配成功，已经执行插入，无需处理
        if (null != previousEntity) {
            //称重与外廓匹配上就执行插入
            if (previousEntity.isWeightTag() && previousEntity.isSizeTag()) {
                //LOGGER.info("clearAndInsertDB--" + carNumber);
                completeEntity(previousEntity, carNumber);
            } else {
                //配置文件，只有称重数据也执行上传
                if(previousEntity.isWeightTag() && "1".equals(onlyWeightUploadTag)){
                    LOGGER.info("only weight upload " + carNumber);
                    completeEntity(previousEntity, carNumber);
                    return;
                }
                mapContainer.remove(carNumber);
                //插入数据库做备份
                int previousStatus = previousEntity.getProcessStatus();
                String remarkInfo = "";
                if (previousEntity.isWeightTag()) {
                    remarkInfo = "长宽高数据缺失。";
                }
                else if(previousEntity.isSizeTag()) {
                    remarkInfo = "衡器称重数据缺失。";
                }
                else{
                    return;
                }
                previousEntity.setRemarkInfo(remarkInfo);
                DbUtil.insertExceptionData(previousEntity);
            }
        }
    }

    /**
     * 称重和外廓接收到数据后都会分别调用这个方法
     * @param currentEntity
     */
    public static void processData(WeightAndLwhEntity currentEntity) {
        // 根据状态判断是由超重或超限调用(0-新流向称重, 1-杜格外廓，2-测速雷达，3-左侧拍，4-右侧拍)
        //称重,测速可能多次触发
        int processStatus = currentEntity.getProcessStatus();
        String carNumber = processStatus == 0 ? currentEntity.getTruckNumber() : currentEntity.getPlate();
        LOGGER.info("processData--" + carNumber + ",processStatus--" + processStatus);
        //无车牌的直接插入异常表
        if(StringUtils.isBlank(carNumber) || carNumber.contains("无车牌")){
            String remarkInfo = "";
            if (0 == processStatus) {
                remarkInfo = "称重检测到无车牌。";
            }
            else if(1 == processStatus) {
                remarkInfo = "外廓检测到无车牌。";
            }
            else{
                return;
            }
            currentEntity.setRemarkInfo(remarkInfo);
            DbUtil.insertExceptionData(currentEntity);
            return;
        }
        //测速写入remark
        if(null != currentEntity.getSpeed()){
            if(processStatus == 0){
                currentEntity.setRemarkInfo(currentEntity.getRemarkInfo() + "weight speed:" + currentEntity.getSpeed() +",");
            }
            if(processStatus == 2){
                currentEntity.setRemarkInfo(currentEntity.getRemarkInfo() + "radar speed:" + currentEntity.getSpeed() +",");
            }
        }

        if(processStatus == 1){
            lwhLatestTimeSecond = new AtomicLong(System.currentTimeMillis()/1000);
        }
        else if(processStatus == 0){
            setWeightInfo(currentEntity,currentEntity);
        }
        long timeout = System.currentTimeMillis() + 30000;
        // 若30秒后仍然不能补全外廓数据或称重数据，会被定时任务清理，写入异常数据表
        currentEntity.setTimeoutMillseconds(timeout);
        // 查询是否为第一部分数据
        WeightAndLwhEntity previousEntity = mapContainer.putIfAbsent(carNumber, currentEntity);
        // 第一次插入
        if (null == previousEntity) {
            //插入内存队列，如果后续匹配不到，会删除内存中的数据并且插入异常数据到数据库
            NoMatchDataUpload.addEntity(currentEntity);
        } else {
            // 第二次插入，补全另一边数据数据，入库
            Integer stationId = station_id;
            previousEntity.setStationId(stationId);
            previousEntity.setRemarkInfo(previousEntity.getRemarkInfo() + currentEntity.getRemarkInfo());
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
                setWeightInfo(currentEntity,previousEntity);
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
                //previousEntity.setLimitWeight(LimitWeight);
                //previousEntity.setOverWeight(OverWeight);
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
                String lbh = currentEntity.getLbh();
                String lbw = currentEntity.getLbw();
                String lbl = currentEntity.getLbl();
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
                previousEntity.setLbh(lbh);
                previousEntity.setLbw(lbw);
                previousEntity.setLbl(lbl);
            } else if (2 == processStatus) {
                //补充测速雷达数据
                String plate = currentEntity.getPlate();
                previousEntity.setPlate(plate);
                BigDecimal Speed = currentEntity.getSpeed();
                previousEntity.setSpeed(Speed);
                previousEntity.setSpeedTag(currentEntity.isSpeedTag());
            } else if (3 == processStatus) {
                //补充左侧拍路径
                String plate = currentEntity.getPlate();
                previousEntity.setPlate(plate);
                previousEntity.setLeftSidePath(currentEntity.getLeftSidePath());
                previousEntity.setPathTag(previousEntity.getPathTag() + 1);
            }else if (4 == processStatus) {
                //补充右侧拍路径
                String plate = currentEntity.getPlate();
                previousEntity.setPlate(plate);
                previousEntity.setRightSidePath(currentEntity.getRightSidePath());
                previousEntity.setPathTag(previousEntity.getPathTag() + 1);
            }
            // 称重，外廓，测速，两张侧拍都匹配上了，执行插入。测速，侧拍作为补充，最后没有匹配上也会插入
            if (previousEntity.isSizeTag() && previousEntity.isWeightTag()
                    && previousEntity.isSpeedTag() && previousEntity.getPathTag() == 2) {
                completeEntity(previousEntity, carNumber);
            } else {
                mapContainer.put(carNumber, previousEntity);
            }
        }
    }

    private static void setWeightInfo(WeightAndLwhEntity currentEntity,WeightAndLwhEntity previousEntity) {
        int AxleCount = currentEntity.getAxleCount();
        String TruckNumber = currentEntity.getTruckNumber();
        BigDecimal Weight = currentEntity.getWeight();
        BigDecimal LimitWeight = null;
        BigDecimal OverWeight = BigDecimal.ZERO;
        if (AxleCount > 0 && AxleCount <= 6) {
            LimitWeight = new BigDecimal(weightLimitMap.get(AxleCount));
            OverWeight = Weight.subtract(LimitWeight);
            if (OverWeight.compareTo(new BigDecimal(0d)) <= 0) {
                OverWeight = new BigDecimal(0d);
            }
            else {
                // 车辆实际重量大于限制重量
                // 计算超重百分比
                BigDecimal actualOverWeightPercentage = OverWeight.divide(LimitWeight, 4, BigDecimal.ROUND_DOWN)
                        .multiply(new BigDecimal(100d));
                // 筛选超重百分比
                if (actualOverWeightPercentage.compareTo(new BigDecimal(overWeightPercentage)) >= 0) {
                    // 发送到LED屏
                    LedComponent.showMessageLed(TruckNumber.trim() + "\r\n涉嫌超载");
                }
            }
        }
        previousEntity.setLimitWeight(LimitWeight);
        previousEntity.setOverWeight(OverWeight);
    }

    public static void completeEntity(WeightAndLwhEntity previousEntity, String carNumber) {
        mapContainer.remove(carNumber);
        //小于设置重量的upload_tag设为1，不上传到154
        if(!StringUtils.isBlank(weightLimit) && !weightLimit.equals("0")){
            //-1小于，0等于，1大于
            if(previousEntity.getWeight() != null && previousEntity.getWeight().compareTo(new BigDecimal(weightLimit)) != 1){
                LOGGER.info("小于" +weightLimit+ "t,只做本地插入 " + carNumber);
                previousEntity.setUploadTag(1);
            }
        }
        //使用本地侧拍
        if("1".equals(lwhUploadFileTag) && previousEntity.getPathTag() != 0){
            changeLocalFile(previousEntity);
        }
        if(StringUtils.isNotBlank(previousEntity.getRemarkInfo())){
            previousEntity.setRemarkInfo(previousEntity.getRemarkInfo().replaceAll("null",""));
        }
        setLwhScenePic(previousEntity);
        DbUtil.insertWeightAndLWH(previousEntity);
        LOGGER.info("insertDB : " + previousEntity.getPlate() + "-" + previousEntity.getLength() + "-" +
                previousEntity.getWeight() + "-" + previousEntity.getHeight() + ",mapContainer:" + mapContainer.size());
    }

    private static void setLwhScenePic(WeightAndLwhEntity previousEntity) {
        try {
            String nowDate = DateFormatUtils.format(previousEntity.getLwhDate(), "yyyyMMddHH");
            String date = nowDate.substring(0, nowDate.length() - 2);
            String hour = nowDate.substring(nowDate.length() - 2);
            String path = "D:\\CameraPic\\" + date + "\\" + hour;
            List<File> files = FileUtil.searchFiles(new File(path), previousEntity.getPlate().toLowerCase());
            LOGGER.info("共找到:" + files.size() + "个文件");
            for (File file : files) {
                if (file.getAbsolutePath().contains("scene")) {
                    LOGGER.info("setLwhScenePic filePath:" + file.getAbsolutePath() + "," + previousEntity.getPlate());
                    previousEntity.setLwhScenePath(file.getAbsolutePath());
                    break;
                }
            }
        }catch (Exception e){
            LOGGER.info("setLwhScenePic error", e);
        }
    }

    private static void changeLocalFile(WeightAndLwhEntity previousEntity) {
        //本地侧拍覆盖新流向侧拍
        if(StringUtils.isNotBlank(previousEntity.getLeftSidePath())){
            previousEntity.setFtpHead("");//左
            //转化为服务器路径
            String sourcePath = previousEntity.getLeftSidePath();
            if(sourcePath.contains("\\")){
                sourcePath = sourcePath.replaceAll("\\\\","/");
            }
            String paths [] = sourcePath.split("/");
            String targetPath = "PicPlate/" + paths[4] + "/" + paths[5] ;
            previousEntity.setFtpHead(targetPath);
            //LOGGER.info("getLeftSidePath:" + sourcePath + ",targetPath:" + targetPath);
        }
        if(StringUtils.isNotBlank(previousEntity.getRightSidePath())){
            previousEntity.setFtpAxle("");//右
            //转化为服务器路径
            String sourcePath = previousEntity.getRightSidePath();
            if(sourcePath.contains("\\")){
                sourcePath = sourcePath.replaceAll("\\\\","/");
            }
            String paths [] = sourcePath.split("/");
            String targetPath = "PicPlate/" + paths[4] + "/" + paths[5] ;
            previousEntity.setFtpAxle(targetPath);
            //LOGGER.info("getRightSidePath:" + sourcePath + ",targetPath:" + targetPath);
        }
    }

}
