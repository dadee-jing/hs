package com.ruoyi.common.httphelper;

public class HttpCustomException extends RuntimeException {

    private int errorCode;

    private HttpCustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static HttpCustomException instance(String errorMessage) {
        return new HttpCustomException(504, errorMessage);
    }
}
