package com.balfish.hotel.zzAlgorithm;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yhm on 2018/3/17 PM6:21.
 */
public class SingleTon {

    private SingleTon() {

    }

    private static class Holder {

        private static final SingleTon singleTon = new SingleTon();

        public static SingleTon getSingleTon() {
            return Holder.singleTon;
        }
    }


    public static void main(String[] args) {
        String geo = "point(19.121 116.123)";
        Pattern compile = Pattern.compile("[\\d]+\\.?[\\d]+");
        Matcher matcher = compile.matcher(geo);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
