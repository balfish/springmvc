package com.balfish.hotel.train.java.java8;

import com.balfish.hotel.bean.User;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.javers.common.collections.Sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yhm on 2017/7/19 AM10:04.
 */
public class Java8Demo {
    public static void main(String[] args) {

        List<User> userList = Lists.newArrayListWithExpectedSize(3);
        userList.add(new User("aa", 11, "aaaa"));
        userList.add(new User("bb", 22, "cccc"));
        userList.add(new User("cc", 33, "cccc"));

        List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(collect);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.parallelStream().forEachOrdered(System.out::println);

        ArrayList<String> collect1 = userList.stream().map(User::getName).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect1);

        String collect2 = userList.stream().map(User::getAddress).collect(Collectors.joining(","));
        System.out.println(collect2);

        Integer collect3 = userList.stream().mapToInt(User::getAge).sum();
        System.out.println(collect3);

        Map<String, List<User>> collect4 = userList.stream().collect(Collectors.groupingBy(User::getAddress));
        System.out.println(collect4.get("aaaa"));

        Map<Boolean, List<User>> collect5 = userList.stream().collect(Collectors.partitioningBy(s -> Objects.equals(s.getAddress(), "aaaa")));
        System.out.println(collect5);

        Set<Integer> set1 = Sets.asSet(1, 2, 3);
        Set<Integer> set2 = Sets.asSet(2, 3, 4);
//
//        Map map1 = Maps.newHashMap();
//        map1.put(1, 1);
//        map1.put(2, 2);
//
//        Map map2 = Maps.newHashMap();
//        map2.put(2, 3);
//        map2.put(3, 3);
//
//        MapDifference difference = Maps.difference(map1, map2);

        System.out.println(Sets.difference(set2, set1));

//
//        Map map = difference.entriesOnlyOnLeft();
//        Map map3 = difference.entriesInCommon();
//        System.out.println(map3);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(list1);
    }
}
