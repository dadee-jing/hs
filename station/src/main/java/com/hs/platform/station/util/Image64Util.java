package com.hs.platform.station.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Image64Util {

    private static Logger LOGGER = LoggerFactory.getLogger(Image64Util.class);

    public static void main(String[] args) {
        String strImg = GetImageStr();
        System.out.println(strImg);
//		GenerateImage(strImg);
    }

    public static String GetImageStr() {
        String imgFile = "C:\\Users\\blany\\Pictures\\在线流程表单.png";
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    // base64字符串转化成图片
    public static boolean generateImage(String imgStr, String fileName) {//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            String imgFilePath = "D:\\文件接收测试\\1001\\videos\\2018\\11\\20\\1\\" + fileName;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
