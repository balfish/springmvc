package com.balfish.hotel.train.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class HttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public HttpClientResponse doGet(URI uri, String charSet) {
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse closeableHttpResponse = null;

        try {
            closeableHttpResponse = httpClient.execute(httpGet);
            return HttpClientResponse.createWithOk(EntityUtils.toString(closeableHttpResponse.getEntity(), charSet));
        } catch (IOException e) {
            LOGGER.error("GET请求执行出错", e);
            return HttpClientResponse.createWithException(e);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    LOGGER.error("关闭响应流出错", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        HttpClientResponse httpClientResponse = new HttpClient().doGet(URI.create("http://www.baidu.com"), DEFAULT_CHARSET);
        String content = httpClientResponse.getContent();
        System.out.println(content);
    }
}
