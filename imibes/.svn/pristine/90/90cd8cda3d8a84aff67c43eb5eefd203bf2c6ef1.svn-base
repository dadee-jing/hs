package com.ruoyi.web.jsonrpc.langguan;

import com.ruoyi.web.jsonrpc.RpcBaseRequest;
import lombok.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlacksmokeRequest extends RpcBaseRequest {

    private List<Object> params;

    public String getCheckbayonetid() {
        return (String) params.get(1);
    }

    public String getToken() {
        return (String) params.get(0);
    }

    public List<CheckData> getCheckDataList() {
        return (List<CheckData>) ((List) params.get(2)).stream().map(map -> {
            CheckData tmp = new CheckData();
            try {
                BeanUtils.populate(tmp, (HashMap) map);
            } catch (IllegalAccessException | InvocationTargetException ignore) {
            }
            return tmp;
        }).collect(toList());
    }
}
