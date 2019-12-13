package com.ruoyi.duge.third.shunde.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.mapper.WeightDataMapper;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.service.ThirdApiService;
import com.ruoyi.duge.third.shunde.persistence.VehicleImage;
import com.ruoyi.duge.third.shunde.persistence.VehicleRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;

@Service("ShundeApi")
public class ShundeApiService implements ThirdApiService {

    @Autowired
    private IConfigDataService configDataService;

    @Autowired
    private WeightDataMapper weightDataMapper;

    private DruidDataSource dataSource = null;

    private java.sql.Connection conn = null;

    private static final Logger log = LoggerFactory.getLogger(ShundeApiService.class);

    @PostConstruct
    public void init() {
        try {
            GetDbConnect();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "${upload_yhl_task_cron}")
    public void scanVehicleData() {
        if ("1".equals(configDataService.getConfigValue("upload_yhl"))) {
            List<WeightData> weightDataList = weightDataMapper.selectNotUploadYhl();
            if (null != weightDataList && weightDataList.size() > 0) {
                log.info("to insert count:" + weightDataList.size());
                LongAdder successCount = new LongAdder();
                    weightDataList.forEach(weightData -> {
                        // log.info("to insert" + weightData.getTruckNumber());
                        if(isAllFileExist(weightData)) {
                            BaseVehicleDataRequest baseVehicleDataRequest = BaseVehicleDataRequest.builder()
                                    .weightData(weightData)
                                    .build();
                            BaseThirdApiResponse baseThirdApiResponse =
                                    submitVehicleData(baseVehicleDataRequest);
                            if (BusinessStatus.SUCCESS == baseThirdApiResponse.getBusinessStatus()) {
                                weightData.setUploadYhl(1);
                                successCount.increment();
                                //log.info("ok insert" + weightData.getTruckNumber());
                            } else {
                                weightData.setUploadYhl(2);
                                log.info("error insert" + weightData.getTruckNumber());
                            }
                            weightDataMapper.updateData(weightData);
                        }
                        else{
                            weightData.setUploadYhl(3);
                            weightDataMapper.updateData(weightData);
                        }
                    });
                log.info("success count:" + successCount);
            }
        }
    }

    /**
     * 10t以上，6个文件都全才插入
     * 10t以下，只要5张图片全，就插入，视频有也不插入
     */
    private boolean isAllFileExist(WeightData weightData) {
        String sids = configDataService.getConfigValue("all_pic_upload_yhl");
        if (null == sids || !sids.contains("," + weightData.getStationId() + ",")) {
            return true;
        } else if (StringUtils.isNotBlank(weightData.getFtpPriorHead()) && StringUtils.isNotBlank(weightData.getFtpTail()) &&
                StringUtils.isNotBlank(weightData.getFtpHead()) && StringUtils.isNotBlank(weightData.getFtpAxle()) &&
                StringUtils.isNotBlank(weightData.getFtpPlate())) {
            if (weightData.getWeight().compareTo(new BigDecimal(10)) > -1) {
                return StringUtils.isNotBlank(weightData.getFtpFullView());
            }
            weightData.setFtpFullView(null);
            return true;
        }
        return false;
    }

    @Override
    public synchronized BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {

        WeightData weightData = null;
        VehicleRecord vehicleRecord = null;
        try {
            conn = getConnect();
            weightData = request.getWeightData();
            vehicleRecord = VehicleRecord.builder()
                    .id(weightData.getId())
                    .recordId(UUID.randomUUID().toString().replace("-", ""))
                    .stationId(weightData.getStationId().intValue())
                    .plateNo(weightData.getTruckNumber())
                    .plateColor(parseColor(weightData.getTruckCorlor()))
                    .vehicleSpeed(null != weightData.getSpeed() ? weightData.getSpeed().doubleValue() : 0)
                    .laneNo(NumberUtils.toInt(weightData.getLaneMid(), weightData.getLane()))
                    .axleNum(weightData.getAxleCount())
                    .vehicleWeight(null != weightData.getWeight() ? weightData.getWeight().doubleValue() : 0)
                    .vehicleHeight(getSizeValue(weightData.getHeight()))
                    .vehicleWidth(getSizeValue(weightData.getWidth()))
                    .vehicleLength(getSizeValue(weightData.getLength()))
                    .overLine(weightData.getLaneMax() != null
                            && !weightData.getLaneMax().equals(weightData.getLaneMin()))
                    .overWeightRadio(null != weightData.getOverWeight() && null != weightData.getLimitWeight()
                            && weightData.getLimitWeight().doubleValue() > 0 ?
                            String.format("%.2f", 100 * weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue()) : "0")
                    .overSpeedRadio(null != weightData.getSpeed() && weightData.getSpeed().doubleValue() > 70 ?
                            String.format("%.2f", (weightData.getSpeed().doubleValue() - 70) / 70) + "" : "0")
                    .overHeightRadio(getSizeValue(weightData.getHeight()) > 4.2 ? String.format("%.2f", (getSizeValue(weightData.getHeight()) - 4.2) / 4.2) : "0")
                    .overWidthRadio(getSizeValue(weightData.getWidth()) > 2.75 ? String.format("%.2f", (getSizeValue(weightData.getWidth()) - 2.75) / 2.75) : "0")
                    .overLengthRadio(getSizeValue(weightData.getLength()) > lengthLimit(weightData.getAxleCount()) ?
                            String.format("%.2f", (getSizeValue(weightData.getLength()) - lengthLimit(weightData.getAxleCount())) / lengthLimit(weightData.getAxleCount())) : "0")
                    .overSpeed(weightData.getSpeed() != null
                            && weightData.getSpeed().doubleValue() > 70d)
                    .overWeight(weightData.getOverWeight() != null
                            && weightData.getOverWeight().doubleValue() > 0)
                    .overHeight(getSizeValue(weightData.getHeight()) >= 4.201)
                    .overWidth(getSizeValue(weightData.getWidth()) >= 2.751)
                    .overLength(getSizeValue(weightData.getLength()) > lengthLimit(weightData.getAxleCount()) + 0.01)
                    .checkDate(weightData.getWeightingDate())
                    .createDate(weightData.getCreateTime())
                    .modifyDate(weightData.getUpdateTime())
                    //.memo(weightData.getRemark())
                    .laneID(weightData.getLaneId())
                    .build();
            insertVehicleRecord(vehicleRecord);
        } catch (Exception e) {
            if (!e.getMessage().contains("ORA-00001")) {
                log.error(e.getMessage(), e);
                if (null != conn) {
                    try {
                        conn.close();
                    } catch (Exception e1) {
                        log.error(e1.getMessage(), e1);
                    }
                }
                return getErrorResponse("SQL EXCEPTION", e.getMessage());
            }
        }

        if (StringUtils.isNotBlank(weightData.getFtpPlate())) {
            try {
                VehicleImage plateImage = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(2)
                        .imageFileName(weightData.getFtpPlate().substring(weightData.getFtpPlate().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpPlate())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(plateImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(weightData.getFtpPriorHead())) {
            try {
                VehicleImage headImage = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(1)
                        .imageFileName(weightData.getFtpPriorHead().substring(weightData.getFtpPriorHead().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpPriorHead())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(headImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(weightData.getFtpTail())) {
            try {
                VehicleImage tailImage = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(3)
                        .imageFileName(weightData.getFtpTail().substring(weightData.getFtpTail().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpTail())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(tailImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(weightData.getFtpHead())) {
            try {
                VehicleImage leftImage = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(4)
                        .imageFileName(weightData.getFtpHead().substring(weightData.getFtpHead().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpHead())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(leftImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(weightData.getFtpAxle())) {
            try {
                VehicleImage rightImage = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(4)
                        .imageFileName(weightData.getFtpAxle().substring(weightData.getFtpAxle().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpAxle())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(rightImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(weightData.getFtpFullView())) {
            try {
                VehicleImage video = VehicleImage.builder()
                        .imgId(UUID.randomUUID().toString().replace("-", ""))
                        .recordId(vehicleRecord.getRecordId())
                        .imgType(5)
                        .imageFileName(weightData.getFtpFullView().substring(weightData.getFtpFullView().lastIndexOf('/') + 1))
                        .imageFilePath(configDataService.getConfigValue("nginx_url") + weightData.getStationId()
                                + "/" + DateFormatUtils.format(weightData.getWeightingDate(), "yyyyMMdd") + "/"
                                + weightData.getFtpFullView())
                        .createDate(weightData.getCreateTime())
                        .modifyDate(weightData.getUpdateTime())
                        .memo(weightData.getRemark())
                        .build();
                insertVehicleImage(video);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (Exception e1) {
                log.error(e1.getMessage(), e1);
            }
        }

        return BaseThirdApiResponse.builder()
                .businessStatus(BusinessStatus.SUCCESS)
                .build();
    }

    static double lengthLimit(int alexCount) {
        switch (alexCount) {
            case 2:
                return 12;
            case 3:
                return 13.7;
            default:
                return 18.1;
        }
    }

    static Integer parseColor(String colorStr) {
        switch (colorStr) {
            case "黄":
                return 2;
            case "蓝":
                return 1;
            case "黑":
                return 3;
            case "绿":
                return 5;
            case "白":
                return 4;
            default:
                return 9;
        }
    }

    static Double getSizeValue(String str) {
        if (null != str) {
            return NumberUtils.toDouble(str, 0) / 1000;
        } else {
            return 0d;
        }
    }

    public void insertVehicleRecord(VehicleRecord vehicleRecord) throws Exception {
        String sql = "INSERT INTO \"TOCC_ZHICHAO\".\"VEHICLE_RECORD\" (\"ID\", \"RECORD_ID\", \"STATION_ID\", \"PLATE_NO\"," +
                " \"PLATE_COLOR\", \"LANE_NO\", \"AXLE_NUM\", \"VEHICLE_SPEED\", \"VEHICLE_WEIGHT\", \"VEHICLE_HEIGHT\", \"VEHICLE_WIDTH\"," +
                " \"VEHICLE_LENGTH\", \"OVER_SPEED\", \"OVER_WEIGHT\", \"OVER_HEIGHT\", \"OVER_WIDTH\", \"OVER_LENGTH\", \"MEMO\", \"CHECK_DATE\"," +
                " \"CREATE_DATE\", \"MODIFY_DATE\", \"OVER_LINE\", \"OVER_WEIGHT_RATIO\", \"OVER_HEIGHT_RATIO\", \"OVER_WIDTH_RATIO\"," +
                " \"OVER_LENGTH_RATIO\", \"OVER_SPEED_RATIO\", \"LANE_ID\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, vehicleRecord.getId());
        ps.setString(2, vehicleRecord.getRecordId());
        ps.setInt(3, vehicleRecord.getStationId());
        ps.setString(4, vehicleRecord.getPlateNo());
        ps.setInt(5, vehicleRecord.getPlateColor());
        ps.setInt(6, vehicleRecord.getLaneNo());
        ps.setInt(7, vehicleRecord.getAxleNum());
        ps.setDouble(8, vehicleRecord.getVehicleSpeed());
        ps.setDouble(9, vehicleRecord.getVehicleWeight());
        ps.setDouble(10, vehicleRecord.getVehicleHeight());
        ps.setDouble(11, vehicleRecord.getVehicleWidth());
        ps.setDouble(12, vehicleRecord.getVehicleLength());
        ps.setBoolean(13, vehicleRecord.getOverSpeed());
        ps.setBoolean(14, vehicleRecord.getOverWeight());
        ps.setBoolean(15, vehicleRecord.getOverHeight());
        ps.setBoolean(16, vehicleRecord.getOverWidth());
        ps.setBoolean(17, vehicleRecord.getOverLength());
        ps.setString(18, vehicleRecord.getMemo());
        ps.setTimestamp(19, new Timestamp(vehicleRecord.getCheckDate().getTime()));
        ps.setTimestamp(20, new Timestamp(vehicleRecord.getCreateDate().getTime()));
        ps.setTimestamp(21, new Timestamp(vehicleRecord.getModifyDate().getTime()));
        ps.setBoolean(22, vehicleRecord.getOverLine());
        ps.setString(23, vehicleRecord.getOverWeightRadio());
        ps.setString(24, vehicleRecord.getOverHeightRadio());
        ps.setString(25, vehicleRecord.getOverWidthRadio());
        ps.setString(26, vehicleRecord.getOverLengthRadio());
        ps.setString(27, vehicleRecord.getOverSpeedRadio());
        ps.setInt(28,vehicleRecord.getLaneID());
        log.debug(ps.toString());
        ps.executeUpdate();
        conn.commit();
    }

    public void insertVehicleImage(VehicleImage vehicleImage) throws Exception {
        String sql = "INSERT INTO \"TOCC_ZHICHAO\".\"VEHICLE_IMG\" ( \"IMG_ID\", \"RECORD_ID\", \"IMG_TYPE\", \"IMG_FILE_NAME\"," +
                " \"IMG_FILE_PATH\", \"CREATE_DATE\", \"MODIFY_DATE\", \"MEMO\") " +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, vehicleImage.getImgId());
        ps.setString(2, vehicleImage.getRecordId());
        ps.setInt(3, vehicleImage.getImgType());
        ps.setString(4, vehicleImage.getImageFileName());
        ps.setString(5, vehicleImage.getImageFilePath());
        ps.setTimestamp(6, new Timestamp(vehicleImage.getCreateDate().getTime()));
        ps.setTimestamp(7, new Timestamp(vehicleImage.getModifyDate().getTime()));
        ps.setString(8, vehicleImage.getMemo());
        log.debug(ps.toString());
        ps.executeUpdate();
        conn.commit();
    }

    @Override
    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request) {
        return null;
    }

    private BaseThirdApiResponse getErrorResponse(String errorCode, String errorMsg) {
        return BaseThirdApiResponse.builder()
                .businessStatus(BusinessStatus.FAIL)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    public java.sql.Connection getConnect() throws Exception {
        java.sql.Connection con = null;
        try {
            GetDbConnect();
            con = dataSource.getConnection();
        } catch (Exception e) {
            throw e;
        }
        return con;
    }

    public void GetDbConnect() throws Exception {
        try {
            if (dataSource == null) {
                dataSource = new DruidDataSource();
                //设置连接参数
                dataSource.setUrl(configDataService.getConfigValue("yhl.db.host"));
                dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                dataSource.setUsername(configDataService.getConfigValue("yhl.db.user"));
                dataSource.setPassword(configDataService.getConfigValue("yhl.db.password"));
                //配置初始化大小、最小、最大
                dataSource.setInitialSize(1);
                dataSource.setMinIdle(1);
                dataSource.setMaxIdle(4);
                dataSource.setMaxActive(8);
                //连接泄漏监测
                dataSource.setRemoveAbandoned(true);
                dataSource.setRemoveAbandonedTimeout(30);
                //配置获取连接等待超时的时间
                dataSource.setMaxWait(10000);
                //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                dataSource.setTimeBetweenEvictionRunsMillis(20000);
                //防止过期
                dataSource.setValidationQuery("SELECT 1 FROM DUAL");
                dataSource.setTestWhileIdle(true);
                dataSource.setTestOnBorrow(true);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
