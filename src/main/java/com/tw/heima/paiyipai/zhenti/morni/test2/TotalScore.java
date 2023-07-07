package com.tw.heima.paiyipai.zhenti.morni.test2;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 9:06 下午
 */
public class TotalScore {
    /**
     * 给定一个长度为n的整型数组，每个元素代表分数，索引从0开始。初始总分数为0，依次遍历数组，每次必须根据特定规则更新总分数。
     * 规则：若选择当前元素，则总分数加上该元素值；若不选择当前元素，则总分数还原为三次更新前的总分数（若当前元素索引小于3则将总分数置为0）。
     * 请计算遍历结束后可以得到的最大总分数
     *
     * 动态规划。首先初始化一个长度为n的数组，用于存储每次遍历更新后的总分数。
     * 每次更新时，比较两种选择更新后的总分数，选择其中最大的一个更新当前的总分数
     * @param args
     */
    public static void main(String[] args) {
        String input = "2,-10,5,3,67,43";
        String[] scores = input.split(",");
        int[] nums = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            nums[i] = Integer.parseInt(scores[i]);
        }

        long max = 0;
        int n = nums.length + 3;
        int[] dp = new int[n];
        int len = n-3;
        for (int i = 0; i < len; i++) {
            dp[i + 3] = Math.max(dp[i + 2] + nums[i], dp[i]);
        }

        //int maxScore = Math.max(dp[n + 2], dp[n + 1]);

        System.out.println("遍历结束后可以得到的最大总分数：" + dp[n-1]);
    }
}
