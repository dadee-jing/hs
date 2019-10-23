package com.hs.platform.station.upload_foshan.model;




import com.ruoyi.common.enums.BusinessStatus;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseThirdApiResponse {

    private BusinessStatus businessStatus;
    private String errorCode;
    private String errorMsg;

    @Override
    public String toString() {
        return "BaseThirdApiResponse{" +
                "businessStatus=" + businessStatus +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
