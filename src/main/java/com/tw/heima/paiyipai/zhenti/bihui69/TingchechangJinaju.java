package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 6:50 下午
 */
public class TingchechangJinaju {
    /**
     * 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。
     * 车辆大小不一，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3）。
     * 统计停车场最少可以停多少辆车，返回具体的数目。
     * <p>
     * 输入描述：
     * 整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。
     * <p>
     * 输出描述：
     * 整型数字字符串，表示最少停车数目。
     * <p>
     * 输入：
     * 1，0，1
     * 输出
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
        // 输入停车场情况，1表示有车，0表示没车
        Scanner sc = new Scanner(System.in);
        String cars = sc.nextLine();

        // 去除输入中的逗号
        int pos = 0;
        while ((pos = cars.indexOf(",", pos)) != -1) {
            cars = cars.substring(0, pos) + cars.substring(pos + 1);
        }

        // 用x替换车位，便于计数
        pos = 0;
        while ((pos = cars.indexOf("111", pos)) != -1) {
            cars = cars.substring(0, pos) + "x" + cars.substring(pos + 3);
        }
        pos = 0;
        while ((pos = cars.indexOf("11", pos)) != -1) {
            cars = cars.substring(0, pos) + "x" + cars.substring(pos + 2);
        }
        pos = 0;
        while ((pos = cars.indexOf("1", pos)) != -1) {
            cars = cars.substring(0, pos) + "x" + cars.substring(pos + 1);
        }

        // 统计停车位数
        int carCount = 0;
        pos = 0;
        while ((pos = cars.indexOf("x", pos)) != -1) {
            carCount++;
            pos++;
        }

        // 根据车位长度调整停车数量
        int totalCount = carCount;
        pos = 0;
        while ((pos = cars.indexOf("xxx", pos)) != -1) {
            totalCount--;
            pos++;
        }
        pos = 0;
        while ((pos = cars.indexOf("xx", pos)) != -1) {
            totalCount--;
            pos++;
        }

        // 输出最少停车数量
        System.out.println(totalCount);
    }
}

