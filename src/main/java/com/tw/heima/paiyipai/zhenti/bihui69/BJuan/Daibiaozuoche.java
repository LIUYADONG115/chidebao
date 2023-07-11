package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:02 上午
 */
public class Daibiaozuoche {
    /**
     * 某组织举行会议，来了多个代表团同时到达，接待处只有一辆汽车，可以同时接待多个代表团，为了提高车辆利用率，请帮接待员计算可以坐满车的接待方案，输出方案数量。
     * 要求：
     * 一个团只能上一辆车，并且代表团人数 (代表团数量小于30，每个代表团人数小于30)小于汽车容量(汽车容量小于100)
     * 需要将车辆坐满
     *
     * 输入描述：
     * 第一行 代表团人数，英文逗号隔开，代表团数量小于30，每个代表团人数小于30
     * 第二行 汽车载客量，汽车容量小于100
     *
     * 5,4,2,3,2,4,9
     * 10
     *
     * 输出描述：
     * 坐满汽车的方案数量
     *
     * 4
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取代表团人数
        int[] groups = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        // 读取汽车载客量
        int capacity = Integer.parseInt(sc.nextLine());

        // 初始化动态规划数组，dp[i]表示载客量为i时的方案数
        int[] dp = new int[capacity + 1];
        dp[0] = 1; // 载客量为0时，方案数为1（不接待任何代表团）

        // 动态规划转移
        for (int group : groups) {
            for (int j = capacity; j >= group; j--) { // 从后往前遍历，避免重复计算
                dp[j] += dp[j - group]; // 转移方程：dp[j] += dp[j - group]，表示加上接待当前代表团后的方案数
            }
        }

        // 输出结果
        if (dp[capacity] == 0) { // 无解
            System.out.println(0);
        } else { // 有解
            System.out.println(dp[capacity]);
        }
    }

    /*
    题目要求计算可以坐满车的接待方案，输出方案数量。我们可以采用动态规划的思想来解决这个问题。
    首先，我们需要明确状态和状态转移方程。
    状态：我们可以用 dp[i] 表示载客量为 i 时的方案数。
    状态转移方程：假设当前有一个代表团，人数为 group，我们可以有两种选择：
        不接待这个代表团，此时方案数为 dp[i]；
        接待这个代表团，此时方案数为 dp[i - group]。
    因此，我们可以得到状态转移方程：dp[i] = dp[i] + dp[i - group]
    其中，dp[i] 表示不接待当前代表团时的方案数，dp[i - group] 表示接待当前代表团后的方案数。
    接下来，我们需要考虑如何初始化和边界情况。
    初始化：当载客量为 0 时，方案数为 1（不接待任何代表团）。
    边界情况：当载客量小于当前代表团人数时，无法接待该代表团，方案数不变。
    最后，我们需要遍历所有代表团，将状态转移方程应用到每个代表团上，最终得到载客量为汽车载客量时的方案数。
     */
}
