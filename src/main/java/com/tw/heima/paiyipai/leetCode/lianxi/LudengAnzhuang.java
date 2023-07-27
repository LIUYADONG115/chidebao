package com.tw.heima.paiyipai.leetCode.lianxi;

import java.util.Scanner;

public class LudengAnzhuang {

    /**
     * 路灯照明
     * N个路灯，从位置0开始安装，路灯之间距离是100。
     * 每个灯有自己的照明半径，计算第一个路灯到最后一个路灯，无法照明的区间长度之和
     * <p>
     * 4
     * 50 70 20 70
     * <p>
     * 路灯1: 覆盖0-50
     * 路灯2: 覆盖30-170
     * 路灯3: 覆盖180-220
     * 路灯4: 覆盖230-370
     * <p>
     * 没有覆盖的面积[170-180]，[220-230]
     * 总长度：20
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            int banjing = sc.nextInt();
            int zuobian = Math.max(i * 100 - banjing, 0);
            int youbian = banjing + i * 100;
            nums[i][0] = zuobian;
            nums[i][1] = youbian;
        }
        int changdu = 0;
        int end = nums[0][1];
        for (int i = 1; i < n; i++) {
            if (nums[i][0] > end) {
                changdu += nums[i][0]-end;
                end = nums[i][1];
            }else {
                end = Math.max(end,nums[i][1]);
            }
        }
        System.out.println(changdu);
    }
}
