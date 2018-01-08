package com.balfish.hotel.train.zzlocal;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by yhm on 2018/1/2 AM11:36.
 * <p>
 * 专为leetcode刷题的类
 * 好好算法，天天向上 -v-
 */
public class Solution {

    public static void main(String[] args) {

        int array[] = new int[]{3, 2, 1, 8, 9, 6, 7};

        bubbleSortV2(array);
        for (int element : array) {
            System.out.print(element + " ");
        }
    }

    private static void swap(int array[], int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    /**
     * 逆序的冒泡
     *
     * @param array 数组
     */
    private static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    /**
     * 优化版的冒泡 如果某次遍历没有一个元素进行了交换，那么认为之后的均有序，提前剪枝
     *
     * @param array 数组
     */
    private static void bubbleSortV2(int array[]) {
        boolean flag = true;
        for (int i = 0; i < array.length && flag; i++) {
            flag = false;
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    flag = true;
                }


            }
        }
    }

    /**
     * 选择排序
     *
     * @param array 数组
     */
    private static void selectSort(int array[]) {
        //选择排序
        int min;
        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, min, i);
            }
        }
    }
}
