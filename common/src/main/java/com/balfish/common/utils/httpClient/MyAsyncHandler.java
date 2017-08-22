package com.balfish.common.utils.httpClient;

import com.ning.http.client.AsyncCompletionHandler;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * Created by yhm on 2017/8/17 AM11:53.
 */
public abstract class MyAsyncHandler<T> extends AsyncCompletionHandler<T> {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式，GET/POST
     */
    private HttpMethod method;
    /**
     * 请求头header参数
     */
    private Map<String, String> headers;
    /**
     * 请求参数
     */
    private Map<String, String> parameters;
    /**
     * 消息体，直接post json/xml的参数
     */
    private String body;
    /**
     * 是否打印日志
     */
    private boolean printLog;

    /**
     * 设置url，用于记录日志
     *
     * @param url
     * @return
     */
    protected MyAsyncHandler addUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 设置请求method，用于记录日志
     *
     * @param method 请求方法
     * @return this
     */
    protected MyAsyncHandler addMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * 设置请求头headers，用于记录日志
     *
     * @param headers 请求头
     * @return this
     */
    protected MyAsyncHandler addHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * 设置请求参数parameters，用于记录日志
     *
     * @param parameters 请求参数
     * @return this
     */
    protected MyAsyncHandler addParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * 设置请求体body，用于记录日志
     *
     * @param body 请求体
     * @return this
     */
    protected MyAsyncHandler addBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * 设置是否打印日志标识，用于记录日志
     *
     * @param printLog 是否打印日志标识
     * @return this
     */
    protected MyAsyncHandler addPrintLog(boolean printLog) {
        this.printLog = printLog;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getBody() {
        return body;
    }

    public boolean getPrintLog() {
        return printLog;
    }
}
