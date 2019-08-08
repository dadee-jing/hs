package com.ruoyi.web.jsonrpc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RpcBaseRequest {

    private String id;
    private String jsonrpc;
    private String method;
}
