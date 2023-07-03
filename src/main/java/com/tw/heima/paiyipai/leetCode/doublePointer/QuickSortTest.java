package com.tw.heima.paiyipai.leetCode.doublePointer;

import java.util.Arrays;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-28 9:21 下午
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 5, 4, 9, 2, 8};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        //expected nums [2, 1, 4, 9, 8]
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //benchmarkData就是基准数,这里就是每个数组的第一个
        int benchmarkData = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            //右边当发现小于基准数的值时停止循环
            while (arr[j] >= benchmarkData && i < j) {
                j--;
            }

            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于基准数的值时停止循环
            while (arr[i] <= benchmarkData && i < j) {
                i++;
            }

            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[left] = arr[i];//这里的arr[i]一定是停小于基准数的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = benchmarkData;
//        quickSort(arr, left, j - 1);  //对左边快排
//        quickSort(arr, j + 1, right); //对右边快排

    }

    public static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
