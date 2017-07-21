package com.balfish.hotel.train.eventbus;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class MyEventBus {

    private final static AsyncEventBus ASYNC_EVENT_BUS = new AsyncEventBus(Executors.newFixedThreadPool(20));

    public static void post(Object event) {
        ASYNC_EVENT_BUS.post(event);
    }

    public static void register(Object handler) {
        ASYNC_EVENT_BUS.register(handler);
    }

    public static void unregister(Object handler) {
        ASYNC_EVENT_BUS.unregister(handler);
    }
}
