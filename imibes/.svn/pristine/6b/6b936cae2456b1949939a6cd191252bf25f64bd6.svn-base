package com.ruoyi.common.httphelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http.helper")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpProperties {

    private int retryTime = 0;

    private int connMaxTotal = 30;

    private int maxPerRoute = 30;

    private int timeToLive = 90;

    private int keepAliveTime = 30;

    private int connectTimeout = 5000;

    private int connectRequestTimeout = 5000;

    private int socketTimeout = 5000;
}
