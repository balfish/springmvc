package com.balfish.hotel.zzAlgorithm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yhm on 2018/4/24 PM7:28.
 */
public class TestUtil extends Thread {

        static int a = 1;
//    volatile static AtomicInteger a = new AtomicInteger(1);

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            a += 1;
//            a.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestUtil testUtil1 = new TestUtil();
        TestUtil testUtil2 = new TestUtil();

        testUtil1.start();
        testUtil2.start();

        testUtil1.join();
        testUtil2.join();


        System.out.println(a);
    }
}
