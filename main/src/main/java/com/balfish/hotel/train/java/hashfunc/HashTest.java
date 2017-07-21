package com.balfish.hotel.train.java.hashfunc;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * (1) 如果hashCode值不一样, 那么就无需比较equals方法
 * (2) 在add方法会调用判重, 来和之前集合中的元素做对比, 一旦相等即退出
 */
public class HashTest {
    static class Person {

        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String toString() {
            return "(" + name + ":" + age + ")";
        }

        public int hashCode() {

            System.out.println(this.name + "...hashCode");
            return 30;   // 标记1
//        return name.hashCode() + age * 37;   //标记2
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Person))
                return false;
            Person p = (Person) obj;
            System.out.println(this.name + "...equals..." + p.name + "?");
            return this.name.equals(p.name) && this.age == p.age;
        }
    }

    public static void main(String[] args) {
        HashSet<Person> hs = new HashSet<Person>();
        hs.add(new Person("a1", 11));
        hs.add(new Person("a2", 12));
        hs.add(new Person("a3", 13));
        hs.add(new Person("a2", 12));
        //  换成这个试试
//        hs.add(new Person("a2", 14));

        Iterator it = hs.iterator();
        while (it.hasNext()) {
            Person p = (Person) it.next();
            System.out.println(p.getName() + "::" + p.getAge());
        }
    }
}
