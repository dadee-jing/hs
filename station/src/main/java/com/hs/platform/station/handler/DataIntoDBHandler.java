package com.hs.platform.station.handler;

import com.hs.platform.station.entity.XxxRequest;
import com.hs.platform.station.entity.XxxResponse;
import com.hs.platform.station.util.Byte2IntUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataIntoDBHandler extends IoHandlerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(DataIntoDBHandler.class);

    public static final String INDEX_KEY = DataIntoDBHandler.class.getName() + ".INDEX";

    @Override
    public void sessionOpened(IoSession session){
        session.setAttribute(INDEX_KEY, 0);
        LOGGER.info("weight sessionOpened");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause){
        LOGGER.info("weight exceptionCaught");
        LOGGER.warn(cause.getMessage(), cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message){
        try {
            //LOGGER.info("weight messageReceived");
            XxxRequest request = (XxxRequest) message;
            int action = request.getAction();
            String requestJson = request.getJsonContent();
            String sequenceTag = Byte2IntUtil.bytesToHexString(request.getSequenceArray());
            // 要根据上传的命令号决定响应的命令号
            ProcessResult result = ProcessRoute.processRequest(action, requestJson, sequenceTag);
            XxxResponse response = new XxxResponse(result.getReplyAction(), request.getSequenceArray(),
                    result.getResultJson());
            session.write(response);
        } catch (Exception e) {
            LOGGER.info("weight messageReceived error");
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

}
