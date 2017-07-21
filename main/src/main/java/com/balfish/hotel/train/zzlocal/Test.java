package com.balfish.hotel.train.zzlocal;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Test {

    private static final Comparator DESC = new Comparator<Double>() {
        public int compare(Double left, Double right) {
            return Doubles.compare(right, left);
        }
    };

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
//        System.out.println(Optional.fromNullable(null).or("default"));

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
        sort(list, Test.DESC);
        System.out.println(list);


        System.out.println(LocalDateTime.now().plusHours(8).toString("yyyy年MM月dd日 HH:mm:ss"));
        System.out.println(LocalDateTime.now().plusHours(9).toString("yyyy年MM月dd日 HH:mm:ss"));

        System.out.println(new Timestamp(1491375639000L).toString());


        List<Integer> rids = Collections.emptyList();

        ImmutableList<Integer> resourceEntityList = FluentIterable.from(rids)
                .transform(new Function<Integer, Integer>() {
                    public Integer apply(Integer rid) {
                        return null;
                    }
                }).toSet().asList();

        System.out.println(resourceEntityList);
    }


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
