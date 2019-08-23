package com.ruoyi.duge.third.trafficPolice.service;

import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.common.httphelper.HttpCustomClient;
import com.ruoyi.common.utils.XStreamUtil;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.duge.third.guangdong.model.GDResponse;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.service.ThirdApiService;
import com.ruoyi.duge.third.trafficPolice.model.*;
import com.ruoyi.duge.third.trafficPolice.utils.ImageUploadUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class TrafficPoliceApiService implements ThirdApiService {

    private final HttpCustomClient httpCustomClient;
    private final IStationInfoService stationInfoService;
    private final Base64.Encoder encoder;

    private final String CONTENT_TYPE = "text/xml;charset=utf-8"; // 请求头内容类型
    private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; // XML报文头
    private final String openApiHost = "http://192.168.159.130:9999/EHL_TIRCP_WS_JR/services/tircpService?wsdl"; // 目标URL地址
    private final String PictureServerIP = "http://192.168.1.201/"; // 图片服务器地址

    private Map<String, String> header; // 请求头

    @Autowired
    public TrafficPoliceApiService(HttpCustomClient httpCustomClient,
                                   IStationInfoService stationInfoService) {
        this.httpCustomClient = httpCustomClient;
        this.stationInfoService = stationInfoService;
        this.encoder = Base64.getEncoder();
        this.header = new HashMap<>();
        this.header.put("Content-Type", CONTENT_TYPE);
    }

    @Override
    public BaseThirdApiResponse submitVehicleData(BaseVehicleDataRequest request) {
        String errorCode = null, errorMsg = null;
        try {
            List<Data> dataList = request.getWeightDataList().stream().map(weightData -> {
                    StationInfo stationInfo = stationInfoService.selectStationInfoById(weightData.getStationId().intValue());
                Data data = null;
                Double overRate = weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue();
                int overLevel = overRate > 0 ? overRate >= 0.3 ? 2 : 1 : 0;
                if (overLevel > 0) {
                    try {
                        data = Data.builder()
                                .sbbh("") // 设备编号（取值：12位设备所在部门编号+6位的流水号）
                                .hpzl(getPlateType(weightData.getTruckCorlor(), weightData.getTruckNumber())) // 号牌种类（取值：TYPEID="2000005"（取值见GA/T 16.7））
                                .hphm(getEncodingUTF8(weightData.getPlate())) // 超速车牌号
                                .wfxw(overLevel == 1 ? "1353" : "1637") // 违法行为（取值：TYPEID="2001001" （参见：GA/T 16.31-2012））
                                .wfsj(DateFormatUtils.format(weightData.getWeightingDate(), "yyyy-MM-dd HH:mm:ss")) // 违法时间
                                .wfdd("") // 违法地点（编号）（取值：TYPEID="2001002" （参见：GA/T 16.33-2012））
                                .wfdz(getEncodingUTF8(stationInfo.getAddress())) // 违法地址的描述
                                .cdbh(weightData.getLane()) // 车道号
                                .clsd(weightData.getSpeed().doubleValue()) // 行驶速度
                                .clxs(stationInfo.getSpeedLimit()) // 车辆限速
                                .hptztp(getBase64Encoder(weightData.getFtpPlate())) // 号牌特征图片
                                .jsrtztp("") // 驾驶人特征图片
                                .zjwj1(getBase64Encoder(weightData.getFtpHead())) // 违法图片1
                                .zjwj2(getBase64Encoder(weightData.getFtpAxle())) // 违法图片2
                                .zjwj3(getBase64Encoder(weightData.getFtpTail())) // 违法图片3
                                .zjwj4(getBase64Encoder(weightData.getFtpPriorHead())) // 违法图片4
                                .splj(weightData.getFtpFullView()) // 视频路径
                                .cjfs("")  // 采集方式（取值：1-闯红灯设备；2-公路卡口设备；3测速设备；4-闭路电视；5-移动摄像；6-警务通；9-其它电子设备）
                                .cjjg("") // 采集机关（12 位 取值自 GA 380 -2002。）
                                .sfqj("00") // 是否区间（取值：00-非区间(默认),01-区间）
                                .kswfsj(null) // 区间开始违法时间
                                .kswfdd(null) // 区间开始违法地点（编号）（取值：TYPEID="2001002" （参见：GA/T 16.33-2012））
                                .kswfdz(null) // 区间开始违法地址
                                .qjjl(null) // 区间距离
                                .qjys(null) // 区间用时
                                .csbl(null) // 区间超速比例
                                .bz("") // 备注
                                .zpsl(0) // 违法图片数量
                                .zpwjm("") // 照片文件名
                                .fxbh("") // 方向编号（取值：TYPEID=”2000006”）
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return data;
            }).filter(Objects::nonNull).collect(Collectors.toList());

            // 处理XML格式数据
            Root root = Root.builder()
                    .tvmr(Tvmr.builder()
                            .version("1.0")
                            .dataList(dataList)
                            .build())
                    .build();

            String XTLB = "01"; // 系统类别
            String JKXLH = ""; // 接口序列号
            String JKID = "01C02"; // 接口标识
            String WriteXmlDoc = "<![CDATA[" + XML_HEADER + XStreamUtil.objectToXml(root) + "]]>"; // 写入数据
            String xmlContent = getSoapData(XTLB, JKXLH, JKID, WriteXmlDoc); // 拼接SOAP报文格式数据

            String responseStr = httpCustomClient.doPost(openApiHost, header, xmlContent);
            String returnStr = responseStr.substring(responseStr.indexOf("<return>") + "<return>".length(), responseStr.indexOf("</return>"));
            String transformData = StringEscapeUtils.unescapeXml(returnStr);
            TPResponse response = XStreamUtil.xmlToObject(transformData, TPResponse.class);
            return getBaseResponse(response);
        } catch (Exception e) {
            errorCode = "SYSTEM ERROR";
            errorMsg = e.getMessage();
        }
        return getErrorResponse(errorCode, errorMsg);
    }

    /**
     * 获取号牌种类
     *
     * @param plateColor  车牌颜色
     * @param plateNumber 车牌号码
     * @return 号牌种类编号
     */
    private String getPlateType(String plateColor, String plateNumber) {
        if (null == plateColor || null == plateNumber) {
            return "99";
        }
        if ("WJ".equalsIgnoreCase(plateNumber.substring(0, 2))) {
            return "31";
        }
        if (plateNumber.contains("军")) {
            return "32";
        }
        switch (plateColor) {
            case "蓝":
                return "02";
            case "黄":
                if (plateNumber.contains("学")) {
                    return "16";
                }
                return "01";
            case "白":
                if (plateNumber.contains("警")) {
                    return "23";
                } else {
                    return "20";
                }
            case "黑":
                if (plateNumber.contains("使")) {
                    return "03";
                } else if (plateNumber.contains("领")) {
                    return "04";
                } else if (plateNumber.contains("港")) {
                    return "26";
                } else if (plateNumber.contains("澳")) {
                    return "27";
                }
            default:
                return "99";
        }
    }

    /**
     * Base64编码处理
     * @param param 参数
     * @return Base64编码处理的字符串
     * @throws Exception
     */
    private String getBase64Encoder(String param) throws Exception {
        if (null != param && !("".equals(param))) {
            return getEncodingUTF8(encoder.encodeToString((PictureServerIP + param).getBytes("utf-8")));
        }
        return "";
    }

    /**
     * UTF-8编码转换
     * @param param 参数
     * @return UTF-8编码转换的字符串
     * @throws Exception
     */
    private String getEncodingUTF8(String param) throws Exception {
        if (null != param && !("".equals(param))) {
            return URLEncoder.encode(param, "utf-8");
        }
        return null;
    }

    /**
     * 拼接SOAP格式报文数据
     * @param arg0 系统类别
     * @param arg1 接口序列号
     * @param arg2 接口标识
     * @param arg3 写入数据
     * @return
     */
    private String getSoapData(String arg0, String arg1, String arg2, String arg3) {
        StringBuffer xmlContent = new StringBuffer("");
        xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlContent.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap"
                + "/envelope/\" xmlns:ser=\"http://server.aspire.com/\">\n");
        xmlContent.append("   <soapenv:Header/>\n");
        xmlContent.append("   <soapenv:Body>\n");
        xmlContent.append("      <ser:WriteObjectOut>\n");
        xmlContent.append("         <!--Optional:-->\n");
        xmlContent.append("         <arg0>" + arg0 + "</arg0>\n");
        xmlContent.append("         <!--Optional:-->\n");
        xmlContent.append("         <arg1>" + arg1 + "</arg1>\n");
        xmlContent.append("         <!--Optional:-->\n");
        xmlContent.append("         <arg2>" + arg2 + "</arg2>\n");
        xmlContent.append("         <!--Optional:-->\n");
        xmlContent.append("         <arg3>" + arg3 + "</arg3>\n");
        xmlContent.append("      </ser:WriteObjectOut>\n");
        xmlContent.append("   </soapenv:Body>\n");
        xmlContent.append("</soapenv:Envelope>");
        return xmlContent.toString();
    }

    private BaseThirdApiResponse getBaseResponse(TPResponse tpResponse) {
        return BaseThirdApiResponse.builder()
                .businessStatus(Head.SUCCESS == tpResponse.getHead().getCode() ?
                        BusinessStatus.SUCCESS : BusinessStatus.FAIL)
                .errorMsg(tpResponse.getHead().getMessage())
                .build();
    }

    private BaseThirdApiResponse getErrorResponse(String errorCode, String errorMsg) {
        return BaseThirdApiResponse.builder()
                .businessStatus(BusinessStatus.FAIL)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    @Override
    public BaseThirdApiResponse submitEquipmentStatus(BaseEquipmentStatusRequest request) {
        return null;
    }
}
