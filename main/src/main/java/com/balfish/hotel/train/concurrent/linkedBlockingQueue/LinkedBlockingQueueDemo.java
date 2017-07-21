package com.balfish.hotel.train.concurrent.linkedBlockingQueue;

import com.balfish.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue 和 CountDownLatch
 * <p>
 * Created by yhm on 2017/7/12 PM2:17
 */

public class LinkedBlockingQueueDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedBlockingQueueDemo.class);
    private static final LinkedBlockingQueue<StringBuilder> linkedBlockingQueue = new LinkedBlockingQueue<StringBuilder>(1000);
    private static final Integer NUM = 4;
    private static final CountDownLatch countDownLatch = new CountDownLatch(NUM);

    /**
     * 静态代码块，只要线程没有完全退出，就进行阻塞队列的扫描取值
     */
    static {
        new Thread(new Runnable() {
            public void run() {
                while (!(countDownLatch.getCount() == 0 && linkedBlockingQueue.isEmpty())) {
                    StringBuilder sb = linkedBlockingQueue.poll();
                    if (sb != null) {
                        System.out.println("从队列写文件..." + sb);
                        try {
                            FileUtils.writeFile(new File("/home/balfish/桌面/1.txt"), sb.toString() + "\n", true);
                        } catch (IOException e) {
                            System.out.println("从队列写文件出错" + e);
                        }
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int k = 0; k < NUM; k++) {
            new Thread(new Runnable() {
                public void run() {
                    StringBuilder sb = new StringBuilder(String.valueOf(1.11111111111));
                    System.out.println("复杂度很高的操作..." + sb);
                    try {
                        for (int i = 0; i <= 10; i++) {
                            linkedBlockingQueue.put(sb);

                        }
                    } catch (InterruptedException e) {
                        LOGGER.error("insert into linkedBlockingQueue error", e);
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("all finished");
        System.out.println(new File("/home/balfish/桌面/1.txt").length());
    }
}