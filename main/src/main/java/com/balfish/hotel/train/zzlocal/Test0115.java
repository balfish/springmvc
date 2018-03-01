package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/1/15 PM12:03.
 */
public class Test0115 implements Runnable {
    private int flag;

    public Test0115(int flag) {
        this.flag = flag;
    }

    private final static Object o1 = new Object();
    private final static Object o2 = new Object();

    public void run() {
        while (true) {
            System.out.println("running");
            if (flag == 0) {
                synchronized (o1) {
                    try {
                        Thread.sleep(50 + (int) (10 * Math.random()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                    }
                }
            }
            if (flag == 1) {
                synchronized (o2) {
                    try {
                        Thread.sleep(50 + (int) (11 * Math.random()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Test0115(1)).start();
        new Thread(new Test0115(0)).start();

    }
}