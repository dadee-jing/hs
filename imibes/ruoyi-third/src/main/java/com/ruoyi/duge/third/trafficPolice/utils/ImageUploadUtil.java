package com.ruoyi.duge.third.trafficPolice.utils;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.third.trafficPolice.model.Data;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.text.SimpleDateFormat;

public class ImageUploadUtil {
    private final static Object fileLock = new Object();
    private static Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);
    static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
    /**
     *
    A通过时间：年(4位)月(2位)日(2位)时(2位，24小时制)分(2位)秒(2位)，不可为空；
    b号牌种类：字典编码261006，不可为空；
    c号牌号码：如：津A350L3， 未识别的写：无牌，不可为空；
    d行驶速度：90km/h，不含小数；
    e号牌颜色：字典编码261005；
    f车道编号：字典编码260010；
    g车行方向：字典编码261004；
    h抓拍方向：字典编码260003；
    i车身颜色：字典编码261003；
    j车辆通行状态：字典编码260005；
    k卡口编号：卡点编号，不可为空；
    s设备编号：设备编号；
    m车牌坐标：111（左上）,222（左上）,333（右下）,444（右下）
    n驾驶室坐标：111（左上）,222（左上）,333（右下）,444（右下）
    o车辆品牌：汉字；
    y设备厂家简称：厂家简称。
    z图片个数：1张全景图片： z11；不可为空；
    两张图片：z21（全景图片）；z22（特征图片）；
    或  z23（全景图片）；z24（号牌图片）；
    三张图片：z31（全景图片）；z32（特征图片）；z33（号牌图片）；
     */
    public static String normalImages(WeightData wd ) throws IOException {
       StringBuffer imageName=new StringBuffer();
       imageName.append("A")
               .append(sdf.format(wd.getCreateTime()))
               .append("_b")
               .append("")
               .append("_c")
               .append(wd.getTruckNumber()!=null ? wd.getTruckNumber() :"无牌")
               .append("_d")
               .append(wd.getSpeed())
               .append("_e")
               .append(parseColor(wd.getTruckCorlor()))
               .append("_f")
               .append(wd.getLane())
               .append("_g")
               .append(wd.getDirection())
               .append("_h")
               .append("")
               .append("_i")
               .append("")
               .append("_j")
               .append("")
               .append("_k")
               .append(wd.getStationName())
               .append("_s")
               .append("")
               .append("_m")
               .append("")
               .append("_n")
               .append("")
               .append("_o")
               .append("")
               .append("_y")
               .append("")
               .append("_z");
        //此处加入上传图片的逻辑

               //upload(wd.getFtpAxle(),imageName.toString());



       return imageName.toString();
   }
    /**
    示例：a20141105205938_b02_c津A350L3_d600011047000_e1345_f90_g60_h15_i01_j2_k441781000000_m1.20,1.50,4.20,5.00,1.65,1.80,1.60,2.00_s370785020003_x01_y易华录_z11.JPG
    其中：
    a违法时间：年(4位)月(2位)日(2位)时(2位，24小时制)分(2位)秒(2位)，不可为空；
    b号牌种类：字典编码261006，不可为空；
    c车牌号：如：津A350L3，不可为空， 未识别的写：无牌；
    d违法地点：12位违法地点代码，依据：GA/T 16.33，不可为空；
    e违法行为：参考当地违法行为代码，4位或5位数字，不可为空；
    f车辆速度：90km/h，不含小数，超速违法行为不可为空；
    g车牌限速：60km/h，不含小数，超速违法行为不可为空；
    h超速比例：50，不含小数，超速违法行为时不可为空；
    i方向编码：字典编码261004；
    j车道编号：字典编码260010；
    k采集机关：设备注册的单位对应的单位编号，不可为空；
    m车辆信息：车辆重量（999.99吨）、车辆限重（999.99吨）、车辆长度（999.99米）、车辆限长（999.99米）、车辆宽度（999.99米）、车辆限宽（999.99米）、车辆高度（999.99米）、车辆限高（999.99米），全部为三位数，精确到两位小数；
    s设备编号：依据设备注册编号；
    x数据来源：01:电子警察02:公路卡口03:测速设备04:闭路电视05:移动摄像06:警务通09:其它电子设备，不可为空；
    y设备厂家简称：厂家简称，不可为空；
    z图片个数：1张合成图片： z11；不可为空；
    两张图片：z21，z22；三张图片：z31，z32，z33；
    建议多张图片顺序：全景、违法前、违法中、违法后；
     */
    public static String IllegalImages(WeightData wd , Data dt) throws IOException {
        StringBuffer imageName=new StringBuffer();
        imageName.append("a")
                .append(sdf.format(wd.getCreateTime()))
                .append("_b")
                .append("")
                .append("_c")
                .append(wd.getTruckNumber()!=null ? wd.getTruckNumber() :"无牌")
                .append("_d")
                .append(dt.getWfxw())
                .append("_e")
                .append(dt.getWfxw())
                .append("_f")
                .append(dt.getClsd())
                .append("_g")
                .append(dt.getClxs())
                .append("_h")
                .append("")
                .append("_i")
                .append(dt.getFxbh())
                .append("_j")
                .append(dt.getCdbh())
                .append("_k")
                .append(dt.getCjjg())
                .append("_m")
                .append("")
                .append("_s")
                .append(dt.getSbbh())
                .append("_x")
                .append(dt.getCjfs())
                .append("_y")
                .append(dt.getCjjg())
                .append("_z");
              //此处加入上传图片的逻辑
              //upload(wd.getFtpAxle(),imageName.toString());
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
    public static int ImagesCount(WeightData wd){
        int i=0;
        if(wd.getFtpPriorHead()!=null){
            i++;
        }
        if(wd.getFtpPlate()!=null){
            i++;
        }
          return i;
    }
    public synchronized static void createDir(String remote, FTPClient ftpClient) throws UnsupportedEncodingException, IOException {
        ftpClient.changeWorkingDirectory("/");
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(
                new String(directory.getBytes("GBK"), "iso-8859-1"))) {
            synchronized (fileLock) {
                // 如果远程目录不存在，则递归创建远程服务器目录
                int start = 0;
                int end = 0;
                if (directory.startsWith("/")) {
                    start = 1;
                } else {
                    start = 0;
                }
                end = directory.indexOf("/", start);
                while (true) {
                    String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                    if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                        if (ftpClient.makeDirectory(subDirectory)) {
                            ftpClient.changeWorkingDirectory(subDirectory);
                        } else {
                            System.out.println("创建目录失败");
                        }
                    }
                    start = end + 1;
                    end = directory.indexOf("/", start);
                    // 检查所有目录是否创建完毕
                    if (end <= start) {
                        break;
                    }
                }
            }
        }
    }
    public static FTPClient getFTPClient(String ftpHost,int ftpPort,String ftpUserName, String ftpPassword) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            // 传文件，使用二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
            } else {
                LOGGER.info(ftpHost + "FTP连接成功。");
            }
        } catch (SocketException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ftpClient;
    }
    public static boolean ftpToFtp(FTPClient sourceClient,FTPClient targetClient,
                                  String sourcePath,String targetPath) throws IOException {
        boolean flag = false;
        InputStream inputStream;
        try {
            inputStream=sourceClient.retrieveFileStream(sourcePath);
            if (null != inputStream) {
                //System.out.println(targetPath.substring(0, targetPath.lastIndexOf('/')));
                createDir(targetPath.substring(0, targetPath.lastIndexOf('/') + 1), targetClient);
                targetClient.changeWorkingDirectory("/");
                flag =targetClient.storeFile(targetPath, inputStream);
                inputStream.close();
                // 同个connect，执行多个任务，每条任务需要complete command
                LOGGER.info("ok:" + sourcePath);
            } else {
                LOGGER.error("empty:" + sourcePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public static void  upload(String sourcePath,String targetPath) throws IOException {
        String shundeHost="";
        int shundePort=0;
        String shundeUserName="";
        String shundePassword="";

        String trafficPoliceHost="";
        int trafficPolicePort=0;
        String trafficPoliceUserName="";
        String trafficPolicePassword="";

        FTPClient sourceClient =getFTPClient(shundeHost,shundePort,shundeUserName,shundePassword);
        FTPClient targetClient =getFTPClient(trafficPoliceHost,trafficPolicePort,trafficPoliceUserName,trafficPolicePassword);
        ftpToFtp(sourceClient,targetClient,sourcePath,targetPath);
    }
}
