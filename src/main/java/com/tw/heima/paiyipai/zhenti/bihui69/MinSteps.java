package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-06 10:03 下午
 */

public class MinSteps {
    /**
     * 求从坐标零点到坐标点n的最小步数，一次只能沿横坐标轴向左或向右移动 2 或 3。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(2);
        } else {
            Vector<Integer> dp = new Vector<>(n + 1);
            for (int i = 0; i <= n; i++) {
                dp.add(Integer.MAX_VALUE);
            }
            dp.set(0, 0);
            for (int i = 2; i <= n; i++) {
                if (i >= 2 && dp.get(i - 2) != Integer.MAX_VALUE) {
                    dp.set(i, Math.min(dp.get(i), dp.get(i - 2) + 1));
                }
                if (i >= 3 && dp.get(i - 3) != Integer.MAX_VALUE) {
                    dp.set(i, Math.min(dp.get(i), dp.get(i - 3) + 1));
                }
            }
            System.out.println(dp.get(n));
        }
        scanner.close();
    }


    public void getStps(int n) {
        if (n == 1) System.out.println(2);
        else if (n == 2) System.out.println(1);
        else if (n == 3) System.out.println(1);
        else {
            System.out.println((n - 4) / 3 + 2);
        }
    }

}



