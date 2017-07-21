package com.balfish.hotel.train.java.threadlocal;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ThreadLocalDemo {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private void setValue() {
        threadLocal.set(Thread.currentThread().getName());
    }

    private String getName() {
        return threadLocal.get();
    }


    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();

        Thread thread = new Thread(() -> {
            threadLocalDemo.setValue();
            System.out.println(threadLocalDemo.getName());

        });

        thread.start();
        thread.join();
        System.out.println(threadLocalDemo.getName());
    }

}
