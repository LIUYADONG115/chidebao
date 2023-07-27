package com.tw.heima.paiyipai.leetCode.lianxi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class BigDecimalTest {
    /**
     * BigDecimal对象
     *  加法（add）
     *  减法（subtract）：
     *  乘法（multiply）：
     *  除法（divide）：
     *  取余（remainder）：
     *
     * 设置小数位数
     * BigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
     *
     *
     * 舍入规则
     * ROUND_UP: 向远离零的方向舍入（即向正无穷方向舍入）
     * ROUND_DOWN: 向接近零的方向舍入（即向负无穷方向舍入）。
     * ROUND_CEILING: 向正无穷方向舍入（向上舍入）。
     * ROUND_FLOOR: 向负无穷方向舍入（向下舍入）。
     * ROUND_HALF_UP: 向最近的整数舍入，如果与两个相邻整数的距离相等，则向上舍入。
     * ROUND_HALF_DOWN: 向最近的整数舍入，如果与两个相邻整数的距离相等，则向下舍入。
     * ROUND_HALF_EVEN: 向最近的整数舍入，如果与两个相邻整数的距离相等，则向偶数方向舍入。
     * ROUND_UNNECESSARY: 禁止舍入操作，如果存在非零小数部分，则抛出异常。
     *
     *
     * @param totalAmount
     * @param totalNum
     * @return
     */
    public static List<BigDecimal> generateRedPackets(BigDecimal totalAmount, int totalNum) {
        List<BigDecimal> redPackets = new ArrayList<>();
        Random random = new Random();

        // 计算每个红包的最大金额
        BigDecimal maxAmount = totalAmount.divide(BigDecimal.valueOf(totalNum)).multiply(BigDecimal.valueOf(2));
//        double maxAmount = totalAmount / totalNum * 2;

        for (int i = 0; i < totalNum; i++) {
            // 随机生成一个金额，保留小数点后两位
            BigDecimal amount = maxAmount.multiply(BigDecimal.valueOf(random.nextDouble()));
            amount = amount.setScale(2,BigDecimal.ROUND_DOWN);//保留两位小数
            //amount = Math.floor(amount * 100) / 100; // 保留两位小数

            // 如果是最后一个红包，直接将剩余的金额作为最后一个红包的金额
            if (i == totalNum - 1) {
                redPackets.add(totalAmount);
            } else {
                // 将红包金额加入列表，并从总金额中扣除
                redPackets.add(amount);
                totalAmount = totalAmount.subtract(amount);
            }
        }

        return redPackets;
    }

    public static void main(String[] args) {
        BigDecimal totalAmount = BigDecimal.valueOf(100.0);
        int totalNum = 10;

        List<BigDecimal> redPackets = generateRedPackets(totalAmount, totalNum);
        System.out.println(redPackets);

        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < redPackets.size(); i++) {
            BigDecimal current = redPackets.get(i);
            sum = sum.add(current);
            System.out.println(sum);
        }
    }
}
