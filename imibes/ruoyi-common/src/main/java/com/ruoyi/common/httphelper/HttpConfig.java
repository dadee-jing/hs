package com.ruoyi.common.httphelper;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpConfig {

    @Autowired
    private HttpProperties httpProperties;

    @Bean
    public HttpRequestRetryHandler httpRequestRetryHandler() {
        // 请求重试
        final int retryTime = httpProperties.getRetryTime();
        return (exception, executionCount, context) -> {
            // Do not retry if over max retry count,如果重试次数超过了retryTime,则不再重试请求
            if (executionCount >= retryTime) {
                return false;
            }
            // 服务端断掉客户端的连接异常
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // time out 超时重试
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            // Unknown host
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // SSL handshake exception
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
        PoolingHttpClientConnectionManager poolHttpConnManager = new PoolingHttpClientConnectionManager(60, TimeUnit.SECONDS);
        // 最大连接数
        poolHttpConnManager.setMaxTotal(httpProperties.getConnMaxTotal());
        // 路由基数
        poolHttpConnManager.setDefaultMaxPerRoute(httpProperties.getConnMaxTotal());
        return poolHttpConnManager;
    }

    @Bean("connectionKeepAliveStrategy")
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
            return httpProperties.getKeepAliveTime() * 1000;
        };
    }

    @Bean("httpHelperRequestConfig")
    public RequestConfig config() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(httpProperties.getConnectRequestTimeout())
                .setConnectTimeout(httpProperties.getConnectTimeout())
                .setSocketTimeout(httpProperties.getSocketTimeout())
                .build();
    }

}
