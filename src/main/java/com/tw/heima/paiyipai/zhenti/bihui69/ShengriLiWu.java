package com.tw.heima.paiyipai.zhenti.bihui69;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-07 4:01 下午
 */
public class ShengriLiWu {
    /**
     * 小牛的孩子生日快要到了，他打算给孩子买蛋糕和小礼物，蛋糕和小礼物各买一个，他的预算不超过x元。蛋糕cake和小礼物gift都有多种价位的可供选择。
     * 请返回共有多少种购买方案
     *
     * 第一行表示cake的单价，以逗号分隔
     * 第二行表示gift的单价，以逗号分隔
     * 第三行表示x预算
     *
     * 10,20,5
     * 5,5,2
     * 15
     *
     * 输出：6
     *
     * 思路：
     * 将cake的单价按照从小到大排序，使用Arrays.sort()方法。
     * 初始化变量totalSolutions为0，用于记录购买方案的总数。
     * 遍历小礼物的单价数组giftPrices，对于每个小礼物的价格giftPrice，执行以下步骤：
     *      如果预算budget小于等于礼物的价格giftPrice，则无法购买，跳过当前循环。
     *      计算最大的蛋糕价格maxCakePrice = budget - giftPrice。
     *      在蛋糕价格数组cakePrices中使用二分查找找到最后一个小于等于最大蛋糕价格maxCakePrice的位置lastIndex。调用searchLast()方法实现二分查找。
     *      如果找到了最后一个小于等于最大蛋糕价格的位置lastIndex，则方案数为该位置加1，将该值加到totalSolutions中。
     *      如果没有找到最后一个小于等于最大蛋糕价格的位置lastIndex，则方案数为插入位置的相反数，将插入位置取反后加到totalSolutions中。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入cake的单价，以逗号分隔
        int[] cakePrices = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        // 输入gift的单价，以逗号分隔
        int[] giftPrices = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        // 输入预算x
        int budget = Integer.parseInt(sc.nextLine());

        // 将cake的单价按照从小到大排序
        Arrays.sort(cakePrices);

        long totalSolutions = 0;
        for (int giftPrice : giftPrices) {
            // 如果预算小于等于礼物的价格，则无法购买
            if (budget <= giftPrice) continue;

            // 计算最大的蛋糕价格
            int maxCakePrice = budget - giftPrice;
            // 在蛋糕价格数组中查找最后一个小于等于最大蛋糕价格的位置
            int lastIndex = searchLast(cakePrices, maxCakePrice);

            if (lastIndex >= 0) {
                // 如果找到了最后一个小于等于最大蛋糕价格的位置，则方案数为该位置加1
                totalSolutions += lastIndex + 1;
            } else {
                // 如果没有找到最后一个小于等于最大蛋糕价格的位置，则方案数为插入位置的相反数
                lastIndex = -lastIndex;
                totalSolutions += lastIndex;
            }
        }

        System.out.println(totalSolutions);
    }

    // 二分查找蛋糕价格数组中最后一个小于等于目标价格的位置
    public static int searchLast(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            int midVal = arr[mid];

            if (midVal > target) {
                high = mid - 1;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                // 如果找到了目标价格，判断是否为最后一个相同的价格，且此时位于有边界
                if (mid == arr.length - 1 || arr[mid] != arr[mid + 1]) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        // 没有找到目标价格
        return -low;
    }
}
