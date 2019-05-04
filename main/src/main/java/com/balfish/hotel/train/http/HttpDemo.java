package com.balfish.hotel.train.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class HttpDemo {

    private static final String URL = "http://www.qunar.com";

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static JsonNode getJsonNode() throws IOException {

        // 1. 组织参数
        List<NameValuePair> nvp = Lists.newArrayList();
//        nvp.add(new BasicNameValuePair("param", param));

        // 2. 生成并发送请求
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json;charset=UTF-8");
        httpPost.setEntity(new UrlEncodedFormEntity(nvp, Charsets.UTF_8));

        Stopwatch stopwatch = Stopwatch.createStarted();

        // 1. 发送HTTP请求
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
//        Preconditions.checkArgument(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK,
//                "HTTP返回非200报文, uri:%s, statusCode:%s", URL, Integer.toString(response.getStatusLine().getStatusCode()));

        String jsonResult = EntityUtils.toString(entity, Charsets.UTF_8);
        Preconditions.checkArgument(StringUtils.isNotBlank(jsonResult), "返回数据为空, uri:%s", URL);

        // 2. 解析JSON结果
        JsonNode rootNode = OBJECT_MAPPER.readTree(jsonResult);

        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("time = %s", elapsed);
        return rootNode;
    }

    public static void main(String[] args) throws IOException {
        JsonNode xx = getJsonNode();
        System.out.println(xx);
    }
}
