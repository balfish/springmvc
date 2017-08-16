package com.balfish.hotel.train.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yhm on 2017/8/1 PM2:18.
 * <p>
 * xml切面示例
 */
public class AspectDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectDemo.class);

    public void beforeAdvice() {
        LOGGER.info("aspect 已经生效喽");
    }
}
