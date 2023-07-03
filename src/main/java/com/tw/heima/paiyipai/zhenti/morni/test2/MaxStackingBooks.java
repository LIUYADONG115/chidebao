package com.tw.heima.paiyipai.zhenti.morni.test2;

import java.util.Arrays;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-02 4:53 下午
 */

public class MaxStackingBooks {
    /**
     * 课本的长宽都是整数，分别记为L，W。如果课本X的长和宽都比课本Y的长和宽大，则可以将Y叠放在X 上面。给定一组课本，叠放时不可旋转，请计算最多可叠放的课本数。
     *
     * 输入：
     * 二维数组形式的字符串，中间无空格。
     *
     * 输出：
     * 一个整数，表示最大可叠放课本数。
     *
     * 示例 1:
     * 输入：
     * [[13,12],[4,3], [6,7], [2,3], [23,21]]
     * 输出：
     * 4
     * @param args
     */
    public static void main(String[] args) {
        //int[][] books = {{23, 12}, {4, 3}, {6, 7}, {2, 3}, {23, 8}};
        int[][] books = {{13, 12}, {4, 3}, {6, 7}, {2, 3}, {23, 21}};
        //  [2,3] [4,3] [6,7] [23,8] [23,12]

        int result = maxStackingBooks(books);
        System.out.println(result);
    }

    // [13,12],[4,3], [6,7], [2,3], [23,21]
    // [2,3],[4,3],[6,7],[13,12], [23,21]


    /**
     * 排序＋动态规划。首先对二维数組根据第一个元素进行排序。定义一个dp数组，dp[i]表示叠放前i本课本的最大可叠放次数。
     *
     * 更新dp[i]的方法：依次遍历前i本课本，当当前遍历的课本j的宽比课本i的宽小时，说明j可以叠放在i上面，用最大的dp[j]+1 更新 dp[i]。dp数组的最大值即为最终结果。
     * @param books
     * @return
     */
    private static int maxStackingBooks(int[][] books) {
        // 按照长和宽进行排序
        Arrays.sort(books, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        System.out.println(Arrays.toString(books));

        int n = books.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxStackingHeight = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (books[i][0] > books[j][0] && books[i][1] > books[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxStackingHeight = Math.max(maxStackingHeight, dp[i]);
        }

        return maxStackingHeight;
    }
}

