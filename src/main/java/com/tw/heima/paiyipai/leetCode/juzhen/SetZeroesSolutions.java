package com.tw.heima.paiyipai.leetCode.juzhen;

import org.bouncycastle.pqc.math.linearalgebra.Matrix;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 2:35 下午
 */
public class SetZeroesSolutions {
    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);
        Arrays.stream(matrix).forEach(it-> System.out.println(Arrays.toString(it)));
       // System.out.println(Arrays.toString(matrix));
    }

    public static void setZeroes(int[][] matrix) {
        Set<Integer> row_zero = new LinkedHashSet<>();
        Set<Integer> col_zero = new LinkedHashSet<>();
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (matrix[i][j] == 0) {
                    row_zero.add(i);
                    col_zero.add(j);
                }
            }
        }

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if(row_zero.contains(i) || col_zero.contains(j)){
                    matrix[i][j] =0;
                }
            }
        }
    }
}
