package com.tw.heima.paiyipai.leetCode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-26 9:49 下午
 */
public class TwoSumSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 5, 11, 15};
        int target = 10;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }

        return new int[0];
    }
}
