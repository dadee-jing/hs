package com.hs.platform.station.codec;

import com.hs.platform.station.entity.XxxResponse;
import com.hs.platform.station.util.Byte2IntUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.StandardCharsets;

/**
 * 编码器
 */
public class XxxResponseEncoder extends ProtocolEncoderAdapter {

    /**
     * 根据文档写入字节
     * @param session
     * @param message
     * @param out
     * @throws Exception
     */
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        XxxResponse response = (XxxResponse) message;

        int action = response.getAction();

        String applyJson = response.getJsonContent();
        applyJson = new String(applyJson.getBytes(), StandardCharsets.UTF_8);
        int applyLen = applyJson.getBytes(StandardCharsets.UTF_8).length + 28;

        IoBuffer buffer = IoBuffer.allocate(applyLen, false);
        // 总的响应
        byte[] replyArray = new byte[applyLen - 4];
        // 写入4个长度
        // byte[] applyLenArray = Byte2IntUtil.intToByteArray(applyLen);
        // System.arraycopy(applyLenArray, 0, replyArray, 0, 4);
        // 总包数
        byte[] applyPackageSumArray = Byte2IntUtil.intToByteArray(1);
        System.arraycopy(applyPackageSumArray, 0, replyArray, 0, 4);
        // 包序号
        byte[] applyPackageIndexArray = Byte2IntUtil.intToByteArray(1);
        System.arraycopy(applyPackageIndexArray, 0, replyArray, 4, 4);
        // 要根据上传的命令号决定响应的命令号
        byte[] actionArray = Byte2IntUtil.intToByteArray(action);
        System.arraycopy(actionArray, 0, replyArray, 8, 4);
        // 序列号应答
        System.arraycopy(response.getSequenceArray(), 0, replyArray, 12, 12);
        // 写入内容
        System.arraycopy(applyJson.getBytes(StandardCharsets.UTF_8), 0, replyArray, 24, applyJson.getBytes(StandardCharsets.UTF_8).length);
        buffer.putInt(applyLen);
        buffer.put(replyArray);
        buffer.flip();
        out.write(buffer);
    }

}
