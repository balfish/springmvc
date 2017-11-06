package com.balfish.hotel.train.productConsumer.impl;

import com.balfish.hotel.train.productConsumer.AbstractConsumer;
import com.balfish.hotel.train.productConsumer.AbstractProducer;
import com.balfish.hotel.train.productConsumer.Model;
import com.balfish.hotel.train.productConsumer.Task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yhm on 2017/11/6 PM3:48.
 */
public class WaitNotifyModel implements Model {

    private final Object BUFFER_LOCK = new Object();

    private final Queue<Task> queue = new LinkedList<>();
    private final int capacity;

    public WaitNotifyModel(int capacity) {
        this.capacity = capacity;
    }

    private final AtomicInteger taskNo = new AtomicInteger(0);

    @Override
    public Runnable newRunnableConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                synchronized (BUFFER_LOCK) {
                    while (queue.isEmpty()) {
                        BUFFER_LOCK.wait();
                    }

                    Task task = queue.poll();
                    assert task != null;
                    TimeUnit.MILLISECONDS.sleep(500 + (long) (Math.random() * 500));
                    System.out.println("consume: " + task.getNo());
                    BUFFER_LOCK.notifyAll();
                }
            }
        };
    }

    @Override
    public Runnable newRunnableProducer() {
        return new AbstractProducer() {
            @Override
            public void produce() throws InterruptedException {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                synchronized (BUFFER_LOCK) {
                    while (queue.size() == capacity) {
                        BUFFER_LOCK.wait();
                    }
                    Task task = new Task(taskNo.getAndIncrement());
                    queue.offer(task);
                    System.out.println("produce: " + task.getNo());
                    BUFFER_LOCK.notifyAll();
                }
            }
        };
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        Arrays.asList(1, 2).forEach(x -> new Thread(model.newRunnableConsumer()).start());
        Arrays.asList(1, 2, 3, 4, 5).forEach(x -> new Thread(model.newRunnableProducer()).start());
    }
}
