package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Scanner;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 11:56 下午
 */
public class Xishujuzhen {
    /**
     * 如果矩阵中的许多系数都为零，那么该矩阵就是稀疏的。对稀疏现象有兴趣是因为它的开发可以带来巨大的计算节省，并且在许多大的实践中都会出现矩阵稀疏的问题。
     *
     * 给定一个矩阵，现在需要逐行和逐列地扫描矩阵，如果某一行或者某一列内，存在连续出现的0的个数超过了行宽或者列宽的一半 [W /2] (整除) ，则认为该行或者该列是稀疏的。
     *
     * 扫描给定的矩阵，输出稀疏的行数和列数。
     *
     * 输入：
     * 第一行输入为M和N，表示矩阵的大小M*N，0 ＜ M ≤ 100，0 ＜ N ≤ 100
     * 接下来M行输入为矩阵的成员，每行N个成员，矩阵成员都是有符号整数，范围-32,768到32,767
     *
     * 输出：
     * 输出两行，第一行表示稀疏行的个数，第二行表示稀疏列的个数
     *
     *
     * 输入：
     * 3 3
     * 1 0 0
     * 0 1 0
     * 0 0 1
     *
     * 输出：
     * 3
     * 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // 矩阵的行数
        int n = scanner.nextInt(); // 矩阵的列数

        Vector<Vector<Integer>> matrix = new Vector<>(m);
        for (int i = 0; i < m; i++) {
            matrix.add(new Vector<>(n));
            for (int j = 0; j < n; j++) {
                matrix.get(i).add(scanner.nextInt()); // 输入矩阵的元素
            }
        }

        Vector<Integer> row_zero_count = new Vector<>(m); // 每一行中0的个数
        Vector<Integer> col_zero_count = new Vector<>(n); // 每一列中0的个数
        IntStream.range(0, m).forEach(i -> row_zero_count.add(0));
        IntStream.range(0, n).forEach(i -> col_zero_count.add(0));

        // 统计每一行和每一列中0的个数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    row_zero_count.set(i, row_zero_count.get(i) + 1); // 统计第i行中0的个数
                    col_zero_count.set(j, col_zero_count.get(j) + 1); // 统计第j列中0的个数
                }
            }
        }

        // 统计稀疏行的个数
        int sparse_row_count = (int) row_zero_count.stream().filter(val -> val >= n / 2).count();
        // 统计稀疏列的个数
        int sparse_col_count = (int) col_zero_count.stream().filter(val -> val >= m / 2).count();

        System.out.println(sparse_row_count); // 输出稀疏行的个数
        System.out.println(sparse_col_count); // 输出稀疏列的个数
    }
}

