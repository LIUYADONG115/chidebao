package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:08 上午
 */
public class Zuidajiazhikuashi {
    // 地图矩阵
    static int[][] map;

    // 上下左右，四个方向的偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 给你一个由 ‘0’ (空地)、‘1’ (银矿)、‘2’(金矿) 组成的的地图，矿堆只能由上下左右相邻的金矿或银矿连接形成。超出地图范围可以认为是空地。
     * 假设银矿价值1，金矿价值2 ，请你找出地图中最大价值的矿堆并输出该矿堆的价值。
     *
     * 输入：
     * 22220
     * 00000
     * 00000
     * 11111
     *
     * 输出：
     * 8
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读入地图信息
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        // 构建地图矩阵
        int rows = lines.size();
        int cols = lines.get(0).length();
        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 记录最大矿堆价值
        int maxVal = 0;

        // 遍历地图矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果点(i,j)没有被访问过，且点(i,j)上有矿，则进入深搜
                if (map[i][j] > 0) {
                    LinkedList<int[]> stack = new LinkedList<>();
                    stack.add(new int[]{i, j});

                    int sum = 0;

                    while (!stack.isEmpty()) {
                        int[] pos = stack.removeLast();
                        int x = pos[0], y = pos[1];

                        sum += map[x][y];
                        map[x][y] = 0;

                        // 遍历四个方向
                        for (int[] offset : offsets) {
                            int newX = x + offset[0];
                            int newY = y + offset[1];

                            // 如果新位置在地图范围内，且有矿，则加入栈中
                            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && map[newX][newY] > 0) {
                                stack.add(new int[]{newX, newY});
                            }
                        }
                    }

                    // 更新最大矿堆价值
                    maxVal = Math.max(maxVal, sum);
                }
            }
        }

        System.out.println(maxVal);
    }
}

