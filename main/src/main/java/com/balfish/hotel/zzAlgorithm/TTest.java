package com.balfish.hotel.zzAlgorithm;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by yhm on 2018/3/4 下午1:23.
 */
public class TTest {
    public static void main(String[] args) {
//        List<Integer> integers = topKFrequent(new int[]{4, 6, 6, 3, 3, 3, 3}, 2);
//        List<Integer> integers = topKFrequent(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}, 2);
//        List<Integer> integers = topKFrequent(new int[]{3, 0, 1, 0}, 2);
        List<Integer> integers = topKFrequent2(new int[]{1}, 1);
        System.out.println(integers);



//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 3) {
                iterator.remove();
//                list.remove(next);
            }
        }

//        for (int i = 0 ; i < list.size() ; i++) {
//            if (list.get(i) == 3) {
//                list.remove(i);
//            }
//        }

        System.out.println(list);
    }

    /**
     * 桶排序
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        List<Integer>[] bucket = new List[nums.length];
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (int i = 0; i < bucket[pos].size() && res.size() < k; i++)
                    res.add(bucket[pos].get(i));
            }
        }
        return res;
    }


    /**
     * 堆排序原理，优先队列
     */
    public static List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((l, r) -> r.getValue() - l.getValue());
        heap.addAll(map.entrySet());

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            res.add(heap.poll().getKey());
        }
        return res;
    }
}
