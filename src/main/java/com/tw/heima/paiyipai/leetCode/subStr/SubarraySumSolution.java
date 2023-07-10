package com.tw.heima.paiyipai.leetCode.subStr;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-08 11:45 上午
 */
public class SubarraySumSolution {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1, 2, 1}, 2));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
