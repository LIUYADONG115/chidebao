package com.tw.heima.paiyipai.zhenti.morni.test4;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-03 10:03 下午
 */
public class NumberingSystem {
    /**
     * 现有一个编号系统。每一个编号由小写英文字母(a-z)和数字(0-9)组成，比如’aads000’, ‘dd001’。编号不能全为字母或数字，允许数字部分有前导0或全为0。
     * 给定至少需要的编号个数 M 和编号中字母长度 N，请计算编号中数字的最短长度。其
     * 中，0<M <=2^50-1,0<N<=5。
     *
     * 输入：
     * 两个非负整数M,N，用空格分隔。
     *
     * 输出：
     * 正整数，表示数字部分的最短长短
     *
     * 示例：
     * 输入：
     * 2599 1
     * 输出：
     * 2
     *
     * 我们知道每个编号由字母和数字组成，其中字母部分的长度为N，数字部分的长度为X。根据题意，我们需要找到最短的X，使得总共的编号个数不少于M。
     *
     * 观察规律可以发现，当数字部分的长度为X时，总共的编号个数为 36^N * 10^X，其中36代表26个字母加上10个数字的总数。为了满足总编号个数不少于M，我们可以通过不等式进行推导：
     *
     * 36^N * 10^X >= M
     *
     * 我们可以通过对X进行递增尝试的方式，找到满足不等式的最小整数X。具体算法如下：
     *
     * 初始化X为0。
     * 计算36^N * 10^X的值，并与M进行比较。
     * 如果计算结果小于M，则增加X的值，继续执行步骤2。
     * 如果计算结果大于等于M，则输出X的值作为最短的数字部分长度。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        int X = 0;
        long total = (long) Math.pow(36, N) * (long) Math.pow(10, X);

        while (total < M) {
            X++;
            total = (long) Math.pow(36, N) * (long) Math.pow(10, X);
        }

        System.out.println(X);
    }
}
