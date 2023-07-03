package com.tw.heima.paiyipai.zhenti.morni.test3;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-02 11:17 下午
 */
public class GetRealN {
    /**
     * 给定一个表面数字 N，该数字是在如下机制下产生的：从1到N，遇到任意数
     * 字位置为数宇 4就直接跳过（比如 23会变成 25， 39 会变成 50，399 会变成 500），请计算表面数字 N 的真实值应该是多少。
     * <p>
     * 输入：
     * 数字 N，表示表面值。
     * <p>
     * 输出：
     * 数字，表示真实值。
     * <p>
     * 示例：
     * 输入：
     * 18
     * 输出：
     * 16
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getRealNumber(n));
    }

    /**
     * 遍历1 至N，计算所有带 4 的数字的个数count，真实值即 N-count。
     * 判断数字是否包含4，可以通过除10求余判断的方式（对于非个位的 4，可以考虑增加变量来减少判断次数）
     */
    public static int getRealNumber(int num) {
        //包含4的个数
        int count = 0;
        for (int i = 0; i < num; i++) {
            if (containsDigitFour(i)) {
                count++;
            }
        }
        return num - count;

    }

    /**
     * 通过除10求余，判断数字是否包含4
     * @param num
     * @return
     */
    public static boolean containsDigitFour(int num) {
        while (num > 0) {
            if (num % 10 == 4) {
                return true;
            }
            num = num / 10;
        }
        return false;
    }
}
