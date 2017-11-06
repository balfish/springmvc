package com.balfish.hotel.train.productConsumer;

/**
 * Created by yhm on 2017/11/6 PM3:02.
 */
public abstract class AbstractConsumer implements Runnable {

    protected abstract void consume() throws InterruptedException;

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
