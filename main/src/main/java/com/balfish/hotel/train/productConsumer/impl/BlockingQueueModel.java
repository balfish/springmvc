package com.balfish.hotel.train.productConsumer.impl;

import com.balfish.hotel.train.productConsumer.AbstractConsumer;
import com.balfish.hotel.train.productConsumer.AbstractProducer;
import com.balfish.hotel.train.productConsumer.Model;
import com.balfish.hotel.train.productConsumer.Task;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yhm on 2017/11/6 PM3:08.
 */
public class BlockingQueueModel implements Model {

    private final BlockingQueue<Task> blockingQueue;

    BlockingQueueModel(int capacity) {
        this.blockingQueue = new LinkedBlockingQueue<>(capacity);
    }

    private final AtomicInteger taskNo = new AtomicInteger(0);

    @Override
    public Runnable newRunnableConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                Task task = blockingQueue.take();
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                TimeUnit.MILLISECONDS.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.getNo());
            }
        };
    }

    @Override
    public Runnable newRunnableProducer() {
        return new AbstractProducer() {
            @Override
            public void produce() throws InterruptedException {
                // 不定期生产，模拟随机的用户请求
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                Task task = new Task(taskNo.getAndIncrement());
                blockingQueue.put(task);
                System.out.println("produce: " + task.getNo());
            }
        };
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        Arrays.asList(1, 2).forEach(x -> new Thread(model.newRunnableConsumer()).start());
        Arrays.asList(1, 2, 3, 4, 5).forEach(x -> new Thread(model.newRunnableProducer()).start());
    }
}
