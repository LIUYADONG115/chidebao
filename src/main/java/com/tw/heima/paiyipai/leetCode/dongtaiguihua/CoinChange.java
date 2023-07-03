package com.tw.heima.paiyipai.leetCode.dongtaiguihua;

import java.util.Arrays;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 9:49 下午
 */
public class CoinChange {
    public static void main(String[] args) {

        int[] coins = new int[]{2};
        int amount = 4;
        System.out.println(coinChange(coins, amount));
    }


    /**
     * 我们采用自下而上的方式进行思考。仍定义 F(i) 为组成金额 i 所需最少的硬币数量，
     * 假设在计算 F(i) 之前，我们已经计算出 F(0)−> F(i−1) 的答案。
     * 则 F(i) 对应的转移方程应为
     * F(i)= min F(i-C) + 1
     *
     * F(0) = 0
     *
     * F(1) = min(F(1-1),F(1-2),F(1-3)) + 1
     * =min(F(0)) + 1
     * =1
     *
     * F(2) = min(F(2-1), F(2-2), F(2-3)) +1
     * = min(F(1), F(0)) +1
     * =1
     *
     * F(3)=min(F(3−1),F(3−2),F(3−3))+1
     * =min(F(2),F(1),F(0))+1
     * =min(1,1,0)+1
     * =1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
