package com.tw.heima.paiyipai.zhenti.morni.test5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-04 10:24 下午
 */
public class SwitchNum {
    /**
     * 给定一个整数数组，给定数字k，请计算使数组中所有小于k的整数相邻的最少交换次数。100<=k<=100, -100<=数组元素<= 100.
     *
     * 输入：
     * 第一行是数组，空格分隔
     * 第二行是整数，表示k
     *
     * 输出：
     * 整数，表示最少交换次数。
     *
     * 示例：
     * 输入：
     * 2 4 2 5 1
     * 3
     * 输出：
     * 1
     *
     * [2 5 7 6 1 4 5 7 2 1]
     * 3
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] stringNums = str.split("\\s");
            int k = Integer.parseInt(sc.nextLine());

            int chuangKouLength = 0;
            for (int i = 0; i < stringNums.length; i++) {
                if (Integer.parseInt(stringNums[i]) < k) {
                    chuangKouLength++;
                }
            }
            if(chuangKouLength == 0){
                System.out.println(0);
                return;
            }

            int max = 0;

            for (int i = 0; i <= stringNums.length - chuangKouLength; i++) {
                int current = 0;
                for (int j = 0; j < chuangKouLength; j++) {
                    if (Integer.parseInt(stringNums[i + j]) < k) {
                        current++;
                    }
                }
                max = Math.max(max, current);
            }
            System.out.println(chuangKouLength - max);
        }
        sc.close();
    }


}
