package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/1/10 AM10:55.
 */
class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLOWORLD = "hello world";
}

public class PassiveReference3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);// 调用类常量
    }
}
