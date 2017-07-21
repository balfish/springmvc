package com.balfish.hotel.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Monitor {

    //成功
    String onSucc() default "succ";

    //失败
    String onFail() default "fail";
}
