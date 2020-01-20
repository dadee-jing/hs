package com.hs.platform.station.third.foshan.socket;

public class Test {
    public static byte[] getWordBytes(int a) {
        return new byte[]{(byte) ((a >> 8) & 0xFF), (byte) (a & 0xFF)};
    }

    public static void main(String[] args) {
        System.out.println(getWordBytes(2));
    }
}
