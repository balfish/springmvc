package com.balfish.hotel.train.pipehandler;

import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ToLowerCaseHandler extends BaseHandler {
    @Override
    public String doHandle(String origin, Map<String, Object> param) {
        return origin.toLowerCase();
    }
}
