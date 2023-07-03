package com.tw.heima.paiyipai.zhenti.huawei;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-21 9:42 下午
 */
import java.util.*;

public class day01_02 {
    private static List<Integer>[] adjacencyList; // 邻接表
    private static List<Integer> obstaclePoints; // 障碍点集合
    private static List<Integer> shortestPath; // 最短路径

    /**
     * 树上逃离
     * 给一棵树，节点编号
     * 0～n,n<10000,树根为编号为0的节点，给一些障碍点，表示这些点无法通过，问是否可以从树根走到叶子结点，如果可以，请输出字典序最小的路径。
     *
     * 输入
     * 第一行一个数组n，表示n个点，一个数字m，表示m条边。
     * 接下来m行，每行输入x，y，表示点x和点y之间有边。
     * 接下来输入一个数字t，接下来t行每行输入一个数字x，表示编号为x的点无法通过。
     *
     * 输出
     * 输出最短路径，如果有多条则输出字典序最小的路径
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 点的个数
        int m = scanner.nextInt(); // 边的条数

        // 初始化邻接表
        adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // 构建邻接表
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            adjacencyList[x].add(y);
        }

        int t = scanner.nextInt(); // 障碍点个数

        // 读取障碍点
        obstaclePoints = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int x = scanner.nextInt();
            obstaclePoints.add(x);
        }

        // 初始化最短路径
        shortestPath = new ArrayList<>();

        // DFS遍历树
        dfs(0, new ArrayList<>());

        // 输出最短路径
        for (int i = 0; i < shortestPath.size(); i++) {
            System.out.print(shortestPath.get(i));
            if (i < shortestPath.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

    private static void dfs(int node, List<Integer> currentPath) {
        // 添加当前节点到当前路径
        currentPath.add(node);

        // 如果当前节点是叶子节点，则判断是否为最短路径
        if (adjacencyList[node].size() == 0) {
            if (shortestPath.isEmpty() || isSmaller(currentPath, shortestPath)) {
                shortestPath = new ArrayList<>(currentPath);
            }
        }

        // 遍历当前节点的邻居节点
        for (int neighbor : adjacencyList[node]) {
            // 如果邻居节点不是障碍点，则继续DFS
            if (!obstaclePoints.contains(neighbor)) {
                dfs(neighbor, currentPath);
            }
        }

        // 回溯，将当前节点从路径中移除
        currentPath.remove(currentPath.size() - 1);
    }

    // 判断路径a是否字典序小于路径b
    private static boolean isSmaller(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());
        for (int i = 0; i < size; i++) {
            if (a.get(i) < b.get(i)) {
                return true;
            } else if (a.get(i) > b.get(i)) {
                return false;
            }
        }
        return a.size() < b.size();
    }
}
