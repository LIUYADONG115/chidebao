package com.tw.heima.paiyipai.zhenti.morni.test6;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 10:22 下午
 */
public class HotPot {
    /**
     * 吃火锅时，每种食材要煮不同时间变得刚好合适吃，由于人的能力有限，每次只能捞一个，每次捞菜后至少要过K秒才能再捞。给定 N 个菜的下菜时间和最佳时间。
     * 请计算最多能吃到多少刚好合适的菜。
     * 其中, 1 < N,K < 1000, 1 < P,Q<1000
     * <p>
     * 输入：
     * 第一行是两个整数 N K，空格分隔。
     * 之后 N行，每行两个整数P Q，空格分隔。代表第P秒下的菜过 Q秒刚好合适吃。
     * <p>
     * 输出：
     * 一个整数，表示最多吃到的刚好合适的菜的数量。
     * <p>
     * 示例：。
     * 输入：
     * 3 1
     * 1 2
     * 2 1
     * 3 1
     * 输出：
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
//        int[][] food = new int[M][2];
//        for (int i = 0; i < M; i++) {
//            food[i][0] = sc.nextInt();
//            food[i][1] = sc.nextInt();
//        }
//        System.out.println(food);

        List<Integer> yinggaichi = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int xiacai = sc.nextInt();
            int zuijia = sc.nextInt();
            int totalTime = xiacai + zuijia;
            yinggaichi.add(totalTime);
        }

        Collections.sort(yinggaichi);

        System.out.println(yinggaichi);

        //第一个菜时间最少，肯定要吃
        int count = 1;
        int lastChicaiTime = yinggaichi.get(0);
        for (int i = 1; i < M; i++) {
            int heshiTime = yinggaichi.get(i);
            if(heshiTime >= lastChicaiTime+N){
                count++;
                lastChicaiTime = heshiTime;
            }
        }
        System.out.println(count);
    }
}
