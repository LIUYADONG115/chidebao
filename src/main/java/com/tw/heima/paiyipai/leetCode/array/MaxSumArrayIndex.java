package com.tw.heima.paiyipai.leetCode.array;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-09 7:51 下午
 */
public class MaxSumArrayIndex {

    /**
     * 暴力解答
     *
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        System.out.println(maxSum);
    }

    /**
     * 动态规划解答
     * 数组：[-2,1,-3,4,-1,2,1,-5,4]
     * 1：理解题意
     *  题目要我们找出和最大的连续子数组的值是多少，「连续」是关键字，连续很重要，不是子序列。
     *  题目只要求返回结果，不要求得到最大的连续子数组是哪一个。这样的问题通常可以使用「动态规划」解决。
     * 2：定义子问题
     *  我们 不知道和最大的连续子数组一定会选哪一个数，那么我们可以求出 所有 经过输入数组的某一个数的连续子数组的最大和。
     *  子问题 1：经过 −2 的连续子数组的最大和是多少；
     *  我们不确定的是：−3 是连续子数组的第几个元素。那么我们就把 −3 定义成连续子数组的最后一个元素。在新的定义下，我们列出子问题如下：
     *  子问题 1：以 −2 结尾的连续子数组的最大和是多少；
     *  子问题 2：以 1 结尾的连续子数组的最大和是多少；
     *  .....
     * 3：定义状态
     *  dp[i]：表示以 nums[i] 结尾 的 连续 子数组的最大和。
     * 4：状态转移方程，
     *  根据状态的定义，由于 nums[i] 一定会被选取，并且以 nums[i] 结尾的连续子数组与以 nums[i - 1] 结尾的连续子数组只相差一个元素 nums[i] 。
     *  当 dp[i - 1] > 0 时候:dp[i] = dp[i - 1]+nums[i]
     *  当 dp[i - 1] < 0 时候:dp[i] = nums[i]
     *  因此：
     *  dp[i]=max{nums[i],dp[i−1]+nums[i]}
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp =new int[len];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for(int i =1;i<len;i++){
            if(dp[i-1]>0){
                dp[i] = dp[i-1]+nums[i];
                maxSum = Math.max(maxSum,dp[i]);
            }else{
                dp[i] = nums[i];
                maxSum = Math.max(maxSum,dp[i]);
            }
        }
        return maxSum;
    }


}
