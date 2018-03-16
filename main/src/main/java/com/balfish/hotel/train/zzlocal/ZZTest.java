package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/3/2 PM7:32.
 */
public class ZZTest {

    public static void main(String[] args) {

        int[] m = new int[]{1, 2, 3, 4, 5};
        int[] n = new int[]{2, 3, 5};
        longCommonSubstring(m, n);
        longCommonSubSequence(m, n);
    }


    private static void longCommonSubstring(int[] m, int[] n) {
        int dp[][] = new int[10][10];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 1;

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < n.length; j++) {
                dp[i][j] = m[i] == n[j] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                System.out.println(String.format("dp[%s][%s] = %s", i, j, dp[i][j]));
            }
        }

        System.out.println(dp[m.length - 1][n.length - 1]);
    }

    private static void longCommonSubSequence(int[] m, int[] n) {
        int dp[][] = new int[10][10];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 1;

        int len;
        int max_len = 0;

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < n.length; j++) {
                if (m[i] == n[j]) {
                    len = dp[i - 1][j - 1] + 1;
                } else {
                    len = 1;
                }
                max_len = Math.max(max_len, len);

            }
        }

        System.out.println(max_len);
    }
}
