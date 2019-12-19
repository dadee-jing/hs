package com.ruoyi.duge.third.trafficPolice.utils;

import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(IOUtil.class);
    static SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
    static SimpleDateFormat today =new SimpleDateFormat("yyyyMMdd" );
    static SimpleDateFormat yearmonth =new SimpleDateFormat("yyyyMM" );
    static SimpleDateFormat day =new SimpleDateFormat("dd" );
    public static boolean IllegalImages(WeightData wd , StationInfo st) throws UnsupportedEncodingException {
        String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
        String sourcePath=basePath+wd.getFtpPriorHead();
        File file=new File(sourcePath);
        if(file.exists() && file.length()>0){
        StringBuffer imageName=new StringBuffer();
        imageName.append("a")
                .append(sdf.format(wd.getWeightingDate()))
                .append("_b")
                .append(parsePlateType(wd.getTruckCorlor(),wd.getTruckNumber()))
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
                .append(calculateSpeedingPercentage(wd.getSpeed().intValue(),st.getSpeedLimit()))
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
                .append("治超")
                .append("_z")
                .append("11")
                .append(".JPG");
        String img=basePath+(wd.getFtpPriorHead()!=null ? wd.getFtpPriorHead():wd.getFtpPlate());
        String img1=basePath+(wd.getFtpTail()!=null ? wd.getFtpTail():wd.getFtpPlate());
        String img3=basePath+(wd.getFtpHead()!=null ? wd.getFtpHead():wd.getFtpPlate());
        String img4=basePath+(wd.getFtpAxle()!=null ? wd.getFtpAxle():wd.getFtpPlate());
        String txt =format0.format(wd.getCreateTime())+"  "+st.getAddress();
        String targetPath="/sharedata/ftp/peccancy/"+yearmonth.format(wd.getLwhDate())+"/"+day.format(wd.getLwhDate())
                    +"/"+wd.getStationId() +"/"+new String(imageName.toString().getBytes(),"UTf-8");
        LOGGER.info("upload the IllegalImages of "+wd.getTruckNumber()+ ",targetPath="+targetPath);
        mergedImages(img,img1,img3,img4,targetPath,txt);
            return true;
        }
        return false;
    }
    public static boolean normalImages(WeightData wd , StationInfo st) throws UnsupportedEncodingException {
        String basePath="/sharedata/ftp/"+wd.getStationId()+"/"+today.format(wd.getCreateTime())+"/";
        String sourcePath=basePath+wd.getFtpPriorHead();
        File file=new File(sourcePath);
        if(file.exists() && file.length()>0){
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
                    .append(st.getKakoCode())
                    .append("_z")
                    .append("11")
                    .append(".JPG");
            String targetPath="/sharedata/ftp/passcar/"+yearmonth.format(wd.getLwhDate())+"/"+day.format(wd.getLwhDate())
                    +"/"+wd.getStationId() +"/"+new String(imageName.toString().getBytes(),"UTf-8");
            IOOperate(sourcePath,targetPath);
            LOGGER.info("upload the normalImages of "+wd.getTruckNumber()+ ",targetPath="+targetPath);
            return true;
        }
        return false;
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
    public static void mergedImages(String img, String img1, String img2,String img3,String outPath,String txt){
        BufferedImage buf;
        BufferedImage buf1;
        BufferedImage buf2;
        BufferedImage buf3;
        BufferedImage buf4;
        OutputStream out=null;
        try {
            buf = resizeImage(1700 ,1000,ImageIO.read(new File(img)));
            buf1 = resizeImage(850 ,500,ImageIO.read(new File(img)));
            buf2 = resizeImage(850 ,500,ImageIO.read(new File(img1)));
            buf3 = resizeImage(850 ,500,ImageIO.read(new File(img2)));
            buf4 = resizeImage(850 ,500,ImageIO.read(new File(img3)));
            Graphics2D g = buf.createGraphics();
            g.drawImage(buf1, 0, 0, buf1.getWidth(), buf1.getHeight(), null);
            g.drawImage(buf2, 850, 0, buf2.getWidth(), buf2.getHeight(), null);
            g.drawImage(buf3, 0, 500, buf2.getWidth(), buf3.getHeight(), null);
            g.drawImage(buf4, 850, 500, buf2.getWidth(), buf4.getHeight(), null);
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("特写图",740 ,40);
            g.drawString("违法前",1600 ,40);
            g.drawString("违法中",740 ,540);
            g.drawString("违法后",1600 ,540);
            g.setFont(new Font("微软雅黑",Font.BOLD,18));
            g.drawString(txt,0 ,990);
            g.dispose();
            ImageIO.setUseCache(false);
            File outFile = new File(outPath);
            if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                outFile.getParentFile().mkdirs();
            }
            ImageIO.write(buf, "jpg", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                    out.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi){
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }
    public static String  calculateSpeedingPercentage(Integer speed, Integer speedLimit){
        System.out.println(speed);
        System.out.println(speedLimit);
        DecimalFormat df=new DecimalFormat("0");
        Integer SpeedingPercentage=Integer.parseInt(df.format(((float)speed/speedLimit -1)*100));
        return SpeedingPercentage>0 ? SpeedingPercentage.toString() : "";
    }
}
