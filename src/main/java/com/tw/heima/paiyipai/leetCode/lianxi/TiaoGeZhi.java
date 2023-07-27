package com.tw.heima.paiyipai.leetCode.lianxi;

import java.util.Arrays;
import java.util.Scanner;

public class TiaoGeZhi {
    /**
     * 跳格子游戏
     * 可以从人一格子开始跳，但不能连续跳，也不能回头跳。计算最高分数
     *
     * 1 2 3 1
     *
     * 4
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        // 1 2 3 1
        int[] numbers =Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = numbers.length;

        //动态规划，记录每个位置的最高分
        int[] dp =new int[n];
        dp[0] = numbers[0];
        if(n>=2){
            dp[1] = Math.max(numbers[0],numbers[1]);
        }

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+numbers[i]);
        }

        System.out.println(dp[n-1]);

    }
}
