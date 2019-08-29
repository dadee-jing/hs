package com.ruoyi.duge.third.trafficPolice.utils;







import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MergedImages {
    public static void Merged(String img, String img1, String img2,String img3,String outPath,String txt){
        BufferedImage buf;
        BufferedImage buf1;
        BufferedImage buf2;
        BufferedImage buf3;
        BufferedImage buf4;
        OutputStream out=null;
        try {
                buf = resizeImage(1700 ,1000,ImageIO.read(new File(img)));
            buf1 = resizeImage(850 ,500,ImageIO.read(new File(img)));
            buf2 = resizeImage(850 ,500,ImageIO.read(new File(img1)));
            buf3 = resizeImage(850 ,500,ImageIO.read(new File(img2)));
            buf4 = resizeImage(850 ,500,ImageIO.read(new File(img3)));
            Graphics2D g = buf.createGraphics();
            g.drawImage(buf1, 0, 0, buf1.getWidth(), buf1.getHeight(), null);
            g.drawImage(buf2, 850, 0, buf2.getWidth(), buf2.getHeight(), null);
            g.drawImage(buf3, 0, 500, buf2.getWidth(), buf3.getHeight(), null);
            g.drawImage(buf4, 850, 500, buf2.getWidth(), buf4.getHeight(), null);
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("特写图",740 ,40);
            g.drawString("违法前",1600 ,40);
            g.drawString("违法中",740 ,540);
            g.drawString("违法后",1600 ,540);
            g.setFont(new Font("微软雅黑",Font.BOLD,18));
            g.drawString(txt,0 ,990);
            g.dispose();
            ImageIO.setUseCache(false);
            File outFile = new File(outPath);
            if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                // 创建父文件夹
                outFile.getParentFile().mkdirs();
            }
           ImageIO.write(buf, "jpg", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                out.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi){
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }
}
