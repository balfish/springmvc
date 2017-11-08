package com.balfish.hotel.train.zzlocal;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yhm on 2017/11/7 PM2:03.
 */
public class Test1107 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();


        for (int i = 1; i <= 100; i++) {
            Thread.sleep(10);
            if (i == 20) {
                cache.put(1, 999);
            }
            System.out.println(executorService.submit(() -> new Test1107().get(1)).get());
        }
        Thread.currentThread().join();
    }

    private static Map<Integer, Integer> cache = Maps.newHashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Integer get(Integer key) {
        Integer value;
        readWriteLock.readLock().lock();
        try {
            value = cache.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = 1; // 从数据库读取等
                    }
                    readWriteLock.readLock().lock();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    public void put(Integer key, Integer value) {
        readWriteLock.writeLock().lock();
        cache.put(key, value);
        readWriteLock.writeLock().unlock();
    }
}


