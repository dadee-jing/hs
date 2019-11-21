package com.hs.platform.station.util;

import com.hs.platform.station.entity.FTPReUploadInfo;
import com.hs.platform.station.schedule.ReUploadFailedData;
import com.hs.platform.station.util.SFTP.FileSystemServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Date;

import static com.hs.platform.station.Constants.*;
import static com.hs.platform.station.Constants.newlx_ftp_server_port;
import static com.hs.platform.station.third.foshan.socket.StructUtil.getPicByStream;
import static com.hs.platform.station.util.ImageDownloadUtil.checkNewlxFtpConnect;
import static com.hs.platform.station.util.ImageDownloadUtil.newlxFtpClient;

public class FTPClientUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(FTPClientUtil.class);

    private final static Object fileLock = new Object();

    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpPassword, String ftpUserName, int ftpPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.setConnectTimeout(10 * 1000);
            ftpClient.setDefaultTimeout(10 * 1000);
            ftpClient.setDataTimeout(10 * 1000);
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            ftpClient.enterLocalPassiveMode();
            // 传文件，使用二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                LOGGER.error("FTP FAIL ERROR RESPONSE " + ftpHost);
            } else {
                LOGGER.info(ftpHost + " FTP连接成功。");
            }
        } catch (Exception e) {
            LOGGER.error("FTP FAIL " + ftpHost + e.getMessage());
        }
        return ftpClient;
    }

    public static FTPSClient getSFTPClient(String ftpHost, String ftpPassword, String ftpUserName, int ftpPort) {
        FTPSClient ftpClient = null;
        try {
            ftpClient = new FTPSClient();
            ftpClient.setConnectTimeout(10 * 1000);
            ftpClient.setDefaultTimeout(10 * 1000);
            ftpClient.setDataTimeout(10 * 1000);
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            ftpClient.enterLocalPassiveMode();
            // 传文件，使用二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                LOGGER.error("FTP FAIL ERROR RESPONSE " + ftpHost);
            } else {
                LOGGER.info(ftpHost + " FTP连接成功。");
            }
        } catch (Exception e) {
            LOGGER.error("FTP FAIL " + ftpHost + e.getMessage());
        }
        return ftpClient;
    }

    //FTPClient 的关闭
    public static void ftpClose(FTPClient ftpClient) {
        if (null != ftpClient) {
            try {
                ftpClient.logout();
            } catch (IOException io) {
                LOGGER.error("ftp client logout failed...{}" + ftpClient.getPassiveHost());
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.disconnect();
                    }
                } catch (IOException io) {
                    LOGGER.error("close ftp client failed...{}" + ftpClient.getPassiveHost());
                }
            }
        }
    }

    public static ByteArrayInputStream parse(final OutputStream out) {
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public static byte[] ftpToFtp(String sourcePath, String targetPath, FTPClient sourceClient,
                                  FileSystemServiceImpl fileSystemService, Date weightingDate) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            //上传前先检查新流向ftp
            sourceClient = checkNewlxFtpConnect();
            sourceClient.changeWorkingDirectory("/");
            Boolean sourceState = sourceClient.retrieveFile(sourcePath, outputStream);
            LOGGER.info("sourceState:" + sourceState);
            //sourceState false代表源文件丢失，执行上传后是0k的文件
            if(!sourceState){
                LOGGER.info("sourcePath:" + sourcePath);
                return null;
            }
        } catch (Exception e) {
            //重新连接新流向ftp，将失败的加入列表
            LOGGER.error("pull file fail " + ReUploadFailedData.ftpReUploadQueue.size() + " " + sourcePath + e.getMessage());
            newlxFtpClient = ImageDownloadUtil.resetFTPClient(newlxFtpClient,true);
            FTPReUploadInfo ftpReUploadInfo = new FTPReUploadInfo(sourcePath,targetPath);
            ReUploadFailedData.ftpReUploadQueue.add(ftpReUploadInfo);
            if(ReUploadFailedData.ftpReUploadQueue.size() > 100){
                ReUploadFailedData.ftpReUploadQueue.poll();
            }
            return null;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (InputStream inputStream = parse(outputStream)) {
            long startTime2 = System.currentTimeMillis();
            Boolean targetState = fileSystemService.uploadFile(targetPath, inputStream);
            long endTime2 = System.currentTimeMillis();
            //targetState false 没有上传到服务器
            LOGGER.info("ok:targetState:" + targetState + ",cost:" +(endTime2 - startTime2)+"," + targetPath);
            //将流转化成byte返回
            try{
                if(!sourcePath.contains("mp4")){
                    InputStream shiJuInputStream = parse(outputStream);
                    byte[] pic = getPicByStream(weightingDate, shiJuInputStream);
                    return pic;
                }
            }
            catch (Exception e){
                LOGGER.error("getPicByStream error");
            }
        } catch (Exception e) {
            //重新连接顺德ftp，将失败的加入列表
            LOGGER.error("push file fail " + ReUploadFailedData.ftpReUploadQueue.size() + " " + targetPath + e.getMessage());
            FTPReUploadInfo ftpReUploadInfo = new FTPReUploadInfo(sourcePath,targetPath);
            ReUploadFailedData.ftpReUploadQueue.add(ftpReUploadInfo);
            if(ReUploadFailedData.ftpReUploadQueue.size() > 100) {
                ReUploadFailedData.ftpReUploadQueue.poll();
            }
            return null;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据path递归创建目录
     *
     * @param remote
     * @param ftpClient
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
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
                            LOGGER.info("创建目录失败--" + remote);
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


    /**
     * 定时任务执行的ftp操作
     */
    public static int reDoftpToFtp(String sourcePath, String targetPath, FTPClient sourceClient, FileSystemServiceImpl fileSystemService) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            sourceClient.changeWorkingDirectory("/");
            sourceClient.retrieveFile(sourcePath, outputStream);
            Boolean sourceState = sourceClient.retrieveFile(sourcePath, outputStream);
            LOGGER.info("re sourceState:" + sourceState);
            if(!sourceState){
                LOGGER.info("re sourcePath:" + sourcePath);
                return 0;
            }
        } catch (Exception e) {
            LOGGER.error("re pull file fail " + ReUploadFailedData.ftpReUploadQueue.size() + " " + sourcePath + e.getMessage());
            return 1;
        }
        try (InputStream inputStream = parse(outputStream)) {
            Boolean targetState = fileSystemService.uploadFile(targetPath, inputStream);
            LOGGER.info("re ok:targetState:" + targetState + " " + sourcePath);
        } catch (Exception e) {
            LOGGER.error("re push file fail " + ReUploadFailedData.ftpReUploadQueue.size() + " " + targetPath + e.getMessage());
            return 2;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        String ftpUserName = "admin";
        String ftpPassword = "admin";
        String remotePath = "/";
        String ftpHost = "19.201.248.55";
        int ftpPort = 21;
        String localPath = "D:\\temp_ftp\\";
        String fileName = "README.txt";
        //String content = readConfigFileForFTP(ftpUserName,ftpPassword,ftpPath,ftpHost,ftpPort,fileName);
        //System.out.println(content);
        //FTPClient client = getFTPClient(ftpHost, ftpPassword, ftpUserName, ftpPort);
        String targetPath = "/1/20190712/PicPlate/20190712_151746606/151940681_back_6_1.jpg";
        System.out.println(targetPath.substring(0, targetPath.lastIndexOf('/') + 1));
        System.out.println(targetPath.substring(targetPath.lastIndexOf('/') + 1));
        //createDir(targetPath.substring(0, targetPath.lastIndexOf('/')), client);
    }

    public static byte[] localToFtp(String sidePath, FileSystemServiceImpl fileSystemService,Date weightingDate) {
        //转化为本地路径,本地路径获取文件，原路径执行上传
        //sidePath:PicPlate/20191118_1510/150214831_3_leftSide.jpg
        //D:/Camera/Picture/20191118/20191118_1510/150214831_3_leftSide.jpg
        String sourcePath = "D:/Camera/Picture/" + sidePath.substring(9,17) + "/" + sidePath.substring(9,22) +
                sidePath.substring(22,sidePath.length());
        InputStream input;
        InputStream inputStream;
        InputStream shiJuInputStream;
        try {
            input =  new FileInputStream(sourcePath);
            ByteArrayOutputStream baos = cloneInputStream(input);
            inputStream = new ByteArrayInputStream(baos.toByteArray());
            shiJuInputStream = new ByteArrayInputStream(baos.toByteArray());
        } catch (FileNotFoundException e) {
            //源文件丢失
            e.printStackTrace();
            LOGGER.error("getSourcePathFile fail" + sourcePath);
            return null;
        }
        try {
            final String stationId = station_id + "";
            String targetPath = stationId + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd") + "/" + sidePath;
            Boolean targetState = fileSystemService.uploadFile(targetPath, inputStream);
            LOGGER.info("localToFtp ok:targetState:" + targetState + " " + sourcePath);
        } catch (Exception e) {
            ReUploadFailedData.localReUploadQueue.add( new FTPReUploadInfo(sidePath,""));
            if(ReUploadFailedData.localReUploadQueue.size() > 100){
                ReUploadFailedData.localReUploadQueue.poll();
            }
            e.printStackTrace();
            LOGGER.error("uploadFile fail " + sourcePath,e);
        }
        try{
            byte[] pic =getPicByStream(weightingDate, shiJuInputStream);
            return pic;
        }catch (Exception e){
            LOGGER.error("getPicByStream fail " + sourcePath);
        }
        return null;
    }


    public static int reLocalToFtp(String sidePath, FileSystemServiceImpl fileSystemService) {
        String sourcePath = "D:/Camera/Picture/" + sidePath.substring(9,17) + "/" + sidePath.substring(9,22) +
                sidePath.substring(22,sidePath.length());
        InputStream inputStream ;
        try {
             inputStream = new FileInputStream(sourcePath);
        }catch (Exception e) {
            LOGGER.error("re getSourcePathFile fail" + sourcePath);
            return 0;
        }
        try {
            final String stationId = station_id + "";
            String targetPath = stationId + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd") + "/" + sidePath;
            Boolean targetState = fileSystemService.uploadFile(targetPath, inputStream);
            LOGGER.info("re localToFtp ok:targetState:" + targetState + " " + sourcePath);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("re localToFtp fail " + sourcePath,e);
            return 1;
        }
        return 0;
    }


//    public static void main(String[] args) throws IOException {
//        File file = new File("d://1.jpg");
//        InputStream inputStream = new FileInputStream(file);
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        Image sourceImage = ImageIO.read(inputStream);
//        if (sourceImage.getWidth(null) > 1920) {
//            sourceImage = sourceImage.getScaledInstance(1920,
//                    1920 *  sourceImage.getHeight(null) / sourceImage.getWidth(null),
//                    Image.SCALE_SMOOTH);
//        }
//        if ( sourceImage.getHeight(null) > 1080) {
//            sourceImage = sourceImage.getScaledInstance(
//                    1080 *  sourceImage.getWidth(null) / sourceImage.getHeight(null),
//                    1080, Image.SCALE_SMOOTH);
//        }
//        ImageIO.write((BufferedImage) sourceImage, "jpg", os);
//        FileOutputStream fileOutputStream = new FileOutputStream(os);
//        File of = new File(os);
//        System.out.println("aaaaaa");
//    }
}
