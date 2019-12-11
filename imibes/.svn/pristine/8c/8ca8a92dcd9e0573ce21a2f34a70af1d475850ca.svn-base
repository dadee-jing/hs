package com.ruoyi.duge.third.guangdong.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.common.httphelper.HttpCustomClient;
import com.ruoyi.common.httphelper.HttpCustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duge.domain.DeviceData;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.third.guangdong.model.GDEquipmentStatusRequest;
import com.ruoyi.duge.third.guangdong.model.GDResponse;
import com.ruoyi.duge.third.guangdong.model.GDVehicleDataRequest;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.service.ThirdApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.util.*;

import static com.ruoyi.common.utils.DateUtils.YYYY_MM_DD_HH_MM_SS;
import static com.ruoyi.duge.third.guangdong.GDConstant.mappingPlateColor;
import static com.ruoyi.duge.third.guangdong.GDConstant.mappingVehicleColor;

@Service("GuangdongApi")
public class GuandongApiService implements ThirdApiService {

    private final HttpCustomClient httpCustomClient;

    private final ObjectMapper objectMapper;

    private static final String ENCODING = "UTF-8";

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

    /**
     * TODO 接口地址
     */
    private final String openApiHost;

    /**
     * TODO 认证ID
     */
    private final String authorizeId;

    private Map<String, String> header;

