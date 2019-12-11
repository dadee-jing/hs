package com.ruoyi.common.httphelper;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component
public class HttpCustomClient {

    @Resource(name = "httpClientManagerFactoryBean")
    private CloseableHttpClient httpClient;

    private static final String ENCODING = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpCustomClient.class);

    /**
     * 发送get请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * 发送get请求；带请求参数
     *
     * @param url    请求地址
     * @param params 请求参数集合
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, null, params);
    }

    /**
     * 发送get请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 创建http对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        // 设置请求头
        packageHeader(headers, httpGet);
        return getResult(httpGet);
    }

    /**
     * 发送post请求；带请求参数
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, String> params) throws Exception {
        return doPost(url, null, params);
    }

    /**
     * 发送post请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, String> headers, Map<String, String> params) throws UnsupportedEncodingException {

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);
        packageHeader(headers, httpPost);

        // 封装请求参数
        packageParam(params, httpPost);

        return getResult(httpPost);
    }

    public String doPost(String url, Map<String, String> headers, String requestBody) throws Exception {
        // 创建http对象
        HttpPost httpPost = new HttpPost(url);
        packageHeader(headers, httpPost);

        setBody(requestBody, httpPost);

        return getResult(httpPost);
    }

    /**
     * 发送put请求；不带请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public String doPut(String url) throws Exception {
        return doPut(url);
    }

    /**
     * 发送put请求；带请求参数
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public String doPut(String url, Map<String, String> params) throws Exception {
        HttpPut httpPut = new HttpPut(url);
        packageParam(params, httpPut);
        return getResult(httpPut);
    }

    /**
     * 发送delete请求；不带请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public String doDelete(String url) throws Exception {
        HttpDelete httpDelete = new HttpDelete(url);
        return getResult(httpDelete);
    }

    /**
     * 发送delete请求；带请求参数
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public String doDelete(String url, Map<String, String> params) throws Exception {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("_method", "delete");
        return doPost(url, params);
    }

    /**
     * Description: 封装请求头
     *
     * @param params
     * @param httpMethod
     */
    public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        // 封装请求头
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Description: 封装请求参数
     *
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    private static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        // 封装请求参数
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                sb.append(nameValuePair.toString()).append(" ");
                nvps.add(nameValuePair);
            }
            logger.info("request param:" + sb.toString());
            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }

    private static void setBody(String requestBody, HttpEntityEnclosingRequestBase httpMethod) {
        logger.info("request body:" + requestBody);
        // 封装请求参数
        if (StringUtils.hasText(requestBody)) {
            StringEntity stringEntity = new StringEntity(requestBody, ENCODING);
            // 设置到请求的http对象中
            httpMethod.setEntity(stringEntity);
        }
    }

    private String getResult(HttpRequestBase httpRequest) throws HttpCustomException {
        logger.info(httpRequest.toString());
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpRequest)) {
            Asserts.notNull(httpResponse, "Empty Response");
            logger.info(httpResponse.toString());
            Asserts.notNull(httpResponse.getEntity(), "Empty Response Entity");
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            logger.info("response body:" + responseBody);
            return responseBody;
        } catch (Exception e) {
            throw HttpCustomException.instance(e.getMessage());
        }
    }
}
