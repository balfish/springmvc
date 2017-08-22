package com.balfish.common.utils.httpClient.test;

import com.balfish.common.utils.httpClient.MyAsyncHttpClient;
import com.google.common.collect.Maps;
import com.ning.http.client.ListenableFuture;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by yhm on 2017/8/17 PM2:27.
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        MyAsyncHttpClient asyncHttpClient = MyAsyncHttpClient.createMyAsyncHttpClient(1000, 5000, 100);
        HashMap<String, String> headers = Maps.newHashMapWithExpectedSize(1);
        headers.put(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        ListenableFuture<String> future3 = asyncHttpClient.httpPost("http://lock.proxy.ep.ia.hoteltest.meituan.com/api/v1/cdb/battery/queryUrl",
                headers,
                null,
                "{\"cabinIdList\": [\"B00000100003\"]}",
                MyAsyncHttpClient.getMyAsyncHandler());

        System.out.println(future3.get());
    }
}
