package com.ruoyi.duge.third.trafficPolice.utils;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(IOUtil.class);
    static SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
    static SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );
    static SimpleDateFormat yearmonth =new SimpleDateFormat("yyyyMM" );
    static SimpleDateFormat day =new SimpleDateFormat("dd" );
    public static String IllegalImages(WeightData wd , StationInfo st) throws UnsupportedEncodingException {
        StringBuffer imageName=new StringBuffer();
        imageName.append("a")
                .append(sdf.format(new Date()))
                .append("_b")
                .append("")
                .append("_c")
                .append(new String((wd.getTruckNumber()!=null ? wd.getTruckNumber() :"无牌").getBytes(),
                        "utf-8"))
                .append("_d")
                .append(st.getAddress())
                .append("_e")
                .append("2001001")
                .append("_f")
                .append(wd.getSpeed().doubleValue())
                .append("_g")
                .append(st.getSpeedLimit())
                .append("_h")
                .append("60")
                .append("_i")
                .append("")
                .append("_j")
                .append("")
                .append("_k")
                .append("TEST")
                .append("_m")
                .append("")
                .append("_s")
                .append("")
                .append("_x")
                .append("TEST")
                .append("_y")
                .append("TEST")
                .append("_z")
                .append("11")
                .append(".JPG");
        Date date=new Date();
        String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
        String img=basePath+wd.getFtpPlate();
        String img1=basePath+(wd.getFtpPriorHead()!=null ? wd.getFtpPriorHead():wd.getFtpPlate());
        String img3=basePath+(wd.getFtpTail()!=null ? wd.getFtpTail():wd.getFtpPlate());
        String txt =format0.format(wd.getCreateTime())+"  "+st.getAddress();
        String targetPath="/sharedata/ftp/peccancy/"+yearmonth.format(date)+"/"+day.format(date)
                +"/"+wd.getStationId() +"/"+imageName.toString();
        LOGGER.info("upload the IllegalImages of "+wd.getTruckNumber()+ ",targetPath="+targetPath);
        MergedImages.Merged(img,img1,img,img3,targetPath,txt);
        return imageName.toString();
    }
    public static String normalImages(WeightData wd , StationInfo st) throws UnsupportedEncodingException {
        StringBuffer imageName=new StringBuffer();
        imageName.append("A")
                .append(sdf.format(wd.getCreateTime()))
                .append("_b")
                .append(parsePlateType(wd.getTruckCorlor(),wd.getTruckNumber()))
                .append("_c")
                .append(new String((wd.getTruckNumber()!=null ? wd.getTruckNumber() :"无牌").getBytes(),
                        "utf-8"))
                .append("_d")
                .append(wd.getSpeed())
                .append("_e")
                .append(parseColor(wd.getTruckCorlor()))
                .append("_f")
                .append(wd.getLane())
                .append("_k")
                .append(wd.getStationId())
                .append("_z")
                .append("11")
                .append(".JPG");
        Date date=new Date();
        String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
        String img=basePath+wd.getFtpPlate();
        String img1=basePath+(wd.getFtpPriorHead()!=null ? wd.getFtpPriorHead():wd.getFtpPlate());
        String img3=basePath+(wd.getFtpTail()!=null ? wd.getFtpTail():wd.getFtpPlate());
        String txt =format0.format(wd.getCreateTime())+"  "+st.getAddress();
        String targetPath="/sharedata/ftp/passcar/"+yearmonth.format(date)+"/"+day.format(date)
                +"/"+wd.getStationId() +"/"+imageName.toString();
        LOGGER.info("upload the normalImages of "+wd.getTruckNumber()+ ",targetPath="+targetPath);
        MergedImages.Merged(img,img1,img,img3,targetPath,txt);
        return imageName.toString();
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
    public static void IOOperate(String sourcePath,String targetPath){
        //创建目录
        InputStream in = null;
        OutputStream out=null;
        try {
            in=new FileInputStream(sourcePath);
            File outFile = new File(targetPath);
            if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                // 创建父文件夹
                outFile.getParentFile().mkdirs();
            }
            out=new FileOutputStream(outFile);
            IOUtils.copy(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static String parsePlateType(String colorStr,String truckNumber){
        switch (colorStr) {
            case "黄":
                if(truckNumber.contains("学")){
                    return "16";
                }else {
                    return "01";
                }
            case "蓝":
                return "02";
            case "黑":
                if(truckNumber.contains("使")){
                    return "03";
                }else if(truckNumber.contains("领")){
                    return "04";
                }else if(truckNumber.contains("港")){
                    return  "26";
                }else if(truckNumber.contains("澳")){
                    return "27";
                }
            case "白":
                if(truckNumber.contains("警")){
                    return "23";
                }else {
                    return "20";
                }
            default:
                return "99";
        }
    }
}
