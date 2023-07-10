package com.tw.heima.paiyipai.zhenti.bihui69;


import java.util.Scanner;
import java.util.Stack;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-08 1:45 下午
 */
public class SearchEnemy {
    /**
     * 有一个大小是N*M的战场地图，被墙壁 ‘#’ 分隔成大小不同的区域，上下左右四个方向相邻的空地 ‘.’ 属于同一个区域，
     * 只有空地上可能存在敌人’E”，请求出地图上总共有多少区域里的敌人数小于K。
     * <p>
     * 输入描述：
     * 第一行输入为N,M,K；N表示地图的行数，M表示地图的列数， K表示目标敌人数量。N，M<=100
     * 第二行一个NxM大小的字符数组。
     * <p>
     * 输出描述：
     * 敌人数小于K的区域数量
     * <p>
     * 输入用例：
     * 3 5 2
     * ..#EE
     * E.#E.
     * ###..
     * <p>
     * 输出用例
     * 1
     * <p>
     * 首先，根据输入描述，我们可以得到地图的行数n、列数m和目标敌人数量k。然后，我们需要读取地图矩阵，将其存储在一个二维字符数组matrix中。
     * <p>
     * 接下来，我们需要初始化一个二维布尔数组visited，用于标记地图中的每个位置是否已经被访问过。初始化visited为false。
     * <p>
     * 然后，我们定义一个深度优先搜索函数dfs，用于计算以位置(i, j)为起点的区域中敌人的数量。在dfs函数中，我们首先将位置(i, j)标记为已访问，
     * 并根据该位置的值判断是否为敌人，如果是，则将计数器count加1。
     * <p>
     * 然后，我们使用一个栈来保存待访问的位置。在每一次循环中，我们从栈中取出一个位置(pos)，并遍历其上下左右四个相邻位置。
     * 如果相邻位置在地图范围内、未被访问过且不是墙壁，则将其标记为已访问，并根据其值判断是否为敌人，
     * 如果是，则将计数器count加1，并将该位置加入到栈中。最后，返回计数器count。
     * <p>
     * 整体思路是，遍历地图中的每个位置，如果该位置未被访问过且不是墙壁，则调用dfs函数计算以该位置为起点的区域中敌人的数量，如果该数量小于目标敌人数量k，则将区域数量加1。最后，输出区域数量。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        char[][] matrix = new char[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = scanner.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = row.charAt(j);
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || matrix[i][j] == '#') {
                    continue;
                }
                ans += dfs(i, j, matrix, visited, k) < k ? 1 : 0;
            }
        }

        System.out.println(ans);
    }

    public static int dfs(int i, int j, char[][] matrix, boolean[][] visited, int k) {
        int count = 0;

        visited[i][j] = true;

        if (matrix[i][j] == 'E') {
            count++;
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int x = pos[0];
            int y = pos[1];

            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && !visited[newX][newY] && matrix[newX][newY] != '#') {
                    visited[newX][newY] = true;

                    if (matrix[newX][newY] == 'E') {
                        count++;
                    }

                    stack.push(new int[]{newX, newY});
                }
            }
        }

        return count;
    }
}


