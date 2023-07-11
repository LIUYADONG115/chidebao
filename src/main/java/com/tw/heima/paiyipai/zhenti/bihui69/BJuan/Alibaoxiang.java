package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 11:45 下午
 */
public class Alibaoxiang {
    /**
     * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，每个箱子上面贴有一个数字。
     * 阿里巴巴念出一个咒语数字k(k<N)，找出连续k个宝箱数字和的最大值，并输出该最大值。
     *
     * 输入：
     * 2,10,-3,-8,40,5
     * 4
     * 输出：39
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读入数字序列
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        // 读入咒语数字k
        int k = Integer.parseInt(scanner.nextLine());

        int sum = 0; // 当前子序列数字之和
        // 先计算前k个数字之和
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum; // 初始最大值为窗口内数字之和

        // 滑动窗口，依次计算每个长度为k的子序列的数字之和，并更新最大值
        for (int i = 1; i <= nums.length - k; i++) {
            sum -= nums[i - 1]; // 窗口左端点向右移动一位，减去左端点数字
            sum += nums[i + k - 1]; // 窗口右端点向右移动一位，加上右端点数字
            maxSum = Math.max(maxSum, sum); // 更新最大值
        }

        System.out.println(maxSum);
    }
}


