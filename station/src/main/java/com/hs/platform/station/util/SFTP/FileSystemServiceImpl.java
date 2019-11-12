package com.hs.platform.station.util.SFTP;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Arrays;

@Component("fileSystemService")
public class FileSystemServiceImpl {

    @Autowired
    private SftpProperties config;

    private static ChannelSftp staticSFTP;

    private static Logger log = LoggerFactory.getLogger(FileSystemServiceImpl.class);

    // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    /**
     * 创建SFTP连接
     * @return
     * @throws Exception
     */
    private ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();
        //log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use password[" + config.getPassword() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(config.getSessionConnectTimeout());

        //log.info("Session connected to {}.", config.getHost());

        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());

        //log.info("Channel created to {}.", config.getHost());

        return (ChannelSftp) channel;
    }

    /**
     * 加密秘钥方式登陆
     * @return
     */
    private ChannelSftp connectByKey() throws Exception {
        JSch jsch = new JSch();

        // 设置密钥和密码 ,支持密钥的方式登陆
        if (StringUtils.isNotBlank(config.getPrivateKey())) {
            if (StringUtils.isNotBlank(config.getPassphrase())) {
                // 设置带口令的密钥
                jsch.addIdentity(config.getPrivateKey(), config.getPassphrase());
            } else {
                // 设置不带口令的密钥
                jsch.addIdentity(config.getPrivateKey());
            }
        }
        //log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use private key[" + config.getPrivateKey()+ "] with passphrase[" + config.getPassphrase() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        // 设置登陆超时时间
        session.connect(config.getSessionConnectTimeout());
        //log.info("Session connected to " + config.getHost() + ".");

        // 创建sftp通信通道
        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());
        //log.info("Channel created to " + config.getHost() + ".");
        return (ChannelSftp) channel;
    }

    /**
     * 上传文件
     * @param targetPath
     * @param inputStream
     * @return
     * @throws Exception
     */
    public synchronized boolean uploadFile(String targetPath, InputStream inputStream){
        //ChannelSftp sftp = this.createSftp();
        getSftp();
        try {
            staticSFTP.cd(config.getRoot());
            //log.info("Change path to {}", config.getRoot());

            int index = targetPath.lastIndexOf("/");
            String fileDir = targetPath.substring(0, index);
            String fileName = targetPath.substring(index + 1);
            boolean dirs = this.createDirs(fileDir, staticSFTP);
            if (!dirs) {
                log.error("Remote path error. path:{}", targetPath);
                return false;
                //throw new Exception("Upload File failure");
            }
            staticSFTP.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("Upload file failure. TargetPath: {}", targetPath, e);
            //重连
            disconnect(staticSFTP);
            getSftp();
            return false;
            //throw new Exception("Upload File failure");
        }
/*        finally {
            this.disconnect(staticSFTP);
        }*/
    }

    private void getSftp() {
        try {
            if (staticSFTP == null){
                staticSFTP = this.createSftp();
                log.info("staticSFTP create");
            }
            else{
                if(!staticSFTP.getSession().isConnected()){
                    staticSFTP.getSession().connect(config.getSessionConnectTimeout());
                    log.info("staticSFTP session reconnect");
                }
                if(staticSFTP.isClosed()){
                    staticSFTP.connect(config.getChannelConnectedTimeout());
                    log.info("staticSFTP channel reconnect1");
                }
                if(!staticSFTP.isConnected()){
                    staticSFTP.connect(config.getChannelConnectedTimeout());
                    log.info("staticSFTP channel reconnect2");
                }
            }
        }
        catch (Exception e) {
            log.error("get sftp fail");
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param targetPath
     * @param file
     * @return
     * @throws Exception
     */
    public boolean uploadFile(String targetPath, File file) throws Exception {
        return this.uploadFile(targetPath, new FileInputStream(file));
    }

    /**
     * 下载文件
     * @param targetPath
     * @return
     * @throws Exception
     */
    public File downloadFile(String targetPath) throws Exception {
        ChannelSftp sftp = this.createSftp();
        OutputStream outputStream = null;
        try {
            sftp.cd(config.getRoot());
            //log.info("Change path to {}", config.getRoot());

            File file = new File(targetPath.substring(targetPath.lastIndexOf("/") + 1));

            outputStream = new FileOutputStream(file);
            sftp.get(targetPath, outputStream);
            //log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            //log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            this.disconnect(sftp);
        }
    }

    /**
     * 删除文件
     * @param targetPath
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String targetPath) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = this.createSftp();
            sftp.cd(config.getRoot());
            sftp.rm(targetPath);
            return true;
        } catch (Exception e) {
            //log.error("Delete file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Delete File failure");
        } finally {
            this.disconnect(sftp);
        }
    }

    /**
     * 创建一级或者多级目录
     * @param dirPath
     * @param sftp
     * @return
     */
    private boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty()
                && sftp != null) {
            String[] dirs = Arrays.stream(dirPath.split("/"))
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);

            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                    //log.info("Change directory {}", dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                        //log.info("Create directory {}", dir);
                    } catch (SftpException e1) {
                        //log.error("Create directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                    try {
                        sftp.cd(dir);
                        //log.info("Change directory {}", dir);
                    } catch (SftpException e1) {
                        //log.error("Change directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 创建session
     * @param jsch
     * @param host
     * @param username
     * @param port
     * @return
     * @throws Exception
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, config.getSessionStrictHostKeyChecking());
        return session;
    }

    /**
     * 关闭连接
     * @param sftp
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            log.info("disconnect sftp");
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
