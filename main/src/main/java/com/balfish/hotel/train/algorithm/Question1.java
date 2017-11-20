package com.balfish.hotel.train.algorithm;

import java.util.Arrays;

/**
 * Created by yhm on 2017/11/14 AM10:13.
 * <p>
 * 使用快排+二分查找
 */
public class Question1 {

//    /**
//     * 题干的函数
//     */
//    public int[] twoSum(int[] nums, int target) {
//
//        int ret[] = new int[2];
//
//        quickSort(nums); // n * logn ~ n^2
//
////        Arrays.stream(nums).fororEach(i -> System.out.print());
//        for (int num : nums) {
//            int otherNum = binarySearch(nums, target - num); // logn
//            if (otherNum != -1) {
//                ret[0] = num;
//                ret[1] = otherNum;
//                break;  // 减枝
//            }
//        }
//        return ret;
//    }
//
//    private void quickSort(int[] nums) {
//        if (nums.length > 0) {
//            _quickSort(nums, 0, nums.length - 1);
//        }
//    }
//
//    private void _quickSort(int[] nums, int begin, int end) {
//        if (begin < end) {
//            int middle = getMiddle(nums, begin, end);
//            _quickSort(nums, begin, middle - 1);
//            _quickSort(nums, middle + 1, end);
//        }
//    }
//
//    private int getMiddle(int[] nums, int begin, int end) {
//        int tmp = nums[begin];
//        while (begin < end) {
//
//            while (begin < end && nums[end] >= tmp) {
//                end--;
//            }
//            nums[begin] = nums[end];
//
//            while (begin < end && nums[begin] <= tmp) {
//                begin++;
//            }
//            nums[end] = nums[begin];
//        }
//        nums[begin] = tmp;
//        return begin;
//    }
//
//    public static void main(String[] args) {
//        int a[] = new int[]{1, 34, 54, 2, 34, 5, 76, 4, 3, 10};
//        int[] twoNums = new Question1().twoSum(a, 9);
//        System.out.println(twoNums[0]);
//        System.out.println(twoNums[1]);
//    }
//
//    private int binarySearch(int[] nums, int m) {
//        int left = 0;
//        int right = nums.length - 1;
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (nums[mid] > m) {
//                right = mid - 1;
//            } else if (nums[mid] < m) {
//                left = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }


}
