package com.balfish.common.utils.httpClient;

import com.google.common.base.Suppliers;
import com.ning.http.client.*;
import com.ning.http.util.AsyncHttpProviderUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * 定义异步httpClient
 * <p>
 * <pre>
 *     一般来说，你可以这样使用这个类。
 * MyAsyncHttpClient asyncHttpClient = MyAsyncHttpClient.createMyAsyncHttpClient(1000, 5000, 100);
 * HashMap<String, String> headers = Maps.newHashMapWithExpectedSize(1);
 * headers.put(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
 * ListenableFuture<String> future3 = asyncHttpClient.httpPost("http://lock.proxy.ep.ia.hoteltest.meituan.com/api/v1/cdb/battery/queryUrl",
 * headers,
 * null,
 * "{\"cabinIdList\": [\"B00000100003\"]}",
 * MyAsyncHttpClient.getMyAsyncHandler());
 *
 * System.out.println(future3.get());
 * }
 *
 *  hint:AsyncHandler是可以自己实现的
 * </pre>
 */

public final class MyAsyncHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAsyncHttpClient.class);

    /**
     * 配置的处理http请求的线程池大小，2倍cup核心数
     */
    private final static int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 空闲连接回收超时时间，默认60s
     */
    private final static int IDLE_CONN_TIME_OUT = 60000;

    /**
     * 读超时时间，默认2s
     */
    private final static int DEFAULT_READ_TIME_OUT = 2000;

    /**
     * 模拟浏览器访问的参数
     */
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) Chrome/27.0.1453.94 Safari/537.36 ga/1.0.0";

    private final AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();

    private AsyncHttpClient getClient() {
        return Suppliers.memoize(() -> new com.ning.http.client.AsyncHttpClient(builder.build())).get();
    }

    /**
     * 闯将异步的httpClient 参数设置要合理!!
     *
     * @param connTimeout 连接超时时间 ，单位毫秒
     * @param readTimeout 读取数据的超时时间，单位毫秒
     * @param maxTotal    连接池大小
     * @return httpClient对象
     */
    public static MyAsyncHttpClient createMyAsyncHttpClient(int connTimeout, int readTimeout, int maxTotal) {
        return new MyAsyncHttpClient(connTimeout, readTimeout, maxTotal);
    }

    /**
     * httpGet请求
     *
     * @param url        请求url
     * @param headers    请求头
     * @param parameters 请求参数
     * @param handler    回调处理器
     * @param <T>        泛型
     * @return ListenableFuture对象
     */
    public final <T> ListenableFuture<T> httpGet(final String url,
                                                 final Map<String, String> headers,
                                                 final Map<String, String> parameters,
                                                 final MyAsyncHandler<T> handler) {
        return httpExecute(url, HttpMethod.GET, headers, parameters, null, handler);
    }

    /**
     * @param url        请求url
     * @param headers    请求头
     * @param parameters 请求参数
     * @param postBody   xml/json请求体
     * @param handler    泛型
     * @param <T>        泛型
     * @return ListenableFuture对象
     */
    public final <T> ListenableFuture<T> httpPost(final String url,
                                                  final Map<String, String> headers,
                                                  final Map<String, String> parameters,
                                                  final String postBody,
                                                  final MyAsyncHandler<T> handler) {
        return httpExecute(url, HttpMethod.POST, headers, parameters, postBody, handler);
    }

    /**
     * 其中的一个实现，当然你也可以自己实现MyBaseAsyncHandler完成定制的逻辑
     *
     * @return MyAsyncHandler
     */
    public static MyAsyncHandler<String> getMyAsyncHandler() {
        return new MyAsyncHandler<String>() {
            private long startTime = System.currentTimeMillis();

            @Override
            public String onCompleted(Response response) throws Exception {
                int statusCode = response.getStatusCode();
                String resp = getResponseBody(response);
                if (statusCode != HttpStatus.SC_OK && StringUtils.isEmpty(resp)) {
                    throw new RuntimeException(String.format("请求状态码异常, 状态码为:%s", statusCode));
                }
                if (this.getPrintLog()) {
                    long costTime = System.currentTimeMillis() - startTime;
                    LOGGER.info("http接口返回成功, 耗时:[{}], url:[{}], method:[{}], headers:[{}], parameters:[{}], body:[{}], resp:[{}]"
                            , costTime, this.getUrl(), this.getMethod(), this.getHeaders(), this.getParameters(), this.getBody(), resp);
                }
                return resp;
            }

            @Override
            public void onThrowable(Throwable t) {
                long costTime = System.currentTimeMillis() - startTime;
                // 记录异常日志
                LOGGER.error("http接口返回成功, 耗时:[{}], url:[{}], method:[{}], headers:[{}], parameters:[{}], body:[{}], resp:[{}]"
                        , costTime, this.getUrl(), this.getMethod(), this.getHeaders(), this.getParameters(), this.getBody(), t);
                throw new RuntimeException(t);
            }
        };
    }

    private MyAsyncHttpClient(int connTimeout, int readTimeout, int maxTotal) {
        // 是否使用连接池
        builder.setAllowPoolingConnections(true);
        builder.setAllowPoolingSslConnections(true);
        // 空闲连接超时回收时间
        builder.setPooledConnectionIdleTimeout(IDLE_CONN_TIME_OUT);
        // 连接超时时间
        builder.setConnectTimeout(connTimeout);
        // 请求超时时间
        builder.setRequestTimeout(readTimeout);
        builder.setReadTimeout(DEFAULT_READ_TIME_OUT);
        // 最大连接数
        builder.setMaxConnections(maxTotal);
        // 每个host最大连接数
        builder.setMaxConnectionsPerHost(maxTotal);
        builder.setAcceptAnyCertificate(true);
        // 模拟浏览器
        builder.setUserAgent(USER_AGENT);
        // 设置处理的线程池，覆盖默认的newCachedThreadPool
        builder.setExecutorService(Executors.newFixedThreadPool(THREAD_POOL_SIZE, Executors.defaultThreadFactory()));
        // 设置信任所有证书
        builder.setAcceptAnyCertificate(true);
        // 默认开启gzip压缩
        builder.setCompressionEnforced(true);
    }

    @PreDestroy
    public void destroy() {
        if (getClient() != null) {
            getClient().close();
        }
    }

    private <T> ListenableFuture<T> httpExecute(String url, HttpMethod httpMethod, Map<String, String> headers, Map<String, String> parameters, String postBody, MyAsyncHandler<T> handler) {
        // 构建builder
        AsyncHttpClient.BoundRequestBuilder requestBuilder = httpMethod == HttpMethod.GET ? getClient().prepareGet(url) : getClient().preparePost(url);

        // 设置http请求头参数
        addHeaders(requestBuilder, headers);

        // 设置http请求参数
        addParameters(httpMethod, requestBuilder, parameters);

        // 设置请求体参数（直接post json/xml的参数）
        if (StringUtils.isNotBlank(postBody)) {
            requestBuilder.setBody(postBody);
        }

        // 设置handler参数
        handler.addUrl(url).addHeaders(headers).addParameters(parameters).addBody(postBody).addMethod(httpMethod).addPrintLog(true);

        // 发送请求
        return getClient().executeRequest(requestBuilder.build(), handler);
    }

    /**
     * 设置多个请求参数
     *
     * @param builder    构造器
     * @param parameters 请求参数
     */
    private void addParameters(HttpMethod method, AsyncHttpClient.BoundRequestBuilder builder, Map<String, String> parameters) {
        if (MapUtils.isEmpty(parameters)) {
            return;
        }
        for (String parameterKey : parameters.keySet()) {
            if (method == HttpMethod.POST) {
                builder.addFormParam(parameterKey, parameters.get(parameterKey));
            } else {
                builder.addQueryParam(parameterKey, parameters.get(parameterKey));
            }
        }
    }

    /**
     * 设置多个header参数
     *
     * @param builder 构造器
     * @param headers 请求头
     */
    private void addHeaders(AsyncHttpClient.BoundRequestBuilder builder, Map<String, String> headers) {
        if (MapUtils.isEmpty(headers)) {
            return;
        }
        for (String headerKey : headers.keySet()) {
            builder.addHeader(headerKey, headers.get(headerKey));
        }
    }

    /**
     * 获取非http 200时的responseBody
     *
     * @param response 响应
     * @return responseBody
     */
    private static String getResponseBody(Response response) {
        try {
            return response.getResponseBody(getCharset(response));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取response的字符编码
     *
     * @param response 响应
     * @return 编码
     */
    private static String getCharset(Response response) {
        String contentType = response.getContentType();
        if (null == contentType) {
            return CharEncoding.UTF_8;
        }
        String charset = AsyncHttpProviderUtils.parseCharset(contentType);
        if (null == AsyncHttpProviderUtils.parseCharset(contentType)) {
            return CharEncoding.UTF_8;
        }
        return charset;
    }
}
