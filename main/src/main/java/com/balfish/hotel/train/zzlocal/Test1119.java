package com.balfish.hotel.train.zzlocal;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;

import java.util.*;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Test1119 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        System.out.println(Optional.ofNullable(null).orElse("default"));

        ArrayListMultimap<Integer, String> multiMap = ArrayListMultimap.create();

        multiMap.put(1, "aa");
        multiMap.put(1, "bb");
        multiMap.put(1, "cc");
        multiMap.put(2, "dd");
//
        for (Integer id : multiMap.keySet()) {
            System.out.println("----------");
            System.out.println(id);
            System.out.println(multiMap.get(id));
        }
        List<Double> list = Lists.newArrayList();
        list.add(1.1);
        list.add(3.3);
        list.add(2.2);

        System.out.println(list);
        sort(list, (left, right) -> Doubles.compare(right, left));
        System.out.println(list);
    }


    /**
     * Collections.sort(List<T> list, Comparator<? super T> c)的底层实现
     *
     * @param list 列表
     * @param c    比较器
     * @param <T>  泛型
     */
    @SuppressWarnings("unchecked")
    private static <T> void sort(List<T> list, Comparator<? super T> c) {
        Object[] array = list.toArray();
        Arrays.sort(array, (Comparator) c);
        ListIterator iterator = list.listIterator();
        for (Object anArray : array) {
            iterator.next();
            iterator.set(anArray);
        }
    }
}
