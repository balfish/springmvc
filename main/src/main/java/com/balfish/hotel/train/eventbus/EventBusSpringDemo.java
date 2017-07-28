package com.balfish.hotel.train.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * eventBus和spring的整合
 */

@Component
public class EventBusSpringDemo implements InitializingBean {

    private static final Logger LOGGER  = LoggerFactory.getLogger(EventBusSpringDemo.class);

    @PostConstruct
    public void init() {
        com.balfish.hotel.train.eventbus.MyEventBus.register(this);
    }

    @Subscribe
    public void sayHello(Object object) {
        LOGGER.info(object.toString() + "hehe");
    }

    @PreDestroy
    public void destroy() {
        MyEventBus.unregister(this);
    }

    public void afterPropertiesSet() throws Exception {
        LOGGER.info("lalalala~~~");
    }
}
