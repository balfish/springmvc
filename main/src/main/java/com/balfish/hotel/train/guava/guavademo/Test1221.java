package com.balfish.hotel.train.guava.guavademo;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yhm on 2017/12/21 PM5:04.
 */
public class Test1221 {

    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);


//        for (Integer i : list) {
//            if (i == 2) {
//                list.remove(i);
//            } else {
//                System.out.println(i);
//            }
//        }
//
//        for (int i = 0 ; i < list.size(); i++) {
//            if (i == 1) {
//                list.remove(i);
//            } else {
//                System.out.println(i);
//            }
//        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 1) {
                list.remove(next);
            } else {
                System.out.println(next);
            }
        }

    }


}
