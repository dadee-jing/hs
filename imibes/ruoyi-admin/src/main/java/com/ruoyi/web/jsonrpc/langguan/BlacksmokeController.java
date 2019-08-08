package com.ruoyi.web.jsonrpc.langguan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.web.jsonrpc.RpcBaseResponse;
import com.ruoyi.yaogan.domain.BlacksmokevehicleInfo;
import com.ruoyi.yaogan.mapper.BlacksmokevehicleInfoMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class BlacksmokeController {

    private static final Logger log = LoggerFactory.getLogger(BlacksmokeController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlacksmokevehicleInfoMapper blacksmokevehicleInfoMapper;

    @RequestMapping("/ws/rpc/DataService")
    @ResponseBody
    public RpcBaseResponse tokenService(@RequestBody BlacksmokeRequest blacksmokeRequest) {

        String resultStr;
        try {
            log.info("rpc blacksmoke request:" + objectMapper.writeValueAsString(blacksmokeRequest));
            blacksmokeRequest.getCheckDataList().forEach(checkData -> {
                Date date = new Date(checkData.getCheckdate());
                BlacksmokevehicleInfo blacksmokevehicleInfo = BlacksmokevehicleInfo.builder()
                        .CDXH("")
                        .CPYS(checkData.getCplatetype())
                        .DWBH("")
                        .HPHM(checkData.getCnumberplate())
                        .HPZL("")
                        .JCSJ(date)
                        .JCXBH("")
                        .JLBH(DateFormatUtils.format(date, "yyyyMMddHHmmssSSS"))
                        .LGMHD(Integer.parseInt(checkData.getRingleman()))
                        .PDJG("1".equals(checkData.getCheckresult()) ? "0" : "1")
                        .RLZL("")
                        .SP1(checkData.getSp1())
                        .TP1(checkData.getTp1())
                        .TP2(checkData.getTp2())
                        .build();
                blacksmokevehicleInfoMapper.insertBlacksmokevehicleInfo(blacksmokevehicleInfo);
            });
            resultStr = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            resultStr = "FAIL-" + e.getMessage();
        }
        return RpcBaseResponse.builder()
                .id(blacksmokeRequest.getId())
                .jsonrpc(blacksmokeRequest.getJsonrpc())
                .result(resultStr)
                .build();
    }
}
