package com.ruoyi.duge.third.guangdong.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GDResponse {

    private String data;
    private Integer rscode;
    private String rsmsg;
    private ERROR_CODE errorCode;

    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    public enum ERROR_CODE {
        AUTHORIZE_FAIL,     // 接口授权认证失败
        RESUTLT_FAIL,       //  数据调用失败
        ERROR_FORMAT,       //  数据格式转换异常
        ERROR_DATAAUDIT,    //  数据审计异常
        ERROR_DATACALCULATION,  //  数据计算异常
        ERROR_HBASE         // 数据存储失败
    }
}
