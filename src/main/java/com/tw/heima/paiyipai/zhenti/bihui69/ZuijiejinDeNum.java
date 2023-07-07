package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个数组X和正整数K，请找出使表达式X[i] - x[i +1] … - X[i + K-1]，结果最接近于数组
 * 的下标i，如果有多个i满足条件，请返回最大的i。
 * <p>
 * 输入：[50,50,2,3],2
 * 输出：1
 *
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-06 10:11 下午
 */
public class ZuijiejinDeNum {
    /**
     * 思路：
     * 1、中位数为50: [50,50,2,3]升序排序后变成[2,3,50,50]，中位数为下标4/2=2的元素50;
     * 2、计算结果为1: X[50,50,2,3]根据题目计算X[i] - …- X[i + K- 1]得出三个数
     * 0 (X[0]-X[1]= 50 -50) 、
     * 48 (X[1]-X[2] = 50 -2)
     * -1 (X[2]-X[3]= 2-3) ，
     * <p>
     * 其中48最接近50，因此返回下标1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input_str = in.nextLine().replaceAll("[\\[\\]]", ""); // 去除输入字符串中的方括号
        String[] input_nums = input_str.split(","); // 将输入字符串按逗号分割成数组
        int n = input_nums.length - 1; // 获取数组长度
        int[] nums = new int[n]; // 创建一个长度为n的整数数组
        int k = Integer.parseInt(input_nums[n]); // 获取最后一个元素作为K的值
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input_nums[i]); // 将字符串转换为整数并存入数组
        }

        int[] sorted_nums = Arrays.copyOf(nums, n); // 复制数组
        Arrays.sort(sorted_nums); // 对复制的数组进行排序
        int median = sorted_nums[n / 2]; // 获取排序后的数组的中位数

        int minDiff = Integer.MAX_VALUE; // 初始化最小差值为整数的最大值
        int result = -1; // 初始化结果为-1
        for (int i = 0; i <= n - k; i++) { // 遍历数组，注意边界条件
            int sum = nums[i]; // 初始化sum为当前元素的值
            for (int j = i + 1; j < i + k; j++) { // 遍历计算表达式的值
                sum -= nums[j]; // 将后续元素依次减去
            }
            int diff = Math.abs(sum - median); // 计算差值的绝对值
            if (diff < minDiff) { // 如果差值小于最小差值
                minDiff = diff; // 更新最小差值
                result = i; // 更新结果
            }
        }

        System.out.println(result); // 输出结果
    }
}

