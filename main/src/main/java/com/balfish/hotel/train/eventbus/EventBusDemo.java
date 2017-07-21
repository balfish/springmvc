package com.balfish.hotel.train.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class EventBusDemo {

    public static void main(String[] args) {


        final EventBus eventBus = new EventBus();

        eventBus.register(new Object() {

            @Subscribe
            public void lister(Integer i) {
                System.out.printf("%s from int\n", i);
            }

            @Subscribe
            public void lister(Long l) {
                System.out.printf("%s from long\n", l);
            }

            @Subscribe
            public void lister(DeadEvent event) {
                System.out.printf("%s=%s from dead events\n", event.getSource().getClass(), event.getEvent());
            }
        });

        eventBus.post(1);
        eventBus.post(1L);
        eventBus.post(new Object());
    }
}
