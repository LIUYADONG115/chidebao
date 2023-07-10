package com.tw.heima.paiyipai.leetCode.juzhen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 3:35 下午
 */
public class SpiralOrderSolutions {
    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        spiralOrder(matrix);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                list.add(matrix[top][column]);
            }

            for (int row = top + 1; row <= bottom; row++) {
                list.add(matrix[row][right]);
            }

            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    list.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    list.add(matrix[row][left]);
                }

            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }
}
