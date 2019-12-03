package com.hs.platform.station.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class WaterMarkUtil {
    public static class Position {
        int x;
        int y;

        void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private String outImgPath;// 输出路径
    private Image srcImg;// 源文件
    private int srcImgWidth;// 获取图片的宽
    private int srcImgHeight;// 获取图片的高
    private BufferedImage bufImg;
    private Position contentP;// 内容的开始位置
    private int border;// 边界
    private int lineHeight;// 行距
    private int lastfontsize;// 上一行的字体大小,默认0，可通过setLastLine改变值
    private int rotarad;

    // 调用方法
    public void addMsg(String msg, Color color, Font font) {
        try {
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(color);
            g.setFont(font);
            g.rotate(Math.toRadians(rotarad));
            fonmatConten2(font, msg, contentP.x, contentP.y + lastfontsize, g);
            lastfontsize = font.getSize();
            g.dispose();
            bufImg.flush();
            srcImg = bufImg;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private int getTheFontWidth(Font font) {
        int width_of_font;
        @SuppressWarnings("restriction")
        FontMetrics tf = sun.font.FontDesignMetrics.getMetrics(font);
        if (font.isBold()) {
            width_of_font = tf.stringWidth("aa");
        } else {
            width_of_font = tf.stringWidth("口");
        }
        return width_of_font;
    }

    // 格式二这个是将数字处理为两字符
    private void fonmatConten2(Font font, String msg, int startx, int starty, Graphics2D g) {
        float linelen = srcImgWidth - border - startx;
        starty += font.getSize();
        // 计算每一行的字数，因为粗体的字体和普通字体的大小有区别，所以采用两种处理方式
        int num;
        int width_of_font = getTheFontWidth(font);
        num = (int) (linelen / width_of_font);
        int currword = 0;
        int lastword = 0;
        int temp = num;
        while (currword < msg.length()) {
            int s = 0;
            lastword = currword;
            for (int i = 0; i < num && currword < msg.length(); i++, currword++) {
                if (msg.charAt(currword) == '\n' && i != 0) {
                    currword++;
                    break;
                }
                if (msg.charAt(currword) <= '~') {
                    s++;
                    if (s == 2) {
                        num++;
                        s = 0;
                    }
                }
            }

            g.drawString(msg.substring(lastword, currword), startx, starty);
            num = temp;
            starty += lineHeight + font.getSize();
        }
    }

    // 输出
    public void outPutFile(String picfomat) {
        try {
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, picfomat, outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 获取输出流
    public OutputStream getOutPutStream(String picfomat) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufImg, picfomat, os);
            return os;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Builder {
        String outImgPath;// 输出路径
        InputStream inputStream;
        int x;
        int y;
        int border;// 边界
        int lineHeight;// 行距
        int rotarad;

        public Builder inputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public Builder outImagPath(String out) {
            outImgPath = out;
            return this;
        }

        public Builder contentPosition(int x, int y) {

            this.x = x;
            this.y = y;
            return this;
        }

        public Builder border(int border) {
            this.border = border;
            return this;
        }

        public Builder lineHeight(int lineHeight) {
            this.lineHeight = lineHeight;
            return this;

        }

        public Builder rotarad(int rotarad) {
            this.rotarad = rotarad;
            return this;
        }

        public WaterMarkUtil create() {
            return new WaterMarkUtil(this);
        }
    }

    // 构造方法
    private WaterMarkUtil(Builder b) {
        this.border = b.border;
        this.lineHeight = b.lineHeight;
        this.contentP = new Position();
        contentP.x = b.x;
        contentP.y = b.y;
        this.lastfontsize = 0;
        this.outImgPath = b.outImgPath;
        this.rotarad=b.rotarad;
        //File srcImgFile = new File(b.srcpath);
        try {
            this.srcImg = ImageIO.read(b.inputStream);
            srcImgWidth = srcImg.getWidth(null);
            srcImgHeight = srcImg.getHeight(null);
            bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String getOutImgPath() {
        return outImgPath;
    }

    public Image getSrcImg() {
        return srcImg;
    }

    public int getSrcImgWidth() {
        return srcImgWidth;
    }

    public int getSrcImgHeight() {
        return srcImgHeight;
    }

    public Position getContentP() {
        return contentP;
    }

    public int getBorder() {
        return border;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public int getLastfontsize() {
        return lastfontsize;
    }

    public void setOutImgPath(String outImgPath) {
        this.outImgPath = outImgPath;
    }

    public void setSrcImg(Image srcImg) {
        this.srcImg = srcImg;
    }

    public void setSrcImgWidth(int srcImgWidth) {
        this.srcImgWidth = srcImgWidth;
    }

    public void setSrcImgHeight(int srcImgHeight) {
        this.srcImgHeight = srcImgHeight;
    }

    public void setBufImg(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    public void setContentP(Position contentP) {
        this.contentP = contentP;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public void setLastfontzie(int size) {
        lastfontsize = size;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public void setLastfontsize(int lastfontsize) {
        this.lastfontsize = lastfontsize;
    }

    public int getRotarad() {
        return rotarad;
    }

    public void setRotarad(int rotarad) {
        this.rotarad = rotarad;
    }

}
