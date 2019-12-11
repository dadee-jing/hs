package com.ruoyi.web.jsonrpc.langguan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.web.jsonrpc.RpcBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class TokenController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/ws/rpc/TokenService")
    @ResponseBody
    public RpcBaseResponse tokenService(@RequestBody TokenRequest tokenRequest) {
        try {
            log.info("rpc token request:" + objectMapper.writeValueAsString(tokenRequest));
        } catch (Exception ignore) {

        }
        return RpcBaseResponse.builder()
                .id(tokenRequest.getId())
                .jsonrpc(tokenRequest.getJsonrpc())
                .result(UUID.randomUUID().toString().replaceAll("-", ""))
                .build();
    }
}
