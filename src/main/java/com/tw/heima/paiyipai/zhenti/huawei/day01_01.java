package com.tw.heima.paiyipai.zhenti.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-21 9:26 下午
 */
public class day01_01 {
    /**
     * 给你若干个区间，每个区间包含开始点和结束点。一段数轴上如果被0个区间覆盖，则对答案的贡献是1，如果被1个区间覆盖，则对答案的贡献是3，如果有多个区间覆盖，则对答案的贡献是4。计算从第一个区间的开始点到最后一个区间结束点的答案。
     *
     * 输入
     * 第一行的数宇表示一共有多少个区间。
     * 后续每行包含由空格分割的两个整数，用于确定每一个区间的开始和结束点，左闭右闭。
     * 区间范围：[0,1000000]。
     * 区间数量：[0,10000]
     *
     * 输出
     * 一个整数，代表答案。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numIntervals = scanner.nextInt();  // 区间个数

        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < numIntervals; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            intervals.add(new int[]{start, end});
        }

        int start = intervals.get(0)[0];  // 第一个区间的开始点
        int end = intervals.get(numIntervals - 1)[1];  // 最后一个区间的结束点

        int[] counter = new int[end - start + 1];  // 计数器数组，记录每个点被区间覆盖的次数

        for (int[] interval : intervals) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                counter[i - start]++;
            }
        }

        int contribution = 0;  // 总贡献值
        for (int count : counter) {
            if (count == 0) {
                contribution += 1;
            } else if (count == 1) {
                contribution += 3;
            } else {
                contribution += 4;
            }
        }

        System.out.println(contribution);
    }
}
