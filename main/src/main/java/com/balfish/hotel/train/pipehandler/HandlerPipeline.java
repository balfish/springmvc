package com.balfish.hotel.train.pipehandler;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * Pipeline的默认实现,一个字符串会从第一个handler依次处理下去,注意handler之间的顺序
 */
public final class HandlerPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerPipeline.class);

    private List<BaseHandler> baseHandlerList = Lists.newArrayList();

    public void setBaseHandlerList(List<BaseHandler> baseHandlerList) {
        this.baseHandlerList = baseHandlerList;
    }

    public String doHandle(final String origin, Map<String, Object> param) {
        if (CollectionUtils.isEmpty(baseHandlerList)) {
            LOGGER.warn("HandlerPipeline的baseHandlerList为空");
            return origin;
        }

        String desc = origin;
        for (BaseHandler baseHandler : baseHandlerList) {
            desc = baseHandler.doHandle(desc, param);
        }
        return desc;
    }
}
