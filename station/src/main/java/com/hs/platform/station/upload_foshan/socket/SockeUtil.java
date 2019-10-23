package com.hs.platform.station.upload_foshan.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockeUtil {
    Socket socket = null;

    public SockeUtil(String ip, int port) throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
    }

    public byte[] sentByte(byte[] values) throws UnsupportedEncodingException, IOException {
        OutputStream output = null;
        InputStream input = null;
        output = socket.getOutputStream();
        //写数据发送报文
        output.write(values);
        //获得服务端返回的数据
        input = socket.getInputStream();

        byte[] bytes = new byte[values.length];
        try {
            Thread.sleep(1000 * 3);
            input.read(bytes, 0, values.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;

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
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
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


    public static void main(String[] argv) throws UnsupportedEncodingException {

        String ip = argv[0];
        int port = Integer.parseInt(argv[1]);
        String sendva = "7e50020000000000000000000000000000007e";
        byte[] sendByte = hexStringToByte(sendva);
        String testv = bytesToHexString(sendByte);
        System.out.println(testv);
        System.out.println(ip + ":" + port);

        SockeUtil socketClient = null;
        ;
        try {
            socketClient = new SockeUtil(ip, port);
        } catch (UnknownHostException e) {
//            log.error("socket链接异常,链接信息："+ip+端口);
            e.printStackTrace();
        } catch (IOException e) {
//            log.error("socket IO异常");
            e.printStackTrace();
        }
        byte[] ss = null;
        try {
            ss = socketClient.sentByte(sendByte);
            bytesToHexString(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}