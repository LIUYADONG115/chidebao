package com.tw.heima.paiyipai.zhenti.morni.test4;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-03 11:05 下午
 */
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parse input
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[][] blockedCells = new int[K][2];
        for (int i = 0; i < K; i++) {
            blockedCells[i][0] = scanner.nextInt();
            blockedCells[i][1] = scanner.nextInt();
        }

        // Create maze matrix
        int[][] maze = new int[M + 1][N + 1];
        for (int i = 0; i < K; i++) {
            maze[blockedCells[i][0]][blockedCells[i][1]] = -1;
        }

        // Initialize first row and first column
        for (int i = 1; i <= M; i++) {
            if (maze[i][1] != -1) {
                maze[i][1] = 1;
            }
        }
        for (int j = 1; j <= N; j++) {
            if (maze[1][j] != -1) {
                maze[1][j] = 1;
            }
        }

        // Calculate number of ways to reach each cell
        for (int i = 2; i <= M; i++) {
            for (int j = 2; j <= N; j++) {
                if (maze[i][j] != -1) {
                    if (maze[i - 1][j] != -1 && maze[i][j - 1] != -1) {
                        maze[i][j] = maze[i - 1][j] + maze[i][j - 1];
                    } else if (maze[i - 1][j] != -1) {
                        maze[i][j] = maze[i - 1][j];
                    } else if (maze[i][j - 1] != -1) {
                        maze[i][j] = maze[i][j - 1];
                    } else {
                        maze[i][j] = -1;
                    }
                }
            }
        }

        // Calculate P and Q
        int P = 0;
        int Q = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] == -1) {
                    P++;
                }
            }
        }
        Q = M * N - maze[M][N];

        // Output result
        System.out.println(P + " " + Q);
    }
}

