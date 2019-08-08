package com.hs.platform.station.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageOverLayText {

    /**
     * 图片贴上文字标注
     *
     * @param srcImgPath
     * @param tarImgPath
     * @param x
     * @param y
     * @param contents
     * @param color
     * @param font
     * @throws IOException
     */
    public static void addWaterMark(String srcImgPath, String tarImgPath,
                                    int x, int y,
                                    List<String> contents, Color color, Font font) throws IOException {
        File srcImgFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);
        for (int i = 0; i < contents.size(); i++) {
            g.drawString(contents.get(i), x, y * (1 + i));
        }
        g.dispose();
        try (FileOutputStream outImgStream = new FileOutputStream(tarImgPath)) {
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
        }
    }

    public static void main(String[] args) {
        String srcImgPath = "E:/image.jpg"; //ԴͼƬ��ַ
        String tarImgPath = "E:/image_1.jpg"; //���洢�ĵ�ַ
        int x = 10;
        int y = 20;
        List<String> contents = new ArrayList<>();
        contents.add("����12000����2550���ߣ�4000");
        contents.add("������17��");
        contents.add("�ٶȣ�120km/h");
        Color color = new Color(255, 255, 255, 128);
        Font font = new Font("΢���ź�", Font.PLAIN, 16);
        try {
            addWaterMark(srcImgPath, tarImgPath, x, y, contents, color, font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
