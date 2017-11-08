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
        threadLocalDemo.setValue();

        Thread thread1 = new Thread(() -> {
            threadLocalDemo.setValue();
            System.out.println(threadLocalDemo.getName());

        });

        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(() -> {
            threadLocalDemo.setValue();
            System.out.println(threadLocalDemo.getName());

        });

        thread2.start();
        thread2.join();
        System.out.println(threadLocalDemo.getName());
    }

}
