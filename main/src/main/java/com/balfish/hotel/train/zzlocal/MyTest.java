package com.balfish.hotel.train.zzlocal;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yhm on 2018/1/9 PM6:22.
 */
public class MyTest {

    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> add("aa")).start();
        }
        System.out.println(map.get("aa"));
    }

    public static void add(String key) {
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
        } else {
            map.put(key, value + 1);
        }
    }
}
