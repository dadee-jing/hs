package com.hs.platform.station.third.model;

import com.hs.platform.station.third.common.enums.BusinessStatus;
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
