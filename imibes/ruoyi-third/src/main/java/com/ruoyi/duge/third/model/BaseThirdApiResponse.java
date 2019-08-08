package com.ruoyi.duge.third.model;

import com.ruoyi.common.enums.BusinessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
