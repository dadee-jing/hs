package com.ruoyi.web.controller.duge;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.third.foshan.service.FoshanApiService;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import com.ruoyi.duge.third.shunde.service.ShundeApiService;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public")
public class PublicController extends BaseController {

    @Autowired
    private com.ruoyi.duge.service.IWeightDataMapperService dataService;

    @Autowired
    private ShundeApiService shundeApiService;

    @PostMapping("/weightData/add")
    @ResponseBody
    public AjaxResult outerAddSave(@RequestBody com.ruoyi.duge.domain.WeightData data) {
        int result;
        try {
            result = dataService.insertData(data);
        } catch (DuplicateKeyException e) {
            result = 1;
        } catch (Exception e) {
            result = 0;
        }
        return toAjax(result);
    }

    @Autowired
    private FoshanApiService foshanApiService;

    @PostMapping("/submit/foshan")
    @ResponseBody
    public String submitFoshan(@RequestBody com.ruoyi.duge.domain.WeightData data) {
        try {
            foshanApiService.submitVehicleData(data.getId());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/submit/byte")
    @ResponseBody
    public String submitByte(@RequestBody String byteString) {
        try {
            foshanApiService.submitByte(byteString);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/submit/shunde/{id}")
    @ResponseBody
    public String submitShunde(@PathVariable("id") Long id) {
        try {
            WeightData weightData = dataService.selectDataById(id);
            shundeApiService.submitVehicleData(BaseVehicleDataRequest.builder().weightData(weightData).build());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
