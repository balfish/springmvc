package com.balfish.common.utils.lcs;

/**
 * 最长公共子序列 核心思想是dp
 * <p>
 * Created by yhm on 2017/7/12 PM2:17
 */
public class SimilarUtils {

    public static double LCS(String str1, String str2, int len1, int len2) {
        /**
         * 　初始化状态转移数组
         */
        int dp[][] = new int[len1 + 2][len2 + 2];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = 0;
        }

        /**
         * 状态转移方程如下
         *
         * dp[i][j] = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? dp[i - 1][j - 1] + 1 : max(dp[i][j - 1],dp[i- 1][j])
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = (dp[i - 1][j] >= dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1]);
                }
            }
        }
        return (double) dp[len1][len2] / Math.max(len1, len2);
    }
}
