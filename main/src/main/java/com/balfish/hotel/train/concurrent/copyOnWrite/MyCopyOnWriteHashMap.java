package com.balfish.hotel.train.concurrent.copyOnWrite;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yhm on 2017/11/10 PM12:01.
 */
public class MyCopyOnWriteHashMap<K, V> {

    private transient final ReentrantLock reentrantLock = new ReentrantLock();

    private volatile transient HashMap<K, V> hashMap;

    public MyCopyOnWriteHashMap(int expectedSize) {
        hashMap = Maps.newHashMapWithExpectedSize(expectedSize);
    }

    public V put(K k, V v) {
        reentrantLock.lock();
        try {
            HashMap<K, V> curHashMap = Maps.newHashMap(hashMap);
            V newV = curHashMap.put(k, v);
            hashMap = curHashMap;
            return newV;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        reentrantLock.lock();
        try {
            HashMap<K, V> curHashMap = Maps.newHashMap(hashMap);
            curHashMap.putAll(map);
            hashMap = curHashMap;
        } finally {
            reentrantLock.unlock();
        }
    }

    public V get(K k) {
        return hashMap.get(k);
    }

}
