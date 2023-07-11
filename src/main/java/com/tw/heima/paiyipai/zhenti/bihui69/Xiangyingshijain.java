package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 6:56 下午
 */
public class Xiangyingshijain {
    /**
     * IGMP协议中，有一个字段称作最大响应时间(Max Response Time)，HOST收到查询报文，
     * 解析出MaxResponseTime字段后，需要在(0~MaxResponseTime)s )时间内选取随机时间回应一个响应报文，
     * 如果再随机时间内收到一个新的查询报文，则会根据两者时间的大小，选取小的一方刷新回应时间。
     * <p>
     * 最大响应时间有如下计算方式：
     * 当MaxRespCode < 128 ,MaxRespTime = MaxRespCode
     * 当MaxRespCode = 128 ,MaxRespTime = (mant | 0x10) << (exp + 3)
     * |0|123|4567|
     * |1|exp|mant|
     * 注: exp 最大响应时间的 高5~7位;mant 为最大响应时间的 低4位
     * <p>
     * 其中接收到的 MaxRespCode 最大值为255，以上出现所有字段均为无符号数。
     * <p>
     * 输入：第一行为查询报文个数 C，后续每行分别为HOST收到报文时间 T，以及最大响应字段 M，以空格分割。
     * 3
     * 0 20
     * 1 10
     * 8 20
     * 输出：HOST发送响应报文的时间。
     * 11
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numQueries = in.nextInt(); // 查询报文个数
        int[] arrivalTime = new int[numQueries]; // HOST收到报文时间
        int[] maxRespCode = new int[numQueries]; // 最大响应时间字段值

        for (int i = 0; i < numQueries; i++) {
            arrivalTime[i] = in.nextInt();
            maxRespCode[i] = in.nextInt();
        }

        int minResponseTime = Integer.MAX_VALUE; // HOST发送响应报文的时间
        for (int i = 0; i < numQueries; i++) {
            int maxRespTime = 0;
            if (maxRespCode[i] < 128) { // 当MaxRespCode < 128 ,MaxRespTime = MaxRespCode
                maxRespTime = maxRespCode[i];
            } else { // 当MaxRespCode >= 128 ,MaxRespTime = (mant | 0x10) << (exp + 3)
                int exp = (maxRespCode[i] & 0x70) >> 4; // exp 最大响应时间的 高5~7位
                int mant = maxRespCode[i] & 0x0F; // mant 为最大响应时间的 低4位
                maxRespTime = (mant | 0x10) << (exp + 3);
            }
            int responseTime = arrivalTime[i] + maxRespTime; // HOST发送响应报文的时间
            minResponseTime = Math.min(minResponseTime, responseTime); // 更新最小的 HOST发送响应报文的时间
        }
        System.out.println(minResponseTime);
    }
}

