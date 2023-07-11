package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 9:28 上午
 */
public class ZuixiaoZhongshuJuli {
    /**
     * 在树苗有限的情况下，要达到最佳效果，就要尽量散开种植，不同树苗之间的最小间距要尽量大。给你一个适合种情树木的点坐标和一个树苗的数量，请帮小明选择一个最佳的最小种植间距。
     * 例如，适合种植树木的位置分别为1,3,5,6,7,10,13 树苗数量是3，种植位置在1,7,13，树苗之间的间距都是6，均匀分开，就达到了散开种植的目的，最佳的最小种植间距是6
     *
     * 输入：
     * 第1行表示适合种树的坐标数量
     * 第2行是适合种树的坐标位置
     * 第3行是树苗的数量
     *
     * 输出：
     * 最佳的最小种植间距
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入适合种树的坐标数量
        int n = scanner.nextInt();

        // 输入适合种树的坐标位置
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = scanner.nextInt();
        }

        // 输入树苗的数量
        int m = scanner.nextInt();

        // 对坐标位置进行排序
        Arrays.sort(positions);

        // 最小间距的最小值、最大值
        int minDisMin = 1, minDisMax = positions[n - 1] - positions[0];

        // 最佳的最小种植间距
        int ans = 0;

        // 二分查找最佳的最小种植间距
        while (minDisMin <= minDisMax) {
            int mid = (minDisMin + minDisMax) >> 1;

            if (check(positions, m, mid)) {
                ans = mid;
                minDisMin = mid + 1;
            } else {
                minDisMax = mid - 1;
            }
        }

        // 输出最佳的最小种植间距
        System.out.println(ans);
    }

    /**
     * 检查某个最小间距下是否能种下 m 棵树
     *
     * @param positions 适合种树的坐标位置
     * @param m         树苗的数量
     * @param minDis    最小间距
     * @return 是否能种下 m 棵树
     */
    public static boolean check(int[] positions, int m, int minDis) {
        // 当前已种下的树苗数量
        int count = 1;

        // 当前种下的最后一棵树的坐标位置
        int curPos = positions[0];

        // 从第二个坐标位置开始遍历
        for (int i = 1; i < positions.length; i++) {
            // 如果当前坐标位置和上一棵树的距离大于等于最小间距，则可以种下一棵树
            if (positions[i] - curPos >= minDis) {
                count++;
                curPos = positions[i];
            }
        }

        // 如果种下的树苗数量大于等于要求，则返回 true
        return count >= m;
    }
}

