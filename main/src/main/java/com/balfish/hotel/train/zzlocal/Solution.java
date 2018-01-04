package com.balfish.hotel.train.zzlocal;


import java.util.*;

/**
 * Created by yhm on 2018/1/2 AM11:36.
 * <p>
 * 专为leetcode刷题的类
 * 好好算法，天天向上 -v-
 */
class Node {
    Node next;
    int val;
}


public class Solution {
    public static void main(String[] args) {
        String str = "zzzcccAaaasdsF";
        System.out.println(deal(str));

    }

    public static String deal(String str) {
        // 下标为 0-25 存'A' - 'Z'  下表为26-51存 'a' - 'z'
        int letters[] = new int[52];

        for (char c : str.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters[(int) c - 'a' + 26]++;
            } else if (c >= 'A' && c <= 'Z') {
                letters[(int) c - 'A']++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < letters.length; l++) {
            if (letters[l] != 0) {
                char c = l >= 26 ? (char) (l - 26 + 'a') : (char) (l + 'A');
                System.out.println(String.format("字符%s出现了%s次", c, letters[l]));
                sb.append((char) (l - 26 + 'a'));
            }
        }
        return sb.toString();
    }
}
