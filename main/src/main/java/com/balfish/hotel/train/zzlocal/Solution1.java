package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/1/4 AM10:58.
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(isSucc(new int[]{1, 2, 2, 2, 3}));
        System.out.println(isSucc(new int[]{1, 2, 2, 3, 3}));
        System.out.println(isSucc(new int[]{1, 5, 5, 3, 4}));
    }

    private static boolean isSucc(int[] array) {
        // 扑克牌就13张把。。假设输入不大于13了。。
        int[] nums = new int[15];
        int duplicate = 0;
        for (int i = 0; i < array.length; i++) {
            nums[array[i]]++;
        }

        int[] threeNums = new int[3];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 3) {
                return false;
            }

            if (nums[i] == 3 || nums[i] == 1) {
                threeNums[index++] = i;
            }
        }

        int x = threeNums[0];
        int y = threeNums[1];
        int z = threeNums[2];
        int t;
        if (x > y) {
            t = x;
            x = y;
            y = t;
        }
        if (x > z) {
            t = x;
            x = z;
            z = t;
        }
        if (y > z) {
            t = y;
            y = z;
            z = t;
        }
        return (y * 2 == x + z) && (z - x == 2);
    }
}
