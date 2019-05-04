package com.balfish.hotel.train.guava.guavademo;

/**
 * Created by yhm on 2017/12/21 PM5:04.
 */
public class Test1221 {

    public static void main(String[] args) {

        // 加法方式
        int a = 1;
        int b = 2;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        //异或
        a = 1;
        b = 2;

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println(a);
        System.out.println(b);
    }

}
