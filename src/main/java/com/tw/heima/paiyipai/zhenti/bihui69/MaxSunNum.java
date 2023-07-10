package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-08 2:24 下午
 */
public class MaxSunNum {
    /**
     * 给定一个由若干整数组成的数组nums ，可以在数组内的任意位置进行分割，将该数组分割成两个非空子数组（即左数组和右数组），
     * 分别对子数组求和得到两个值，计算这两个值的差值，请输出所有分割方案中，差值最大的值。
     * <p>
     * 6
     * 1 -2 3 4 -9 7
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入数组长度
        int length = Integer.parseInt(scanner.nextLine());

        // 输入数字序列
        long[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 左数组的和
        long leftSum = 0;

        // 右数组的和
        long rightSum = Arrays.stream(numbers).sum();

        // 差值的最大取值
        long maxDifference = 0;

        // 遍历数组，计算差值的最大取值
        for (int i = 0; i < length - 1; i++) {
            // 更新左数组的和
            leftSum += numbers[i];

            // 更新右数组的和
            rightSum -= numbers[i];

            // 计算当前分割方案的差值
            long difference = Math.abs(leftSum - rightSum);

            // 更新差值的最大取值
            maxDifference = Math.max(maxDifference, difference);
        }

        // 输出差值的最大取值
        System.out.println(maxDifference);
    }
}
