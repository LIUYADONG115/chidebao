package com.tw.heima.paiyipai.leetCode.dongtaiguihua;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-02 1:17 下午
 */
public class MaximumPathSum {
    /**
     * 一个高度为N的由正整数组成的三角形，从上走到下，求经过的数字和的最大值。
     * 每次只能走到下一层相邻的数上，例如从第3层的6向下走，只能走到第4层的2或9上。
     *
     * 该三角形第n层有n个数字，例如：
     *
     * 第一层有一个数字：5
     *
     * 第二层有两个数字：8 4
     *
     * 第三层有三个数字：3 6 9
     *
     * 第四层有四个数字：7 2 9 5
     *
     * 最优方案是：5 + 8 + 6 + 9 = 28
     * @param triangle
     * @return
     */
    //方案1
    public static int maximumPathSum(int[][] triangle) {
        int n = triangle.length;

        // 创建一个二维数组用于保存中间结果
        int[][] dp = new int[n][n];

        // 初始化最后一行
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }

        // 从倒数第二行开始向上递推
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 在下一行的相邻两个数中选择较大的数与当前位置的数相加
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        // 最终结果保存在dp数组的第一个元素
        return dp[0][0];
    }

//    public static void main(String[] args) {
//        int[][] triangle = {
//                {5},
//                {8, 4},
//                {3, 6, 9},
//        };
//        int maxSum = maximumPathSum(triangle);
//        System.out.println("最大和为: " + maxSum);
//    }

    //方案2
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long max = 0;
        int[][] dp = new int[n][n];
        dp[0][0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int num = in.nextInt();
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + num;
                else
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + num;
                max = Math.max(dp[i][j], max);
            }
        }
        System.out.println(max);
    }
}

