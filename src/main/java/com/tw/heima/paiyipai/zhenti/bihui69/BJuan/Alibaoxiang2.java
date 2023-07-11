package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:16 上午
 */
public class Alibaoxiang2 {

    /**
     * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，每个箱子上面贴有一个数字，箱子中可能有一个黄金宝箱。
     * 黄金宝箱满足排在它之前的所有箱子数字和等于排在它之后的所有箱子数字之和；
     * 第一个箱子左边部分的数字和定义为0；最后一个箱子右边部分的数字和定义为0.
     * 请帮阿里巴巴找到黄金宝箱，输出第一个满足条件的黄金宝箱编号，如果不存在黄金宝箱，请返回-1。
     *
     * 输入：
     * 2,5,-1,8,6
     *
     * 输出：第一个黄金宝箱的编号
     * 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        String[] arrStr = input.split(",");
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (leftSum == totalSum - leftSum - arr[i]) {
                System.out.println(i);
                System.exit(0);
            }
            leftSum += arr[i];
        }
        System.out.println(-1);
    }
}
