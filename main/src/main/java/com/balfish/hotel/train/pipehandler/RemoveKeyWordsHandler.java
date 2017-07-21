package com.balfish.hotel.train.pipehandler;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class RemoveKeyWordsHandler extends BaseHandler {

    @Override
    public String doHandle(String origin, Map<String, Object> param) {
        String keyword = (String) param.get("keyword");
        if (StringUtils.isBlank(keyword)) {
            return origin;
        }
        return StringUtils.removePattern(origin, keyword);
    }
}
