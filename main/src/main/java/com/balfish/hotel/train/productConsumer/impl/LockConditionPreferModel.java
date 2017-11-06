package com.balfish.hotel.train.productConsumer.impl;

import com.balfish.hotel.train.productConsumer.AbstractConsumer;
import com.balfish.hotel.train.productConsumer.AbstractProducer;
import com.balfish.hotel.train.productConsumer.Model;
import com.balfish.hotel.train.productConsumer.Task;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yhm on 2017/11/6 PM3:48.
 */
public class LockConditionPreferModel implements Model {

    private final Lock CONSUMER_LOCK = new ReentrantLock();
    private final Condition NOT_EMPTY_CONDITION = CONSUMER_LOCK.newCondition();

    private final Lock PRODUCER_LOCK = new ReentrantLock();
    private final Condition NOT_FULL_CONDITION = PRODUCER_LOCK.newCondition();
    private AtomicInteger bufLen = new AtomicInteger(0);
    private final Buffer<Task> buffer = new Buffer<>();

    private final int capacity;

    public LockConditionPreferModel(int capacity) {
        this.capacity = capacity;
    }

    private final AtomicInteger taskNo = new AtomicInteger(0);

    @Override
    public Runnable newRunnableConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                int newBufSize;
                CONSUMER_LOCK.lockInterruptibly();
                try {
                    while (bufLen.get() == 0) {
                        System.out.println("buffer is empty...");
                        NOT_EMPTY_CONDITION.await();
                    }

                    Task task = buffer.poll();
                    newBufSize = bufLen.decrementAndGet();
                    assert task != null;
                    TimeUnit.MILLISECONDS.sleep(500 + (long) (Math.random() * 500));
                    System.out.println("consume: " + task.getNo());
                    if (newBufSize > 0) {
                        NOT_EMPTY_CONDITION.signalAll();
                    }
                } finally {
                    CONSUMER_LOCK.unlock();
                }

                if (newBufSize < capacity) {
                    PRODUCER_LOCK.lockInterruptibly();
                    try {
                        NOT_FULL_CONDITION.signalAll();
                    } finally {
                        PRODUCER_LOCK.unlock();
                    }
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
                int newBufSize;
                PRODUCER_LOCK.lockInterruptibly();

                try {
                    while (bufLen.get() == capacity) {
                        System.out.println("buffer is full...");
                        NOT_FULL_CONDITION.await();
                    }
                    Task task = new Task(taskNo.getAndIncrement());
                    buffer.offer(task);
                    newBufSize = bufLen.incrementAndGet();
                    System.out.println("produce: " + task.getNo());
                    NOT_FULL_CONDITION.signalAll();
                } finally {
                    PRODUCER_LOCK.unlock();
                }

                if (newBufSize > 0) {
                    CONSUMER_LOCK.unlock();
                    try {
                        NOT_EMPTY_CONDITION.signalAll();
                    } finally {
                        CONSUMER_LOCK.unlock();
                    }
                }
            }
        };
    }

    private static class Buffer<E> {
        private Node head;
        private Node tail;

        Buffer() {
            head = tail = new Node(null);
        }

        private void offer(E e) {
            tail.next = new Node(e);
            tail = tail.next;
        }

        private E poll() {
            head = head.next;
            E e = head.item;
            head.item = null;
            return e;
        }

        private class Node {
            E item;
            Node next;

            Node(E item) {
                this.item = item;
            }
        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        Arrays.asList(1, 2).forEach(x -> new Thread(model.newRunnableConsumer()).start());
        Arrays.asList(1, 2, 3, 4, 5).forEach(x -> new Thread(model.newRunnableProducer()).start());
    }
}
