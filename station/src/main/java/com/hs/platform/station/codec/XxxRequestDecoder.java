package com.hs.platform.station.codec;


import com.hs.platform.station.entity.XxxRequest;
import com.hs.platform.station.util.Byte2IntUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class XxxRequestDecoder extends CumulativeProtocolDecoder {

    private static final String DECODER_STATE_KEY = XxxRequestDecoder.class.getName() + ".STATE";

    public static final int MAX_CONTENT_SIZE = 8192;

    private static Logger LOGGER = LoggerFactory.getLogger(XxxRequestDecoder.class);

    private static class DecoderState {
        private int packageAmount = 0;//总包数
        private int packageIndex = 0;//包序号
        private int action = 0;//命令号
        private byte[] sequenceArray;//唯一序列号
        private byte[] jsonTemp;
        private StringBuilder jsonContent = new StringBuilder();
    }

    /**
     * 根据新流向文档解码
     *
     * @param session
     * @param in
     * @param out
     * @return
     * @throws Exception
     */
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        DecoderState decoderState = (DecoderState) session.getAttribute(DECODER_STATE_KEY);
        if (decoderState == null) {
            decoderState = new DecoderState();
            session.setAttribute(DECODER_STATE_KEY, decoderState);
        }
        // 若包长不足4字节，不作处理，扔回队列继续拼凑
        if (in.remaining() > 4) {
            in.mark();
            int length = in.getInt();
            in.reset();
            if (length > in.remaining()) {
                // 剩余包体长度小于新流向报文标注的预期长度，不作处理，扔回队列继续拼凑
                LOGGER.warn("newlx package incomplete");
                return false;
            }
            // 根据文档按顺序读入
            byte[] bytes = new byte[length];
            in.get(bytes);
            //将字节数组解包成DecoderState中的对象
            byte[] packageAmountArray = new byte[4];
            System.arraycopy(bytes, 4, packageAmountArray, 0, 4);
            decoderState.packageAmount = Byte2IntUtil.byteArrayToInt(packageAmountArray);
            //System.out.println("package amount:"+decoderState.packageAmount);
            byte[] packageIndexArray = new byte[4];
            System.arraycopy(bytes, 8, packageIndexArray, 0, 4);
            decoderState.packageIndex = Byte2IntUtil.byteArrayToInt(packageIndexArray);
            //System.out.println("package index:"+decoderState.packageIndex);
            byte[] actionArray = new byte[4];
            System.arraycopy(bytes, 12, actionArray, 0, 4);
            decoderState.action = Byte2IntUtil.byteArrayToInt(actionArray);
            byte[] sequenceArray = new byte[12];
            System.arraycopy(bytes, 16, sequenceArray, 0, 12);
            decoderState.sequenceArray = sequenceArray;
            //读入json串
            int remainLen = length - 28;
            byte[] jsonArray = new byte[remainLen];
            System.arraycopy(bytes, 28, jsonArray, 0, remainLen);
            if (decoderState.packageAmount == 1) {
                // 单包信息
                String jsonContent = new String(jsonArray, StandardCharsets.UTF_8);
                decoderState.jsonContent.append(jsonContent);
                writeAndReset(session, out, decoderState);
            } else {
                // 拆包信息，多个包续着拼
                decoderState.jsonTemp = Arrays.copyOf(decoderState.jsonTemp, decoderState.jsonTemp.length + jsonArray.length);//数组扩容
                System.arraycopy(jsonArray, 0, decoderState.jsonTemp, decoderState.jsonTemp.length, jsonArray.length);
                session.setAttribute(DECODER_STATE_KEY, decoderState);
                if (decoderState.packageAmount == decoderState.packageIndex) {
                    String jsonContent = new String(jsonArray, StandardCharsets.UTF_8);
                    decoderState.jsonContent.append(jsonContent);
                    writeAndReset(session, out, decoderState);
                }
            }
            return in.remaining() > 0;
        }
        LOGGER.warn("newlx package incomplete");
        return false;
    }

    /**
     * 将解码好的一条完整数据提交到 DataIntoDBHandler 处理
     * @param session
     * @param out
     * @param decoderState
     */
    private void writeAndReset(IoSession session, ProtocolDecoderOutput out, DecoderState decoderState) {
        XxxRequest request = new XxxRequest(decoderState.action, decoderState.sequenceArray, decoderState.jsonContent.toString());
        out.write(request);
        decoderState = new DecoderState();
        session.setAttribute(DECODER_STATE_KEY, decoderState);
    }
}
