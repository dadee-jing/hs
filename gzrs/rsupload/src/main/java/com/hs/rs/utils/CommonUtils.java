package com.hs.rs.utils;

import org.apache.commons.io.IOUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
/**
 *  http://192.168.187.20:9001/B440112004/2019/12/20/dopler_B440112004_01_20191220000014_7f4c2c.jpg
 */
public class CommonUtils{
    static String host="http://192.168.187.20:9001";
    static SimpleDateFormat year =new SimpleDateFormat("yyyy" );
    static SimpleDateFormat month =new SimpleDateFormat("MM" );
    static SimpleDateFormat day =new SimpleDateFormat("dd" );
    public static String newPath(String dwbh, Date tsTime, String imageName ){
        return  host + "/"+dwbh+ "/"+year.format(tsTime)+ "/"+month.format(tsTime)+ "/"+
                day.format(tsTime)+ "/"+imageName;
    }
    public static String toBase64(String dwbh, Date tsTime, String imageName ) {
        String sourcePath = newPath(dwbh, tsTime, imageName);
        File file = new File(sourcePath);
        if (file.exists() && file.length() > 0) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(file);
                byte[] bytes = IOUtils.toByteArray(is);
                if (bytes.length>512000){
                    return Base64.getEncoder().encodeToString(resize(2048,1208,file));
                }
                return Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sourcePath;
    }
    public static byte[] resize(int x, int y, File picFile) {
        byte[] b = null;
        try {
            BufferedImage bfi = ImageIO.read(picFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if (bfi.getHeight() > x || bfi.getWidth() > y) {
                BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
                bufferedImage.getGraphics().drawImage(
                        bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
                boolean flag = ImageIO.write(bufferedImage, "jpg", out);
                b = out.toByteArray();
                out.close();
                return b;
            }
            boolean flag = ImageIO.write(bfi, "jpg", out);
            b = out.toByteArray();
            out.close();
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }


}
