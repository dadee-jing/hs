import com.ruoyi.duge.domain.DeviceData;
import com.ruoyi.duge.third.model.BaseEquipmentStatusRequest;
import com.ruoyi.duge.third.model.BaseThirdApiResponse;
import com.ruoyi.duge.third.service.ThirdApiService;

import java.util.Date;
import java.util.UUID;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ThirdApplication.class)
public class GuandongApiServiceTest {

    //@Autowired
    private ThirdApiService thirdApiService;

    //@Test
    public void submitEquipmentStatus() {

        DeviceData deviceData = new DeviceData();
        deviceData.setCheckDate(new Date());
        deviceData.setCheckId(1);
        deviceData.setCheckState(1);
        deviceData.setDeviceType("0");
        deviceData.setExceptionDesc("");
        deviceData.setIndexCode(UUID.randomUUID().toString());
        deviceData.setIpAddress("192.168.1.123");
        BaseEquipmentStatusRequest baseEquipmentStatusRequest = BaseEquipmentStatusRequest.builder()
                .deviceData(deviceData)
                .build();
        BaseThirdApiResponse baseThirdApiResponse = thirdApiService.submitEquipmentStatus(baseEquipmentStatusRequest);
        System.out.println(baseThirdApiResponse.toString());
    }
}
