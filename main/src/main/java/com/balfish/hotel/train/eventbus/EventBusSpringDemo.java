package com.balfish.hotel.train.eventbus;

import com.google.common.eventbus.Subscribe;
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


    @PostConstruct
    public void init() {
        com.balfish.hotel.train.eventbus.MyEventBus.register(this);
    }

    @Subscribe
    public void sayHello(Object object) {
        System.out.println(object.toString() + "hehe");
    }


    @PreDestroy
    public void destroy() {
        MyEventBus.unregister(this);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("lalalala~~~");
    }
}
