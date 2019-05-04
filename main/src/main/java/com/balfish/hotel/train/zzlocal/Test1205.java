package com.balfish.hotel.train.zzlocal;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yhm on 2017/12/5 AM10:21.
 */
public class Test1205<T> {

    public static <T> byte[] Serialize(T object) {
        return null;
    }

    private <T> T service() {
        return null;
    }

    public Test1205() {
    }

    public static void main(String[] args) {

//        HashSet<Integer> set = Sets.newHashSetWithExpectedSize(127);

//        HashSet<Integer> set = new HashSet<>(200);

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(129);
        System.out.println(set);
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
