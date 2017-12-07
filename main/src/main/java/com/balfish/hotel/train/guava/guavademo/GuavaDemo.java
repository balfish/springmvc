package com.balfish.hotel.train.guava.guavademo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * 几个guava特性的演示
 * <p/>
 * <pre>
 *       集合, partition, optional, immutable  etc.
 * </pre>
 */
public class GuavaDemo {

    public static void main(String[] args) {
        // partitionDemo();
//        optionalDemo();
//        collectionDemo();
        unmodifiableAndImmutableDemo();
    }

    /**
     * Immutable(不可变)集合
     * <p/>
     * <pre>
     * 先看下在jdk中提供了Collections.unmodifiableXXX系列方法来实现不可变集合,但是有一些问题,
     * Collections.unmodifiableList实现的不是真正的不可变集合，当原始集合修改后，不可变集合也发生变化。
     * 不可变集合不可以修改集合数据，当强制修改时会报错，实例中的最后两个add会直接抛出不可修改的错误。
     * </pre>
     * <p/>
     * <pre>
     *  immutable对象有以下的优点：
     *      1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
     *      2.线程安全的：immutable对象在多线程下安全，没有竞态条件
     *      3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
     *      4.可以被使用为一个常量，并且期望在未来也是保持不变的
     *
     *  Guava提供了对JDK里标准集合类里的immutable版本的简单方便的实现，以及Guava自己的一些专门集合类的immutable实现。
     *  当你不希望修改一个集合类，或者想做一个常量集合类的时候，使用immutable集合类就是一个最佳的编程实践。
     *
     *  注意：每个Guava immutable集合类的实现都拒绝null值。万一你真的需要能接受null值的集合类，你可以考虑用Collections.unmodifiableXXX。
     *
     * Immutable集合创建方法：
     *    1.用copyOf方法, 譬如, ImmutableSet.copyOf(set)
     *    2.使用of方法，譬如，ImmutableSet.of("a", "b", "c")或者ImmutableMap.of("a", 1, "b", 2)
     *    3.使用Builder类
     * </pre>
     */
    private static void unmodifiableAndImmutableDemo() {
//        unmodifiableDemo();
        immutableDemo();
    }

    private static void immutableDemo() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list：" + list);

        ImmutableList<String> imlist = ImmutableList.copyOf(list);
        System.out.println("imlist：" + imlist);

        ImmutableList<String> imOflist = ImmutableList.of("peida", "jerry", "harry");
        System.out.println("imOflist：" + imOflist);

        ImmutableSortedSet<String> imSortList = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList：" + imSortList);

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after imlist:" + imlist);

        ImmutableSet<Color> imColorSet = ImmutableSet.<Color>builder().add(new Color(0, 255, 255))
                .add(new Color(0, 191, 255)).build();
        System.out.println("imColorSet:" + imColorSet);
    }

    private static void unmodifiableDemo() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> unmodifiableList = Collections.unmodifiableList(list);
        List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));

        System.out.println("unmodifiableList [0]：" + unmodifiableList.get(0));

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after unmodifiableList:" + unmodifiableList);

        // 下面两句均会抛异常
        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList1 add a item after list:" + unmodifiableList1);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList);
    }

    /**
     * com.google.common.collect  [Multiset, Multimap, Table, BiMap]
     */
    private static void collectionDemo() {
        // 不是集合，可以增加重复的元素，并且可以统计出重复元素的个数
        Multiset<Integer> multiSet = HashMultiset.create();
        multiSet.add(10);
        multiSet.add(30);
        multiSet.add(30);
        multiSet.add(40);
        System.out.println(multiSet.count(30)); // 2
        System.out.println(multiSet.size()); // 4
        System.out.println(multiSet);// [40, 10, 30 x 2]

        // 一种key可以重复的map，子类有ListMultimap和SetMultimap，对应的通过key分别得到list和set
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        multimap.put(1, "xiao1");
        multimap.put(1, "xiao11");
        multimap.put(2, "xiao2");
        multimap.put(3, "xiao3");
        System.out.println(multimap.keySet().size());
        for (String str : multimap.get(1)) {
            System.out.println(str + " _debug");
        }

        // 相当于有两个key的map
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "hehe");
        table.put(1, 2, "haha");
        table.put(1, 3, "hiahia");
        table.put(1, 4, "heihei");
        Map<Integer, String> column = table.column(3);
        System.out.println(column);
        Map<Integer, String> map = table.row(1);
        int max = Collections.max(map.keySet());
        System.out.println(max);

        // 是一个一一映射，可以通过key得到value，也可以通过value得到key
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "hello");
        biMap.put(2, "world");
        System.out.println(biMap.inverse().get("world"));
    }

    /**
     * 在Guava中Optional类就是用来强制提醒对Null的判断。
     * <p/>
     * Optional<T>.of(T) 为Optional赋值，当T为Null直接抛NullPointException,建议这个方法在调用的时候直接传常量,不要传变量
     * <p/>
     * Optional<T>.fromNullable(T) 为Optional赋值，当T为Null则使用默认值。建议与or方法一起用,风骚无比
     * <p/>
     * Optional<T>.absent() 为Optional赋值,采用默认值
     * <p/>
     * T or(T) 当Optional的值为null时,使用or赋予的值返回。与fromNullable是一对好基友
     * <p/>
     * T orNull() 当Optional的值为null时,则返回null,否则返回Optional的值
     * <p/>
     * T get() 当Optional的值为null时，抛出IllegalStateException，返回Optional的值
     * <p/>
     * boolean isPresent() 如果Optional存在值,则返回True
     * <p/>
     * Set<T> asSet() 将Optional中的值转为一个Set返回
     */
    private static void optionalDemo() {
        String name1 = "fish";
        String name2 = null;

        System.out.println("我的name是 " + Optional.fromNullable(name1).or("无名氏"));
        System.out.println("我的name是 " + Optional.fromNullable(name2).or("无名氏"));
        System.out.println("我的name是 " + Optional.fromNullable(name1).orNull());
        System.out.println("我的name是 " + Optional.fromNullable(name2).orNull());

        // 只有最后一次的of生效,障眼法
        System.out.println(Optional.of("fish").of("cat").of("world").asSet());

        System.out.println(Optional.of(12).isPresent());
        System.out.println(Optional.absent().isPresent());
        System.out.println("int   " + Optional.of(12).get());
    }

    /**
     * partition：根据size传入的List进行切割，切割成符合要求的小的List 并将这些小的List存入一个新的List对象中返回
     * <p/>
     * 执行结果: [[ab, cd, ef, gh], [ij, kl]]
     */
    private static void partitionDemo() {
        List<String> list = Lists.newArrayList("ab", "cd", "ef", "gh", "ij", "kl");
        List<List<String>> lists = Lists.partition(list, 4);
        System.out.println(lists);
    }

}
