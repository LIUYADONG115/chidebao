package com.tw.heima.paiyipai.zhenti.morni.test6;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 6:34 下午
 */
public class MNmigong {
    static int row; // 行数
    static int col; // 列数
    static int wallNum; // 墙的数量
    static int[][] walls; // 墙的位置
    static int[][] matrix; // 迷宫矩阵

    /**
     * 假设一个大小为 MxN的方格迷宫(O<M,N<=1000)，每个方格可以用(x,y)描述。
     * 入口在(0,0)，出口在(M-1,N-1)。用例保证挑战者可以从入口走到出口。
     * 迷宫中有K个墙壁(O<=K<M*N)，且挑战者只能向右或向上前进，因此在迷宫中存在一些挑战者不可达的位置，记为 P，也存在一些挑战者一旦到达就无法走到出口的位置，记为Q。
     * 给定迷宫大小和墙壁位置，请计算P和Q分别有多少个。
     *
     * 输入：
     * 第一行是M N,空格分隔
     * 第二行是K
     * 之后K行是墙壁坐标，每行坐标均以空格分隔
     *
     * 输出：
     * 两个整数，空格分隔，结尾不带回车换行
     *
     * 示例：
     * 输入：
     * 6 4
     * 5
     * 0 2
     * 1 2
     * 4 1
     * 5 1
     * 输出：
     * 2 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        row = sc.nextInt();
        col = sc.nextInt();
        wallNum = sc.nextInt();

        walls = new int[wallNum][2];
        for (int i = 0; i < wallNum; i++) {
            walls[i][0] = sc.nextInt();
            walls[i][1] = sc.nextInt();
        }

        matrix = new int[row][col];

        for (int[] wall : walls) {
            int i = wall[0];
            int j = wall[1];
            matrix[i][j] = 1; // 标记墙
        }

        matrix[row - 1][col - 1] = 2; // 标记出口

        dfs(0, 0); // 从入口开始搜索

        int trapNum = 0; // 陷阱方格数量
        int unreachableNum = 0; // 不可达方格数量

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    unreachableNum++;
                } else if (matrix[i][j] == -1) {
                    trapNum++;
                }
            }
        }

        System.out.println(trapNum + " " + unreachableNum);
    }

    public static boolean dfs(int x, int y) {
        if (x >= row || y >= col || matrix[x][y] == 1 || matrix[x][y] == -1) {
            // 如果越界、是墙或陷阱，返回 false
            return false;
        }

        if (matrix[x][y] == 2) {
            // 如果到达出口，返回 true
            return true;
        }

        boolean east = dfs(x + 1, y); // 搜索东边的方格
        boolean north = dfs(x, y + 1); // 搜索北边的方格

        if (east || north) {
            matrix[x][y] = 2; // 如果能到达出口，标记为可达
        } else {
            matrix[x][y] = -1; // 如果不能到达出口，标记为陷阱
        }

        return matrix[x][y] == 2; // 返回当前方格是否可达
    }
}