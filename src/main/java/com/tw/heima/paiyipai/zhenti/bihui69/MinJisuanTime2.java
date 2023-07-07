package com.tw.heima.paiyipai.zhenti.bihui69;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 10:52 下午
 */

import java.util.Scanner;
import java.util.Vector;

public class MinJisuanTime2 {
    /**
     * 为了充分发挥GPU[算力]，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务。
     * 假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int maxChuliNum = sc.nextInt();
            int N = sc.nextInt();
            int[] tasks = new int[N];
            for (int i = 0; i < N; i++) {
                tasks[i] = sc.nextInt();
            }

            int time = 0;
            int shengyu = 0;
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] + shengyu > maxChuliNum) {
                    time++;
                    shengyu = tasks[i] + shengyu - maxChuliNum;
                } else {
                    time++;
                    shengyu = 0;
                }
            }

            while (shengyu - maxChuliNum > 0) {
                time++;
                shengyu = shengyu - maxChuliNum;
            }
            System.out.println(time);
        }
    }
}
