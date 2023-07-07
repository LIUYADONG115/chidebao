package com.tw.heima.paiyipai.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 6:49 下午
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 6, 3};

        // 使用自定义的降序比较器
        Arrays.sort(arr);

        // 打印排序后的数组
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
