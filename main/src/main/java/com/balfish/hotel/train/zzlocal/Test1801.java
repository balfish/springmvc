package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/2/24 PM5:06.
 */
public class Test1801 {

    public static void main(String[] args) {
        System.out.println(func("www.zhidao.baidu.com"));
    }

    private static String func(String str) {
        char c = '.';
        char[] chars = str.toCharArray();

        String[] string = new String[10];
        for (int i = 0; i < string.length; i++) {
            string[i] = "";
        }

        int num = 0;

        int length = chars.length;

        for (int i = 0; i < length; i++) {
            if (chars[i] == c) {
                num++;
                continue;
            }
            string[num] += chars[i];
        }

        System.out.println(string[0]);

        StringBuilder stringBuilder = new StringBuilder();

        while (num >= 0) {
            stringBuilder.append(string[num]);

            if (num != 0) {
                stringBuilder.append("/");
            }
            num--;
        }
        return stringBuilder.toString();


    }
}
