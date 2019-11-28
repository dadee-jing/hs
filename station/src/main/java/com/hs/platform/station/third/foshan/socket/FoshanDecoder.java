package com.hs.platform.station.third.foshan.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.hs.platform.station.third.foshan.socket.Byte2IntUtil.byteArrayToInt;
import static com.hs.platform.station.third.foshan.socket.Byte2IntUtil.bytesToHexString;

public class FoshanDecoder extends ProtocolDecoderAdapter {

    private static final Logger log = LoggerFactory.getLogger(FoshanDecoder.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * TODO 处理黏包问题
     *
     * @param session
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if (null == in) {
            return;
        }
//        int length = in.getInt();
//        in.position(0);
//        System.out.println("body length:" + length);


        byte[] tag = new byte[1];
        in.get(tag);
        Map<String, Object> receiveMessageMap = new HashMap<>();
        FoshanRspMessage foshanRspMessage = FoshanRspMessage.builder().build();
        //System.out.println("start_tag:" + bytesToHexString(tag));
        receiveMessageMap.put("start_tag", bytesToHexString(tag));
        foshanRspMessage.setStartTag(bytesToHexString(tag));

        byte[] instrId = new byte[2];
        in.get(instrId);
        //System.out.println("instrId:" + bytesToHexString(instrId));
        receiveMessageMap.put("instrId", bytesToHexString(instrId));
        foshanRspMessage.setInstrId(bytesToHexString(instrId));

        byte[] instrType = new byte[1];
        in.get(instrType);
        //System.out.println("instrType:" + bytesToHexString(instrType));
        receiveMessageMap.put("instrType", bytesToHexString(instrType));
        foshanRspMessage.setInstrType(bytesToHexString(instrType));

        byte[] serialNo = new byte[2];
        in.get(serialNo);
        //System.out.println("serialNo:" + bytesToHexString(serialNo));
        receiveMessageMap.put("serialNo", bytesToHexString(serialNo));
        foshanRspMessage.setSerialNo(bytesToHexString(serialNo));

        byte[] progressId = new byte[4];
        in.get(progressId);
        //System.out.println("progressId:" + bytesToHexString(progressId));
        receiveMessageMap.put("progressId", bytesToHexString(progressId));
        foshanRspMessage.setProgressId(bytesToHexString(progressId));

        byte[] tbd = new byte[4];
        in.get(tbd);
        //System.out.println("tbd:" + bytesToHexString(tbd));
        receiveMessageMap.put("tbd", bytesToHexString(tbd));
        foshanRspMessage.setTbd(bytesToHexString(tbd));

        byte[] messageLength = new byte[4];
        in.get(messageLength);
        int ml = byteArrayToInt(messageLength);
        //System.out.println("messageLength:" + ml);
        receiveMessageMap.put("messageLength", ml);
        foshanRspMessage.setLength(ml);

        if (ml > 0) {
            byte[] bodyByte = new byte[1];
            ArrayList<Byte> tmp = new ArrayList<>();
            do {
                in.get(bodyByte);
                tmp.add(bodyByte[0]);
            } while (bodyByte[0] != 0x7e);
            byte[] body = new byte[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) {
                body[i] = tmp.get(i);
            }
            receiveMessageMap.put("body", bytesToHexString(body));
            receiveMessageMap.put("body_str", new String(body));
            foshanRspMessage.setBody(new String(body));

            // 0A
            //in.get(tag);
            //System.out.println("addition:" + bytesToHexString(tag));
        } else {
            in.get(tag);
            System.out.println("end_tag:" + bytesToHexString(tag));
            receiveMessageMap.put("end_tag", bytesToHexString(tag));
            foshanRspMessage.setEndTag(bytesToHexString(tag));
        }

        String outStr = objectMapper.writeValueAsString(receiveMessageMap);
        /*
        if (!"7002".equalsIgnoreCase((String) receiveMessageMap.get("instrId"))) {
            outStr = objectMapper.writeValueAsString(receiveMessageMap);
            //log.info("receive:" + outStr);
        } else {
            outStr = "receive_online";
        }*/
        //out.write(outStr);
        out.write(foshanRspMessage);
        /*
        String response = null;

        if (bytes[1] == 0x50 && bytes[2] == 0x20) {
            if (bytes[18] == 0x00) {
                response = "success";
            } else {
                response = "error";
            }
        }
        log.info("foshan response:" + response);
        */
    }
}
