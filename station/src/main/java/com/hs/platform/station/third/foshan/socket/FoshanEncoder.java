package com.hs.platform.station.third.foshan.socket;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hs.platform.station.third.foshan.service.FoshanApiService.waitingResponseMap;
import static com.hs.platform.station.third.foshan.socket.Byte2IntUtil.bytesToHexString;
import static com.hs.platform.station.third.foshan.socket.Byte2IntUtil.getWordBytes;
import static com.hs.platform.station.third.foshan.socket.StructUtil.*;

public class FoshanEncoder extends ProtocolEncoderAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(FoshanEncoder.class);

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = null;
        try {
            FoshanMessage foshanMessage = (FoshanMessage) message;
            byte[] result;
            if (StringUtils.isBlank(foshanMessage.getMessageBody())) {
                int serialNo = getSerailNo();
                byte[] msg = combineMsg(foshanMessage.getMessageType(), serialNo,
                        foshanMessage.getCarData2Info(), foshanMessage.getPic1(), foshanMessage.getPic2(),
                        foshanMessage.getPic3(), foshanMessage.getPic4(), foshanMessage.getPic5());
                result = combineAll(msg);
                String serialStr = bytesToHexString(getWordBytes(serialNo));
                if (null != serialStr && null != foshanMessage.getPlate()) {
                    waitingResponseMap.put(serialStr, foshanMessage.getPlate());
                    LOGGER.info("input " + serialStr + " " + foshanMessage.getPlate());
                }
            } else {
                result = Byte2IntUtil.hexStringToByte(foshanMessage.getMessageBody());
            }
            LOGGER.info("msgSize:" + result.length + ",plate:" + foshanMessage.getPlate());
            //log.info("FoshanEncoder:" + bytesToHexString(result));

            buffer = IoBuffer.allocate(result.length, true);
            buffer.put(result);
            buffer.flip();
            out.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != buffer) {
                buffer.clear();
            }
        }
    }
}
