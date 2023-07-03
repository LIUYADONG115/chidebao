package com.tw.heima.paiyipai.leetCode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-27 9:11 下午
 */
public class LongestConsecutiveSolution {
    public static void main(String[] args) {

        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <p>
     * 输入：nums = [100,4,200,1,3,2]
     * <p>
     * 输出：4
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }
}
