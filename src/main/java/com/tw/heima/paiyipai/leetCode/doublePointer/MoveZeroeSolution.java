package com.tw.heima.paiyipai.leetCode.doublePointer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-27 10:19 下午
 */
public class MoveZeroeSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 1, 3, 12};
        ascArray(nums);
        //sortArrayDescending(nums);
//        descArray();

        //quickSort(nums, 0, nums.length - 1);
        //System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * <p>
     * 输入: nums = [0, 2, 0, 1, 3, 12]
     * <p>
     * 输出: [2, 1, 3, 12, 0, 0]
     * <p>
     * i=0,j= 0
     * [0, 2, 0, 1, 3, 12]
     * i=1,位置1和位置0交换，并且j++变成1，j=1
     * [2, 0, 0, 1, 3, 12]
     * i=2,j=1
     * [2, 0, 0, 1, 3, 12]
     * i=3, 位置3和位置1交换，并且j++变成2, j=2
     * [2, 1, 0, 0, 3, 12]
     * i=4，位置4和位置2交换，并且j++变成3, j=3
     * [2, 1, 3, 0, 0, 12]
     * i=5，位置5和位置3交换，并且j++变成4, j=4
     * [2, 1, 3, 12, 0, 0]
     * <p>
     * 参考使用快速排序算法
     *
     * @param nums
     */
    public static void sortArrayDescending(int[] nums) {
        if (nums == null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
            System.out.println(Arrays.toString(nums));
        }
    }


    /**
     * 对数组进行升序处理
     *
     * @param nums
     */
    public static void ascArray(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 使用冒泡方式
     * 对数组进行升序处理
     */
    public static void descArray() {
        int[] nums = new int[]{5, 3, 4, 2, 1};
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    int tmp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 使用快速排序
     * 对数组进行升序处理
     */
    public static void quickSort(int[] arr, int low, int high) {
        int p, i, j, temp;

        if (low >= high) {
            return;
        }
        //p就是基准数,这里就是每个数组的第一个
        p = arr[low];
        i = low;
        j = high;
        while (i < j) {
            //右边当发现小于p的值时停止循环
            while (arr[j] >= p && i < j) {
                j--;
            }

            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于p的值时停止循环
            while (arr[i] <= p && i < j) {
                i++;
            }

            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = p;
        quickSort(arr, low, j - 1);  //对左边快排
        quickSort(arr, j + 1, high); //对右边快排
    }
}
