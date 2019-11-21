package com.hs.platform.station.handler;

import com.hs.platform.station.entity.WeightAndLwhEntity;
import com.hs.platform.station.util.WeightAndLWHContainer;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class LWHClientHandler extends IoHandlerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(LWHClientHandler.class);

    public static final String INDEX_KEY = LWHClientHandler.class.getName() + ".INDEX";

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.setAttribute(INDEX_KEY, 0);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        // LOGGER.warn(cause.getMessage(), cause);
        System.out.println(cause.getMessage());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

        LOGGER.info("-客户端与服务端连接[空闲] - " + status.toString());
        if (session != null) {
            session.close(true);
        }
    }

    /**
     * 读取外廓报文，并写入队列
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message){
        try {
            String dataString = (String) message;
            //LOGGER.info("[duge_lwh_message]: " + dataString);
            String[] dataArray = dataString.split(" ");
            Map<String, String> dataMap = new HashMap<>();
            for (int i = 0; i < dataArray.length; i++) {
                if (i % 2 == 0 && i + 1 < dataArray.length) {
                    dataMap.put(dataArray[i], dataArray[i + 1]);
                }
            }

            String plate = dataMap.get("plate");
            WeightAndLwhEntity entity = new WeightAndLwhEntity();
            entity.setPlate(plate);
            String speed = dataMap.get("speed");
            String pathTag = dataMap.get("pathTag");
            if (StringUtils.isNotBlank(speed)) {
                //LOGGER.info("speed hit:" + plate + " " + speed);
                entity.setProcessStatus(2);
                entity.setSpeed(new BigDecimal(speed));
                entity.setSpeedTag(true);
            }
            //配置文件配置是否从外廓传图片
            else if ((StringUtils.isNotBlank(pathTag))) {
                if("1".equals(WeightAndLWHContainer.lwhUploadFileTag)){
                    if("1".equals(pathTag)){
                        //LOGGER.info("leftSidePath hit:" + plate);
                        String leftSidePath = dataMap.get("leftSidePath");
                        entity.setProcessStatus(3);
                        entity.setLeftSidePath(leftSidePath);
                        entity.setPathTag(entity.getPathTag() + 1);
                    }
                    else if("2".equals(pathTag)){
                        //LOGGER.info("rightSidePath hit:" + plate);
                        String rightSidePath = dataMap.get("rightSidePath");
                        entity.setProcessStatus(4);
                        entity.setRightSidePath(rightSidePath);
                        entity.setPathTag(entity.getPathTag() + 1);
                    }
                }
            }

            else {
                String timeString = dataMap.get("time");
                String processTime = timeString.substring(0, 4) + "-" + timeString.substring(4, 6) + "-"
                        + timeString.substring(6, 8) + " " + timeString.substring(8, 10) + ":" + timeString.substring(10, 12)
                        + ":" + timeString.substring(12, 14);
                Timestamp lwhDate = Timestamp.valueOf(processTime);
                String width = dataMap.get("width");
                String height = dataMap.get("height");
                String length = dataMap.get("length");
                String laneMid = dataMap.get("laneMid");
                String laneMin = dataMap.get("laneMin");
                String laneMax = dataMap.get("laneMax");
                String passTime = dataMap.get("passTime");
                entity.setProcessStatus(1);
                entity.setLwhDate(lwhDate);
                entity.setWidth(width);
                entity.setHeight(height);
                entity.setLength(length);
                entity.setLaneMid(laneMid);
                entity.setLaneMin(laneMin);
                entity.setLaneMax(laneMax);
                entity.setPassTime(passTime);
                entity.setSizeTag(true);
            }
            // 解析报文->WeightAndLwhEntity
            WeightAndLWHContainer.processData(entity);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
