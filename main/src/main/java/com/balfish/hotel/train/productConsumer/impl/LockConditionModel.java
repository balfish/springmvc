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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yhm on 2017/11/6 PM3:48.
 */
public class LockConditionModel implements Model {

    private final Lock BUFFER_LOCK = new ReentrantLock();
    private final Condition CONDITION = BUFFER_LOCK.newCondition();
    private final Queue<Task> queue = new LinkedList<>();

    private final int capacity;

    public LockConditionModel(int capacity) {
        this.capacity = capacity;
    }

    private final AtomicInteger taskNo = new AtomicInteger(0);

    @Override
    public Runnable newRunnableConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                BUFFER_LOCK.lockInterruptibly();
                try {
                    while (queue.isEmpty()) {
                        CONDITION.await();
                    }

                    Task task = queue.poll();
                    assert task != null;
                    TimeUnit.MILLISECONDS.sleep(500 + (long) (Math.random() * 500));
                    System.out.println("consume: " + task.getNo());
                    CONDITION.signalAll();
                } finally {
                    BUFFER_LOCK.unlock();
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

                BUFFER_LOCK.lockInterruptibly();

                try {
                    while (queue.size() == capacity) {
                        CONDITION.await();
                    }
                    Task task = new Task(taskNo.getAndIncrement());
                    queue.offer(task);
                    System.out.println("produce: " + task.getNo());
                    CONDITION.signalAll();
                } finally {
                    BUFFER_LOCK.unlock();
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
