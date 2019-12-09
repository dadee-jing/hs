package com.ruoyi.duge.third.foshan.service;

import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.service.IWeightDataMapperService;
import com.ruoyi.duge.third.ThirdApplication;
import com.ruoyi.duge.third.model.BaseVehicleDataRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ThirdApplication.class)
public class FoshanApiServiceTest {

    //@Autowired
    IWeightDataMapperService weightDataMapperService;

    //@Autowired
    FoshanApiService foshanApiService;

    //@Test
    public void submitVehicleData() {
        WeightData weightData = weightDataMapperService.selectDataById(1L);
        foshanApiService.submitVehicleData(BaseVehicleDataRequest.builder()
                .weightData(weightData).build());
    }
}
