package com.balfish.hotel.train.http;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class HttpClientResponse implements Serializable {

    private static final int STATUS_ERROR = -1;
    private static final int STATUS_OK = 200;
    private static final long serialVersionUID = 109888044977284411L;

    private int status;
    private String message;
    private String content;

    private HttpClientResponse(int status, String message, String content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public static HttpClientResponse createWithOk(String content) {
        return new HttpClientResponse(STATUS_OK, "ok", content);
    }

    public static HttpClientResponse createWithException(Exception e) {
        return new HttpClientResponse(STATUS_ERROR, e.getMessage(), null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
