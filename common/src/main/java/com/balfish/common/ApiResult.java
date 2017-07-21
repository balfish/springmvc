package com.balfish.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ApiResult<T> {

    private final String ver = "1.0";
    private String errmsg;
    private int status;
    private T data;

    private ApiResult(T t) {
        this.data = t;
    }

    private ApiResult(int errcode, String errmsg, T t) {
        this.status = errcode;
        this.errmsg = errmsg;
        this.data = t;
    }

    public static <T> ApiResult<T>
    buildSuccessResult() {
        return new ApiResult<T>(null);
    }

    public static <T> ApiResult<T> buildSuccessResult(T t) {
        return new ApiResult<T>(t);
    }

    public static <T> ApiResult<T> buildFailedResult(int errcode, String errmsg, T t) {
        return new ApiResult<T>(errcode, errmsg, t);
    }

    public static <T> ApiResult<T> buildFailedResult(int errcode, String errmsg) {
        return new ApiResult<T>(errcode, errmsg, null);
    }

    public static <T> ApiResult<T> buildFailedResult(String errmsg) {
        return new ApiResult<T>(-1, errmsg, null);
    }

    public String getVer() {
        return ver;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public int getStatus() {
        return status;
    }

    public void setStatus(int errcode) {
        this.status = errcode;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

