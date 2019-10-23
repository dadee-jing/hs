package com.hs.platform.station.upload_foshan.socket;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoshanMessage {

    public static final int HEART_BEAT_MSG = 0x5002;
    public static final int BODY_MSG = 0x5020;
    private int messageType;
    private byte[] carData2Info;
    private byte[] pic1;
    private byte[] pic2;
    private byte[] pic3;
    private byte[] pic4;
    private byte[] pic5;
    private String messageBody;
}
