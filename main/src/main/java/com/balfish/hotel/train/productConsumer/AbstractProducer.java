package com.balfish.hotel.train.productConsumer;

/**
 * Created by yhm on 2017/11/6 PM3:02.
 */
public abstract class AbstractProducer implements Runnable {

    protected abstract void produce() throws InterruptedException;

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
