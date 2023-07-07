package com.tw.heima.paiyipai.zhenti.morni.test6;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 11:17 下午
 */
public class MaxSum3shu {
    /**
     * 给定一个正整数数组 A 和一个目标值 k，从数组中选择三个数字使其尽可能地接近k (小于等于k)，请计算可选择的最大的三数之和，若不存在返回-1。
     * 其中，A的长度<100， 1≤A 中元素＜1000; 0≤k≤100000.
     *
     * 输入;
     * 第一行是正整数数组A，逗号分隔
     * 第二行是k
     *
     * 输出：
     * 整数，表示可选择的最大的三数之和
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] strs = str.split(",");
            int[] nums = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                nums[i] = Integer.parseInt(strs[i]);
            }
            // 13,16,17,26
            Arrays.sort(nums);
            int K = sc.nextInt();

            int max = 0;
            for (int i = 0; i <= nums.length - 3; i++) {
                int num = nums[i] + nums[i + 1] + nums[i + 2];
                if (num <= K) {
                    max = Math.max(max,num);
                }
            }
            System.out.println(max);
        }
        sc.close();
    }
}
