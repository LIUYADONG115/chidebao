package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 11:59 下午
 */
public class Jieyuebeifen {
    /**
     * 有若干个文件，使用刻录光盘的方式进行备份，假设每张光盘的容量是500MB，求使用光盘最少的文件分布方式
     * 所有文件的大小都是整数的MB，且不超过500MB；文件不能分割、分卷打包
     *
     * 输入描述：
     * 一组文件大小的数据
     *
     * 输出描述：
     * 使用光盘数量
     *
     * 输入：
     * 100,500,300,200,400
     *
     * 输出：
     * 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读入文件大小数据
        int[] fileSizes = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        // 对文件大小进行排序
        Arrays.sort(fileSizes);
        // 最少需要的光盘数量的范围
        int min = 0;
        int max = fileSizes.length + 1;
        while (min < max) {
            // 二分查找
            int mid = (min + max) / 2;
            // 初始化光盘大小数组
            int[] discs = new int[mid];
            Arrays.fill(discs, 500);
            boolean flag = true;
            // 从大到小遍历文件大小
            for (int i = fileSizes.length - 1; i >= 0; i--) {
                int fileSize = fileSizes[i];
                // 对光盘大小数组进行排序
                Arrays.sort(discs);
                // 将文件存储到剩余空间最大的光盘中
                if (discs[mid - 1] >= fileSize) {
                    discs[mid - 1] -= fileSize;
                } else {
                    // 剩余空间最大的光盘都无法存储该文件，需要增加光盘数量
                    flag = false;
                    break;
                }
            }
            if (flag) {
                // 光盘数量可以更少，继续缩小范围
                max = mid;
            } else {
                // 光盘数量需要增加，扩大范围
                min = mid + 1;
            }
        }
        // 输出最少需要的光盘数量
        System.out.println(min);
    }
}

