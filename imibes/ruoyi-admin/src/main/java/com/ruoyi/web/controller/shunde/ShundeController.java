package com.ruoyi.web.controller.shunde;

import com.ruoyi.duge.domain.DeviceHealthState;
import com.ruoyi.duge.third.yihualu.service.YiHuaLuApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shunde")
public class ShundeController {
    private static final Logger log = LoggerFactory.getLogger(ShundeController.class);

    @Autowired
    private YiHuaLuApiService yiHuaLuApiService;

    @PostMapping("/uploadEquipmentStatus")
    public void getEquipmentData(@RequestBody List<DeviceHealthState> params)throws Exception{
        if(null != params){
            yiHuaLuApiService.InsertData(params);
            params.forEach(param -> log.debug(param.toString()));
        }
    }
}