    @Autowired
    public GuandongApiService(HttpCustomClient httpCustomClient) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.httpCustomClient = httpCustomClient;
        this.header = new HashMap<>();
        this.header.put("content-type", CONTENT_TYPE);
        this.openApiHost = "http://120.196.48.101:7003";
        this.authorizeId = "UEJDWjQ0MDRQVEpT";
    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        GDVehicleDataRequest gdVehicleDataRequest = getGDVehicleDataRequest(request.getWeightData());
        return callOpenApi(this.openApiHost + "/equipment/vehicleData", gdVehicleDataRequest);
    }

    @Override
    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request) {
        GDEquipmentStatusRequest gdEquipmentStatusRequest = getGDEquipmentStatusRequest(request.getDeviceData());
        return callOpenApi(this.openApiHost + "/equipment/statusData", gdEquipmentStatusRequest);
    }

    private BaseThirdApiResponse callOpenApi(String url, Object paramObject) {
        String errorCode = null, errorMsg = null;
        try {
            Map<String, String> param = new HashMap<>(2);
            param.put("authorizeId", this.authorizeId);
            param.put("strData", Base64Utils.encodeToString(objectMapper.writeValueAsBytes(paramObject)));
            String responseStr = httpCustomClient.doPost(url, header, param);
            GDResponse gdResponse = objectMapper.readValue(responseStr, GDResponse.class);
            return getBaseResponse(gdResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (HttpCustomException e) {
            errorCode = "HTTP EXCEPTION";
            errorMsg = e.getMessage();
        } catch (UnsupportedEncodingException e) {
            errorCode = "ENCODING ERROR";
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorCode = "SYSTEM ERROR";
            errorMsg = e.getMessage();
        }
        return getErrorResponse(errorCode, errorMsg);
    }

    private BaseThirdApiResponse getBaseResponse(GDResponse gdResponse) {
        return BaseThirdApiResponse.builder()
                .businessStatus(GDResponse.SUCCESS == gdResponse.getRscode() ?
                        BusinessStatus.SUCCESS : BusinessStatus.FAIL)
                .errorCode(null != gdResponse.getErrorCode() ? gdResponse.getErrorCode().name() : null)
                .errorMsg(gdResponse.getRsmsg())
                .build();
    }

    private BaseThirdApiResponse getErrorResponse(String errorCode, String errorMsg) {
        return BaseThirdApiResponse.builder()
                .businessStatus(BusinessStatus.FAIL)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    private GDEquipmentStatusRequest getGDEquipmentStatusRequest(DeviceData deviceData) {
        return GDEquipmentStatusRequest.builder()
                .indexCode(deviceData.getIndexCode())
                .deviceStatus(String.valueOf(deviceData.getCheckState()))   //TODO 映射逻辑
                .checkTime(DateUtils.parseDateToStr(YYYY_MM_DD_HH_MM_SS, deviceData.getCheckDate()))
                .exceptionReason(deviceData.getExceptionDesc())
                .build();
    }

    private GDVehicleDataRequest getGDVehicleDataRequest(WeightData weightData) {
        List<GDVehicleDataRequest.Axle> axleList = new ArrayList<>();
        if (null != weightData.getAxleWeight1()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(1)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight1().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight2()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(2)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight2().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight3()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(3)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight3().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight4()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(4)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight4().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight5()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(5)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight5().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight6()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(6)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight6().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight7()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(7)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight7().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }
        if (null != weightData.getAxleWeight8()) {
            axleList.add(GDVehicleDataRequest.Axle.builder()
                    .axleNo(8)
                    .axleDistance(null) // TODO 轴距
                    .axleInfo(weightData.getAxleWeight8().intValue())
                    .axleSpeed(null)    // 车速
                    .build());
        }

        // TODO
        Date now = new Date();
        return GDVehicleDataRequest.builder()
                // TODO 机构代码+唯一id
                .dataID(DateUtils.parseDateToStr("yyyyMMdd", now) + String.format("%06d", weightData.getId().intValue()))
                .indexCode(weightData.getStationName()) //TODO 机构代码+ 卡口编号
                .checkID("") //TODO 机构代码+ 检测点编号
                .plateNo(weightData.getPlate())
                .plateColor(mappingPlateColor(weightData.getTruckCorlor()))
                .plateType("1")   // TODO 车牌类型？
                .vehicleColor(mappingVehicleColor("")) // TODO 颜色映射
                .vehicleType("1") // TODO 车辆类型映射 weightData.getVehicleType()
                .vehicleSpeed(weightData.getSpeed().doubleValue())
                .laneNo(weightData.getLane())
                .isOverWeight(null != weightData.getOverWeight() &&
                        weightData.getOverWeight().doubleValue() > 0 ? "1" : "0")
                .axleNum(weightData.getAxleCount())
                .isDirect("0") // TODO weightData.getDirection()
                .overWeight(null != weightData.getOverWeight() ? weightData.getOverWeight().doubleValue() : 0)
                .vehicleWeight(weightData.getWeight().intValue())
                .limitWeight(weightData.getLimitWeight().intValue())
                .checkTime(DateUtils.parseDateToStr(YYYY_MM_DD_HH_MM_SS, weightData.getWeightingDate()))
                .passCode(DateUtils.parseDateToStr("yyyyMMdd", now) + String.format("%06d", weightData.getId().intValue()))
                .dataIsCredible("0")
                .drivingDirection("1") // 1上行 2下行 weightData.getDirection()
                .isDangerous("0")
                .tyreNums(0)
                .loadRate(null != weightData.getOverWeight() ?
                        weightData.getOverWeight().divide(weightData.getWeight(), RoundingMode.HALF_UP).doubleValue() : 0)
                .vehicleHeight(Integer.parseInt(weightData.getHeight()))
                .vehicleWidth(Integer.parseInt(weightData.getWidth()))
                .vehicleLong(Integer.parseInt(weightData.getLength()))
                .lengthoverLimitedRate(null)    //TODO 超长
                .widthoverLimitedRate(null)     // TODO 超宽
                .heightoverLimitedRate(null)    // TODO 超高
                .lengthoverLimited(null)
                .widthoverLimited(null)
                .heightoverLimited(null)
                .axleArray(axleList)
                // TODO 参考文档图片要求补全
                .imageArray(Arrays.asList(GDVehicleDataRequest.Image.builder()
                                .imageName("秤前车头图片")
                                .imageFeature("D")
                                .imageURL(weightData.getFtpPriorHead())
                                .build(),
                        GDVehicleDataRequest.Image.builder()
                                .imageName("车牌特征图")
                                .imageFeature("X")
                                .imageURL(weightData.getFtpPlate())
                                .build(),
                        GDVehicleDataRequest.Image.builder()
                                .imageName("车头图片地址（秤上）")
                                .imageFeature("")
                                .imageURL(weightData.getFtpHead())
                                .build(),
                        GDVehicleDataRequest.Image.builder()
                                .imageName("车尾图片地址（秤上）")
                                .imageFeature("")
                                .imageURL(weightData.getFtpTail())
                                .build(),
                        GDVehicleDataRequest.Image.builder()
                                .imageName("车轴图片地址（秤上）")
                                .imageFeature("")
                                .imageURL(weightData.getFtpAxle())
                                .build()))
                .videoArray(Collections.singletonList(GDVehicleDataRequest.Video.builder()
                        .videoName("车头视频文件")
                        .videoURL(weightData.getFtpFullView())
                        .build()))
                .build();
    }
}
