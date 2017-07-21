package com.balfish.hotel.train.concurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * result:
 * Worker's waiting
 * Worker's waiting
 * Worker's waiting
 * Worker's waiting
 * Worker's waiting
 * Inside Barrier
 * ID:9 Working
 * ID:10 Working
 * ID:11 Working
 * ID:12 Working
 * ID:13 Working
 * <p>
 * <p>
 * CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 * CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。
 * CyclicBarrier初始时还可带一个Runnable的参数， 此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。
 */
public class CyclicBarrierDemo {

    private static final int THREAD_NUM = 5;

    public static class WorkerThread implements Runnable {

        CyclicBarrier barrier;

        public WorkerThread(CyclicBarrier b) {
            this.barrier = b;
        }

        public void run() {
            try {
                System.out.println("Worker's waiting");
                //线程在这里等待，直到所有线程都到达barrier
                barrier.await();
                System.out.println("ID:" + Thread.currentThread().getId() + " Working");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
//            //当所有线程到达barrier时执行
//            @Override
//            public void run() {
//                System.out.println("Inside Barrier");
//            }
//        });
//
//        for (int i = 0; i < THREAD_NUM; i++) {
//            new Thread(new WorkerThread(cb)).start();
//        }

        try {
            throw new RuntimeException("fist");
        } catch (RuntimeException e) {
            throw new RuntimeException("second");
        } catch (Exception e) {
            System.out.println("heeh");
            //ignore
        }
    }
}