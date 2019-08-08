package com.hs.platform.station.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author E-mail: test_t@163.COM
 * @createTime 创建时间：2016/12/6 上午9:26:48
 * @类说明 图片文件转化为字节数组字符
 * @copyright
 */

public class DemoImage {

    private static Logger LOGGER = LoggerFactory.getLogger(DemoImage.class);

    /**
     * @param path 图片路径
     * @return
     * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @author
     * @Date
     */
    public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * @param base64 图片Base64数据
     * @param path   图片路径
     * @return
     * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
     * @author
     * @Date 2016-12-06
     */
    public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null) { // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //测试
//		public static void main(String[] str) throws Exception {
//			
//			String path = "C:\\\\Users\\\\blany\\\\Pictures\\\\在线流程表单.png";
//			String base64 = DemoImage.imageToBase64(path);
//		
//			
//			//DemoImage.base64ToImage(base64, "D:\\2.png");
//			
//			System.out.println(base64+"+++++++++++");
//			
//			
//			
//		}


}
