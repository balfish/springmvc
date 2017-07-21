package com.balfish.hotel.bean;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public abstract class MerchantAttrsVo {
    private Map<String, String> extraAttr = Maps.newHashMap();

    public MerchantAttrsVo setExtraAttr(Map<String, String> extraAttr) {
        this.extraAttr = extraAttr;
        return this;
    }

    public Map<String, String> getExtraAttr() {
        return extraAttr;
    }
}
