package com.ruoyi.duge.third.foshan.socket;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ruoyi.duge.third.foshan.socket.Byte2IntUtil.*;

public class StructUtil {

    private static final Logger log = LoggerFactory.getLogger(StructUtil.class);

    private static int serailNo = 0;

    public static byte[] getTime2t(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        //System.out.println(hour);
        int minute = localDateTime.getMinute();
        //System.out.println(minute);
        int second = localDateTime.getSecond();
        //System.out.println(second);
        String millStr = String.valueOf(date.getTime());
        // System.out.println(millStr);
        //int millionSecond = Integer.parseInt(millStr.substring(millStr.length() - 3));
        int millionSecond = 0;
        // System.out.println(millionSecond);

        byte[] byear = getShortBytes(year);
        byte bmonth = getByte(month);
        byte bday = getByte(day);
        byte bhour = getByte(hour);
        byte bminute = getByte(minute);
        byte bsecond = getByte(second);
        byte[] bmill = getShortBytes(millionSecond);
        return new byte[]{byear[0], byear[1], bmonth, bday, bhour, bminute, bsecond, bmill[0], bmill[1]};
    }

    static void fillArray(byte[] result, AtomicInteger currPos, byte[] toAdd) {
        System.arraycopy(toAdd, 0, result, currPos.getAndAdd(toAdd.length), toAdd.length);
    }

    public static byte[] getPic(Date picDate, File picFile) {
        byte[] fileBytes = resize(1700, 1000, picFile);
        byte[] picDateBytes = getTime2t(picDate);
        byte[] picLengthBytes = getLongBytes(fileBytes.length);
        byte[] result = new byte[9 + 4 + fileBytes.length];
        AtomicInteger currPos = new AtomicInteger(0);
        // 按顺序填数组
        fillArray(result, currPos, picDateBytes);//抓拍日期信息
        fillArray(result, currPos, picLengthBytes);//图片长度
        fillArray(result, currPos, fileBytes); //图片本身
        return result;
    }

