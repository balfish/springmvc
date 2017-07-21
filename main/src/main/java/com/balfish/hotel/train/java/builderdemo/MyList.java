package com.balfish.hotel.train.java.builderdemo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * builder设计模式
 */
public class MyList extends ArrayList {

    private List<Object> list;

    MyList(List<Object> list) {
        this.list = list;
    }

    public static <E> Builder<E> builder() {
        return new Builder<E>();
    }

    public static final class Builder<E> {
        private List<Object> tmpList = Lists.newArrayList();

        public Builder<E> add(E element) {
            tmpList.add(element);
            return this;
        }

        public Builder<E> addAll(List<E> elementList) {
            tmpList.addAll(elementList);
            return this;
        }

        public MyList build() {
            return new MyList(tmpList);
        }
    }

    @Override
    public String toString() {
        return "MyList{" + "list=" + list + '}';
    }

    public static void main(String[] args) {

        MyList myList = MyList.builder().add(1).add("hehe").build();
        System.out.println(myList);
    }

}
