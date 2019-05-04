package com.balfish.hotel.train.zzlocal;

import java.util.Arrays;

/**
 * Created by yhm on 2018/3/7 PM7:08.
 */
public class ZZTest2 {

    public static void main(String[] args) {
        int a[] = new int[]{1, 2, 3, 4};
        func(a, 0);
    }

    private static void func(int[] a, int index) {

        if (index == a.length) {
            System.out.println(Arrays.toString(a));
            return;
        }

        for (int i = index; i < a.length; i++) {
            swap(a, index, i);
            func(a, index + 1);
            swap(a, i, index);
        }
    }

    private static void swap(int[] a, int index, int i) {
        int tmp = a[index];
        a[index] = a[i];
        a[i] = tmp;
    }
}
