package com.hs.platform.station.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

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

    public static void ftpToFtp(String sourcePath, String targetPath, FTPClient sourceClient, FTPClient targetClient) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            sourceClient.changeWorkingDirectory("/");
            sourceClient.retrieveFile(sourcePath, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("pull file fail " + sourcePath + e.getMessage());
            return;
        }
        try (InputStream inputStream = parse(outputStream)) {
            createDir(targetPath.substring(0, targetPath.lastIndexOf('/') + 1), targetClient);
            targetClient.changeWorkingDirectory("/");
            targetClient.storeFile(targetPath, inputStream);
            LOGGER.info("ok:" + sourcePath);
        } catch (Exception e) {
            LOGGER.error("push file fail " + targetPath + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                            LOGGER.info("创建目录失败");
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
