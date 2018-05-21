package com.balfish.hotel.zzAlgorithm;

/**
 * Created by yhm on 2018/3/17 PM6:21.
 */
public class ZTest0317 {

    public static void main(String[] args) throws InterruptedException {

        // 手写快排 2018/03/17
        int a[] = new int[]{1, 6, 5, 54, 2, 4, 68, 99, 0};
        quickSort(a);

        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] a) {
        if (a.length > 0) {
            _quickSort(a, 0, a.length - 1);
        }
    }

    private static void _quickSort(int[] a, int low, int high) {
        if (low < high) {
            int middle = getMiddle(a, low, high);
            _quickSort(a, low, middle - 1);
            _quickSort(a, middle + 1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {

        int tmp = a[low];
        while (low < high) {
            while (low < high && a[high] >= tmp) {
                high--;
            }
            a[low] = a[high];

            while (low < high && a[low] <= tmp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = tmp;
        return low;
    }


}
