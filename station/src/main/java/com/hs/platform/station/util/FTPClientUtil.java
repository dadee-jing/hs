package com.hs.platform.station.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.SocketException;
import java.util.List;

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

    //FTPClient 的关闭
    public static void ftpClose(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 去 服务器的FTP路径下上读取文件
     *
     * @param ftpUserName
     * @param ftpPassword
     * @param ftpPath
     * @param ftpHost
     * @param ftpPort
     * @param fileName
     * @return
     */
    public static String readConfigFileForFTP(String ftpUserName, String ftpPassword, String ftpPath, String ftpHost,
                                              int ftpPort, String fileName) {
        StringBuffer resultBuffer = new StringBuffer();
        InputStream in = null;
        FTPClient ftpClient = null;
        LOGGER.info("开始读取绝对路径" + ftpPath + "文件!");
        try {
            ftpClient = getFTPClient(ftpHost, ftpPassword, ftpUserName, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            in = ftpClient.retrieveFileStream(fileName);
        } catch (FileNotFoundException e) {
            LOGGER.error("没有找到" + ftpPath + "文件");
            LOGGER.error(e.getMessage(), e);
            return "下载配置文件失败，请联系管理员.";
        } catch (SocketException e) {
            LOGGER.error("连接FTP失败.");
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("文件读取错误。");
            LOGGER.error(e.getMessage(), e);
            return "配置文件读取失败，请联系管理员.";
        }
        if (in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String data = null;
            try {
                while ((data = br.readLine()) != null) {
                    resultBuffer.append(data + "\n");
                }
            } catch (IOException e) {
                LOGGER.error("文件读取错误。");
                LOGGER.error(e.getMessage(), e);
                return "配置文件读取失败，请联系管理员.";
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } else {
            LOGGER.error("in为空，不能读取。");
            return "配置文件读取失败，请联系管理员.";
        }
        return resultBuffer.toString();
    }

    /**
     * FTPClient 下载文件
     *
     * @param remotePath          : ftp上的地址:   /export/home/test.txt
     * @param localPath：本地存放文件的地址 如：D:\EDI_TEST_FILE\00ec9bcfbcc441c699301fac111feb03\Sybase\fact\test.txt
     */
    public static void getFileByFtp(String remotePath, String localPath, FTPClient client) {
        File localFile = new File(localPath);
        OutputStream ous = null;
        try {
            ous = new FileOutputStream(localFile);
            client.retrieveFile(remotePath, ous);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != ous) {
                try {
                    ous.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 从FTP获取文件，并打水印
     *
     * @param remotePath
     * @param targetPath
     * @param client
     * @param contents
     */
    public static void getFtpFileThenAddText(String remotePath, String targetPath, FTPClient client, List<String> contents) {
        try (FileOutputStream outImgStream = new FileOutputStream(targetPath);
             InputStream inputStream = client.retrieveFileStream(remotePath)) {
            Image srcImg = ImageIO.read(inputStream);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            Color color = new Color(255, 255, 255, 128);
            g.setColor(color);
            for (int i = 0; i < contents.size(); i++) {
                g.drawString(contents.get(i), 10, 10 * (1 + i));
            }
            g.dispose();
            ImageIO.write(bufImg, "jpg", outImgStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ftpToFtp(String sourcePath, String targetPath, FTPClient sourceClient, FTPClient targetClient) {
        try {
            sourceClient.changeWorkingDirectory("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // inputStream自动关闭
        try (InputStream inputStream = sourceClient.retrieveFileStream(sourcePath)) {
            if (null != inputStream) {
                //System.out.println(targetPath.substring(0, targetPath.lastIndexOf('/')));
                createDir(targetPath.substring(0, targetPath.lastIndexOf('/') + 1), targetClient);
                targetClient.changeWorkingDirectory("/");
                targetClient.storeFile(targetPath, inputStream);
                // 同个connect，执行多个任务，每条任务需要complete command
                LOGGER.info("ok:" + sourcePath);
            } else {
                LOGGER.error("empty:" + sourcePath);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
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
