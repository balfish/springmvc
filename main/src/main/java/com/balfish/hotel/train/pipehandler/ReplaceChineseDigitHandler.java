package com.balfish.hotel.train.pipehandler;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ReplaceChineseDigitHandler extends BaseHandler {

    private static final String[] CHINESE_DIGIT_STRING = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] DIGIT_STRING = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public String doHandle(String origin, Map<String, Object> param) {
        return StringUtils.replaceEach(origin, CHINESE_DIGIT_STRING, DIGIT_STRING);
    }
}
