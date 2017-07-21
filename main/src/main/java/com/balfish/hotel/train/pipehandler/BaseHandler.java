package com.balfish.hotel.train.pipehandler;

import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public abstract class BaseHandler {

    public abstract String doHandle(final String origin, Map<String, Object> param);
}
