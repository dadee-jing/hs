package com.hs.platform.station.third.foshan.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hs.platform.station.third.foshan.socket.SockeUtil.bytesToHexString;
import static com.hs.platform.station.third.foshan.socket.StructUtil.combineAll;
import static com.hs.platform.station.third.foshan.socket.StructUtil.combineMsg;

public class FoshanEncoder extends ProtocolEncoderAdapter {

    private static final Logger log = LoggerFactory.getLogger(FoshanEncoder.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = null;
        try {
            FoshanMessage foshanMessage = (FoshanMessage) message;
            byte[] result;
            if (StringUtils.isBlank(foshanMessage.getMessageBody())) {
                byte[] msg = combineMsg(foshanMessage.getMessageType(),
                        foshanMessage.getCarData2Info(), foshanMessage.getPic1(), foshanMessage.getPic2(),
                        foshanMessage.getPic3(), foshanMessage.getPic4(), foshanMessage.getPic5());
                result = combineAll(msg);
            } else {
                result = Byte2IntUtil.hexStringToByte(foshanMessage.getMessageBody());
            }

            //log.info("FoshanEncoder:" + bytesToHexString(result));

            buffer = IoBuffer.allocate(result.length, true);
            buffer.put(result);
            buffer.flip();
            out.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(null != buffer){
                buffer.clear();
            }
        }
    }
}
