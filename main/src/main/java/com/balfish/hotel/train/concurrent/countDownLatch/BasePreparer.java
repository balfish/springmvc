package com.balfish.hotel.train.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yhm on 2017/11/9 AM10:41.
 */
public abstract class BasePreparer implements Runnable {

    private CountDownLatch countDownLatch;

    private String prepareName;

    private boolean prepareDone;

    public BasePreparer(String prepareName, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.prepareName = prepareName;
        this.prepareDone = false;
    }

    protected abstract void prepare();

    @Override
    public void run() {
        try {
            prepare();
            prepareDone = true;
        } catch (Exception e) {
            prepareDone = false;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public String getPrepareName() {
        return prepareName;
    }

    public boolean isPrepareDone() {
        return prepareDone;
    }
}
