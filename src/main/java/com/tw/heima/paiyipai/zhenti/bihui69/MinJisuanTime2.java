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
     *
     * 第一个参数为GPU一次执行最多的任务个数
     * 第二个参数为任务数据的长度
     * 第三个参数为任务数组
     *
     * 输出：执行完所有任务最少需要多少秒
     *
     * 3
     * 5
     * 1 2 3 4 5
     *
     * 输出：6
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

    public static void Test2() {
        Scanner scanner = new Scanner(System.in);
        int maxCount, tasksLen;
        Vector<Integer> tasks = new Vector<>();
        String line;
        int time = 0;
        int remain = 0;

        while (scanner.hasNextLine()) {
            tasks.clear();
            time = 0;
            remain = 0;

            maxCount = Integer.parseInt(scanner.nextLine());
            tasksLen = Integer.parseInt(scanner.nextLine());
            line = scanner.nextLine();
            Scanner ss = new Scanner(line);
            for (int i = 0; i < tasksLen; i++) {
                int task = ss.nextInt();
                tasks.add(task);
            }

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) + remain > maxCount) {
                    remain = tasks.get(i) + remain - maxCount;
                } else {
                    remain = 0;
                }
                time++;
            }

            while (remain > 0) {
                remain -= maxCount;
                time++;
            }

            System.out.println(time);
        }
    }
}
