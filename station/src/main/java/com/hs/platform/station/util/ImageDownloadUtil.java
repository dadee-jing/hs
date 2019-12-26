package com.hs.platform.station.util;

import com.hs.platform.station.persistence.local.dao.ConfigDataRepository;
import com.hs.platform.station.persistence.local.entity.WeightData;
import com.hs.platform.station.third.foshan.service.FoshanApiService;
import com.hs.platform.station.third.foshan.socket.FoshanMessage;
import com.hs.platform.station.util.SFTP.FileSystemServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import static com.hs.platform.station.Constants.*;
import static com.hs.platform.station.third.foshan.socket.StructUtil.getCarData2Info;
import static com.hs.platform.station.third.foshan.socket.StructUtil.getPicByStream;
import static com.hs.platform.station.util.FTPClientUtil.resizePicToOutputStream;

@Component
public class ImageDownloadUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageDownloadUtil.class);
    private static int picCount;
    public static FTPClient newlxFtpClient = null;
    //public static FTPSClient shundeFtpClient = null;
    @Autowired
    private ConfigDataRepository configDataRepository;
    @Autowired
    private FileSystemServiceImpl fileSystemService;

    public static int station_id = Integer.valueOf(DbUtil.getConfigValue("station_id"));
    public static String address = DbUtil.getAddress(station_id);
    public static String newlx_ftp_server_host = DbUtil.getConfigValue("newlx_ftp_server_host");
    public static String lwh_server_host = DbUtil.getConfigValue("lwh_server_host");
    public static String led_ip = DbUtil.getConfigValue("led_ip");

    @PostConstruct
    void init() {
        newlxFtpClient = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        LOGGER.info("station_id:" + station_id + ",newlx_ftp_server_host:" + newlx_ftp_server_host +
                ",lwh_server_host:" + lwh_server_host + ",led_ip:" + led_ip);
       // shundeFtpClient = FTPClientUtil.getSFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
    }


    public static FTPClient resetFTPClient(FTPClient ftpClient, boolean newlx) {
        LOGGER.info("to reset " + newlx);
        FTPClientUtil.ftpClose(ftpClient);
        FTPClient newOne = FTPClientUtil.getFTPClient(newlx_ftp_server_host, newlx_ftp_passwd, newlx_ftp_user, newlx_ftp_server_port);
        LOGGER.info("over reset " + newlx);
        return newOne;
    }

    public static FTPSClient resetFTPClient(FTPSClient ftpClient, boolean newlx) {
        LOGGER.info("to reset " + newlx);
        FTPClientUtil.ftpClose(ftpClient);
        FTPSClient newOne  = FTPClientUtil.getSFTPClient(shunde_ftp_server_host, shunde_ftp_passwd, shunde_ftp_user, shunde_ftp_server_port);
        LOGGER.info("over reset " + newlx);
        return newOne;
    }


    public HashMap<String, String> submitDownloadTask(final WeightData entity) {
        //图片源文件丢失或者源文件0k的，更新文件链接为空
        HashMap<String,String> fileInfoState = new HashMap<>();
        try {
            final String stationId = station_id + "";
            // 按照固定目录存放 ftppath/stationID/date/   ftp/1/20190717/
            String targetParentPath = stationId + "/" + DateFormatUtils.format(entity.getWeightingDate(), "yyyyMMdd");
            if (null != newlxFtpClient && null != fileSystemService) {
                String FtpPriorHead = entity.getFtpPriorHead();//前 scene
                String FtpTail = entity.getFtpTail();//后 back
                String FtpHead = entity.getFtpHead();//左侧 left
                String FtpAxle = entity.getFtpAxle();//右侧 right
                String FtpPlate = entity.getFtpPlate();//车牌 plate
                String FtpFullView = entity.getFtpFullView();//视频 video
                String lwhScenePath = entity.getLwhScenePath();//外廓前抓

                FoshanMessage foshanMessage = new FoshanMessage();
                //上传图片，组装对象。上传前重置图片为0
                picCount = 0;
                //Arrays.asList生成的list直接remove会报错
                List<String> pathListPre = Arrays.asList(FtpPriorHead, FtpTail, FtpHead, FtpAxle,FtpPlate, FtpFullView);
                List<String> pathList = new ArrayList<>(pathListPre);
                if("1".equals(WeightAndLWHContainer.lwhUploadFileTag) && entity.getPathTag() != 0){
                    //上传本地侧拍
                    uploadLocalFile(entity,targetParentPath,foshanMessage,pathList,fileInfoState);
                }
                //上传新流向文件
                uploadNewlxFile(foshanMessage,pathList,targetParentPath,entity,fileInfoState);
                //上传外廓前抓拍
                uploadLwhScenePic(foshanMessage,entity.getPlate(),entity.getLwhDate(),lwhScenePath,targetParentPath,fileInfoState);
                //组装市局发送对象
                combineFoshanMessage(foshanMessage,entity);
                FoshanApiService.addEntity(foshanMessage);
            }
        } catch (Exception e) {
            LOGGER.error("FILE TRANSFORM ERROR ",e);
        }
        return fileInfoState;
    }

    private void uploadLwhScenePic(FoshanMessage foshanMessage, String plate, Date lwhDate,String lwhScenePath,
                                   String targetParentPath,HashMap<String, String> fileInfo) {
        //lwhScenePath D:\CameraPic\20191220\16\粤A7BB15_2_scene_7974.jpg
        //PicPlate/20191226_1021/102156_2_scene_1540.jpg
        try{
            long startTime = System.currentTimeMillis();
            String min = DateFormatUtils.format(lwhDate, "yyyyMMdd_HHmm");
            String senond = DateFormatUtils.format(lwhDate, "HHmmss");
            String targetPath;
            if(lwhScenePath.contains("_")){
                targetPath ="PicPlate/" + min + "/" + senond + lwhScenePath.substring(lwhScenePath.indexOf("_"));
            }
            else{
                targetPath ="PicPlate/" + min + "/" + senond + "_" + lwhScenePath;
            }
            int picSize = 0;
            InputStream input =  new FileInputStream(lwhScenePath);
            ByteArrayOutputStream reSizeOutputStream = FTPClientUtil.resizePicToOutputStream(input);
            if (null != reSizeOutputStream) {
                input = FTPClientUtil.parse(reSizeOutputStream);
                InputStream yhlInput = FTPClientUtil.parse(reSizeOutputStream);
                byte[] pic = getPicByStream(lwhDate, input);
                foshanMessage.setPic2(pic);
                picCount++;
                picSize = reSizeOutputStream.size();
                fileSystemService.uploadFile(targetParentPath + "/" + targetPath, yhlInput);
                fileInfo.put("lwhScenePath",targetPath);
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("uploadLwhScenePic end,picSize:" + picSize + "," + plate + ",cost:" + (endTime - startTime));
        }catch (Exception e){
            LOGGER.info("uploadLwhScenePic error," + plate, e);
        }

    }

    private void uploadLocalFile(WeightData entity, String targetParentPath, FoshanMessage foshanMessage,
                                                    List<String> pathList,HashMap<String,String> fileInfoState) {
        String FtpHead = entity.getFtpHead();//左侧 left
        String FtpAxle = entity.getFtpAxle();//右侧 right
        String leftSidePath = entity.getLeftSidePath();//本地左侧拍
        String rightSidePath = entity.getRightSidePath();//本地右侧拍
        //优先使用本地侧拍， 没有则新流向补充。
        //isBlank 判断某字符串是否为空或长度为0或由空白符(whitespace)构成
        if(StringUtils.isNotBlank(leftSidePath)){
             HashMap<String,Object> fileInfo = FTPClientUtil.localToFtp(leftSidePath,
                    targetParentPath + '/' + FtpHead,fileSystemService,entity.getWeightingDate());
             if(fileInfo.get("fileState") != null && "0".equals(fileInfo.get("fileState").toString())){
                 fileInfoState.put("left","0");
             }
             else{
                 byte[] picLeftSide = (byte[]) fileInfo.get("pic");
/*                 if(picLeftSide != null && picLeftSide.length != 0){
                     foshanMessage.setPic3(picLeftSide);
                     picCount++;
                 }*/
             }
             pathList.remove(FtpHead);
        }
        if(StringUtils.isNotBlank(rightSidePath)){
            HashMap<String,Object> fileInfo = FTPClientUtil.localToFtp(rightSidePath,
                    targetParentPath + '/' + FtpAxle,fileSystemService,entity.getWeightingDate());
            if(fileInfo.get("fileState") != null && "0".equals(fileInfo.get("fileState").toString())){
                fileInfoState.put("right","0");
            }
            else{
                byte[] picRightSide = (byte[]) fileInfo.get("pic");
/*                if(picRightSide != null && picRightSide.length != 0){
                    foshanMessage.setPic4(picRightSide);
                    picCount++;
                }*/
            }
            pathList.remove(FtpAxle);
        }
    }

    private void uploadNewlxFile(FoshanMessage foshanMessage,List<String> pathList, String targetParentPath,
                                                    WeightData weightData,HashMap<String,String> fileInfoState) {
        for(int i = 0; i < pathList.size(); i++){
            String filePath = pathList.get(i);
            if (StringUtils.isNotBlank(filePath)) {

                HashMap<String,Object> fileInfo = FTPClientUtil.ftpToFtp(filePath,
                        targetParentPath + '/' + filePath, newlxFtpClient, fileSystemService,
                        weightData.getWeightingDate(),weightData.getLaneMid(),weightData.getSpeed(),weightData.getPlate());
                String fileState = fileInfo.get("fileState").toString();
                if("0".equals(fileState)){
                    fileInfoState.put(filePath.substring(filePath.length() - 14,filePath.length() - 8),"0");
                }
                else{
                    byte[] pic = (byte[]) fileInfo.get("pic");
                    if(pic != null && pic.length > 0){
                        if(filePath.contains("scene")){
                            foshanMessage.setPic1(pic);
                            picCount++;
                        }
/*                        else if(filePath.contains("back")){
                            foshanMessage.setPic2(pic);
                            picCount++;
                        }
                        else if(filePath.contains("left")){
                            foshanMessage.setPic3(pic);
                            picCount++;
                        }
                        else if(filePath.contains("right")){
                            foshanMessage.setPic4(pic);
                            picCount++;
                        }*/
/*                        else if(filePath.contains("plate")){
                            foshanMessage.setPic5(pic);
                            picCount++;
                        }*/
                    }
                }

            }
        }
    }

    private void combineFoshanMessage(FoshanMessage foshanMessage,WeightData weightData) {
        foshanMessage.setMessageType(FoshanMessage.BODY_MSG);
        foshanMessage.setPlate(weightData.getPlate());
        foshanMessage.setCarData2Info
                (getCarData2Info(Integer.parseInt(configDataRepository.findFirstByKey("site_id").getValue()),
                        weightData.getLane(),
                        weightData.getWeightingDate(),
                        weightData.getAxleCount(),
                        null,
                        weightData.getWeight().floatValue(),
                        weightData.getSpeed().floatValue(),
                        Float.parseFloat(weightData.getLength()) / 1000.0f,
                        Float.parseFloat(weightData.getWidth()) / 1000.0f,
                        Float.parseFloat(weightData.getHeight()) / 1000.0f,
                        0.00f, 0.00f,
                        weightData.getPlate(),
                        0,
                        mappingPlateColor(weightData.getTruckCorlor()),
                        5,
                        0, 0, 0, 0, picCount));
    }


    /**
     * 检查两个ftp连接
     */
    public static FTPClient checkNewlxFtpConnect(){
        if (!FTPReply.isPositiveCompletion(newlxFtpClient.getReplyCode())) {
            LOGGER.info("check nlxftp reconnect");
            newlxFtpClient = ImageDownloadUtil.resetFTPClient(newlxFtpClient,true);
        }
        return newlxFtpClient;
    }

    public int mappingPlateColor(String colorChs) {
        switch (colorChs) {
            case "蓝":
                return 0;
            case "黄":
                return 1;
            case "黑":
                return 3;
            case "白":
                return 2;
            case "绿":
                return 4;
            default:
                return 9;
        }
    }

}
