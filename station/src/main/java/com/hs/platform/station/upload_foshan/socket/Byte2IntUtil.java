package com.hs.platform.station.upload_foshan.socket;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

public class Byte2IntUtil {

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{(byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)};
    }

    public static byte[] getLongBytes(long a) {
        return new byte[]{(byte) (a & 0xFF), (byte) ((a >> 8) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 24) & 0xFF)};
    }

    public static byte[] getFloatBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return intToByteArray(intBits);
    }

    public static byte[] float2byte(float f) {
        int fbit = Float.floatToIntBits(f);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }
        int len = b.length;
        byte[] dest = new byte[len];
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }

    /**
     * 字节转换为浮点
     *
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public static byte[] getDwordBytes(int a) {
        return intToByteArray(a);
    }

    public static byte[] getWordBytes(int a) {
        return new byte[]{(byte) ((a >> 8) & 0xFF), (byte) (a & 0xFF)};
    }

    public static byte[] getShortBytes(int a) {
        return new byte[]{(byte) (a & 0xFF), (byte) ((a >> 8) & 0xFF)};
    }

    public static byte getByte(int a) {
        return (byte) (a & 0xFF);
    }

    public static byte[] getBytes(int a) {
        return new byte[]{(byte) (a & 0xFF)};
    }

    public static byte[] getStringBytes(String str, String charset) {
        return str.getBytes(Charset.forName(charset));
    }

    public static byte[] getString16Bytes(String str) {
        byte[] result = new byte[16];
        byte[] tmp = getStringBytes(str, "GBK");
        System.arraycopy(tmp, 0, result, 0, tmp.length);
        for (int i = tmp.length; i < 16; i++) {
            result[i] = 0x00;
        }
        return result;
    }

    public static byte[] hexStringToByte(String hex) {
        hex = hex.toUpperCase();
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static byte[] getFileBytes(File file) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {

            fileInputStream = new FileInputStream(file);
            bytesArray = IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