    /**
     * 97
     *
     * @param siteId
     * @param landId
     * @param weightDate
     * @param totalAxis
     * @param axisSpace
     * @param gross
     * @param speed
     * @param length
     * @param width
     * @param height
     * @param f1
     * @param f2
     * @param plateName
     * @param plateType
     * @param plateColor
     * @param busType
     * @param doubleLane
     * @param noLane
     * @param c1
     * @param c2
     * @param picCount
     * @return
     */
    public static byte[] getCarData2Info(int siteId,            // 2byte
                                         int landId,            // 1byte
                                         Date weightDate,       // 9byte
                                         int totalAxis,         // 1byte
                                         float[] axisSpace,     // 24byte
                                         float gross,           // 4byte
                                         float speed,           // 4byte
                                         float length,          // 4byte
                                         float width,           // 4byte
                                         float height,          // 4byte
                                         float f1,              // 4byte
                                         float f2,              // 4byte
                                         String plateName,      // 16byte
                                         int plateType,         // 1byte
                                         int plateColor,        // 1byte
                                         int busType,           // 1byte
                                         int doubleLane,        // 1byte
                                         int noLane,            // 1byte
                                         int c1,                // 1byte
                                         int c2,                // 1byte
                                         int picCount) {        // 1byte
        byte[] result = new byte[97];
        AtomicInteger currPos = new AtomicInteger(0);
        fillArray(result, currPos, getShortBytes(siteId));
        //fillArray(result, currPos, getWordBytes(siteId));
        fillArray(result, currPos, getBytes(landId));
        byte[] weightDateBytes = getTime2t(weightDate);
        fillArray(result, currPos, weightDateBytes);
        fillArray(result, currPos, getBytes(totalAxis));
        fillArray(result, currPos, new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});
        fillArray(result, currPos, float2byte(gross));
        fillArray(result, currPos, float2byte(speed));
        fillArray(result, currPos, float2byte(length));
        fillArray(result, currPos, float2byte(width));
        fillArray(result, currPos, float2byte(height));
        fillArray(result, currPos, float2byte(f1));
        fillArray(result, currPos, float2byte(f2));
        fillArray(result, currPos, getString16Bytes(plateName));
        fillArray(result, currPos, getBytes(plateType));
        fillArray(result, currPos, getBytes(plateColor));
        fillArray(result, currPos, getBytes(busType));
        fillArray(result, currPos, getBytes(doubleLane));
        fillArray(result, currPos, getBytes(noLane));
        fillArray(result, currPos, getBytes(c1));
        fillArray(result, currPos, getBytes(c2));
        fillArray(result, currPos, getBytes(picCount));
        return result;
    }

    public static byte[] combineMsg(int instructionId,
                                    byte[] carData2Info,
                                    byte[] picData1,
                                    byte[] picData2,
                                    byte[] picData3,
                                    byte[] picData4,
                                    byte[] picData5) {
        int msgLength;
        int pic1len = picData1 != null ? picData1.length : 0;
        int pic2len = picData2 != null ? picData2.length : 0;
        int pic3len = picData3 != null ? picData3.length : 0;
        int pic4len = picData4 != null ? picData4.length : 0;
        int pic5len = picData5 != null ? picData5.length : 0;
        if (0x5020 == instructionId) {
            msgLength = carData2Info.length + pic1len + pic2len + pic3len + pic4len + pic5len;
        } else {
            msgLength = 0;
        }
        byte[] result = new byte[17 + msgLength];
        AtomicInteger currPos = new AtomicInteger(0);

        // 指令id
        fillArray(result, currPos, getWordBytes(instructionId));
        // 命令类型
        fillArray(result, currPos, new byte[]{0x00});
        // 流水号
        fillArray(result, currPos, getWordBytes(serailNo++));
        // 进程id
        fillArray(result, currPos, getDwordBytes(0));
        // 保留
        fillArray(result, currPos, getDwordBytes(0));
        // 消息体长度
        fillArray(result, currPos, getDwordBytes(msgLength));
        // 消息体
        if (msgLength > 0) {
            fillArray(result, currPos, carData2Info);
            if (pic1len > 0) {
                fillArray(result, currPos, picData1);
            }
            if (pic2len > 0) {
                fillArray(result, currPos, picData2);
            }
            if (pic3len > 0) {
                fillArray(result, currPos, picData3);
            }
            if (pic4len > 0) {
                fillArray(result, currPos, picData4);
            }
            if (pic5len > 0) {
                fillArray(result, currPos, picData5);
            }
        }
        // 标识位
        // fillArray(result, currPos, new byte[]{0x7e});
        return result;
    }

    public static byte[] combineAll(byte[] msg) {
        byte[] transformed = replaceTag(msg);
        byte[] result = new byte[transformed.length + 2];
        AtomicInteger currPos = new AtomicInteger(0);

        // 标识位
        fillArray(result, currPos, new byte[]{0x7e});
        // 消息头+消息体
        fillArray(result, currPos, transformed);
        // 标识位
        fillArray(result, currPos, new byte[]{0x7e});
        return result;
    }

    static byte[] replaceTag(byte[] source) {
        ArrayList<Byte> tmp = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            if (0x7e == source[i]) {
                tmp.add(getByte(0x7d));
                tmp.add(getByte(0x02));
                //System.out.println("hit 0x7e");
                continue;
            } else if (0x7d == source[i]) {
                tmp.add(getByte(0x7d));
                tmp.add(getByte(0x01));

                //System.out.println("hit 0x7d");
                continue;
            } else {
                tmp.add(source[i]);
            }
        }
        byte[] result = new byte[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            result[i] = tmp.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        byte[] car = getCarData2Info(158, 2, new Date(), 2, null,
                1.13f, 31.4f,
                4.65f, 1.97f, 1.48f,
                0.0f, 0.0f,
                "粤A5RM08", 0, 0, 0,
                0, 0, 0, 0, 2);
        byte[] pic1 = getPic(new Date(), new File("d:\\6.jpg"));
        byte[] pic2 = getPic(new Date(), new File("d:\\6.jpg"));
        //byte[] msg = combineMsg(0x5020, car, pic1, pic2);
        byte[] msg = combineMsg(0x5020, car, pic1, pic2, pic1, pic1, pic1);
        byte[] result = combineAll(msg);

        byte[] lengthBytes = new byte[4];
        System.arraycopy(result, 14, lengthBytes, 0, 4);
        System.out.println("len:" + byteArrayToInt(lengthBytes));
        System.out.println("last:" + bytesToHexString(new byte[]{result[byteArrayToInt(lengthBytes) + 18]}));
        System.out.println("all_size:" + result.length);
        System.out.println(bytesToHexString(result));

        //byte[] msg2 = combineMsg(0x5002, null, null,null);
        //byte[] result2 = combineAll(msg2);
        //System.out.println(bytesToHexString(result2));

        System.out.println(bytesToHexString(float2byte(3.449f)));
        System.out.println(bytesToHexString(getFloatBytes(3.449f)));

        byte[] fileBytes = getFileBytes(new File("d:\\6.jpg"));
        System.out.println(bytesToHexString(fileBytes));
        System.out.println(bytesToHexString(replaceTag(fileBytes)));
    }

    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
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