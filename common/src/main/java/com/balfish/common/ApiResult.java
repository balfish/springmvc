package com.balfish.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ApiResult<T> {

    private String errMsg;
    private int status;
    private T data;

    private ApiResult(T t) {
        this.data = t;
    }

    private ApiResult(int status, String errMsg, T t) {
        this.status = status;
        this.errMsg = errMsg;
        this.data = t;
    }

    public static <T> ApiResult<T> buildSuccessResult() {
        return new ApiResult<T>(null);
    }

    public static <T> ApiResult<T> buildSuccessResult(T t) {
        return new ApiResult<T>(t);
    }

    public static <T> ApiResult<T> buildFailedResult(int status, String errMsg, T t) {
        return new ApiResult<T>(status, errMsg, t);
    }

    public static <T> ApiResult<T> buildFailedResult(int status, String errMsg) {
        return new ApiResult<T>(status, errMsg, null);
    }

    public static <T> ApiResult<T> buildFailedResult(String errMsg) {
        return new ApiResult<T>(-1, errMsg, null);
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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

