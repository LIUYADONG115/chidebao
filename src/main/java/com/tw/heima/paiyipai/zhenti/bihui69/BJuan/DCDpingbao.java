package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 9:31 上午
 */
public class DCDpingbao {

    /**
     * DVD机在视频输出时，为了保护电视显像管，在待机状态会显示“屏保动画”，如下图所示，DVD Logo在屏幕内来回运动，碰到边缘会反弹。
     * 请根据如下要求，实现屏保Logo坐标的计算算法。
     * 1：屏幕是一个800*600像素的矩形，规定屏幕的左上角点坐标原点，沿横边向右方向为X轴，沿竖边向下方向为Y轴
     * 2：Logo是一个50*25像素的矩形，初始状态下，左上角点坐标记做(x，y)，它在X和Y方向上均以1像素/秒的速度开始运动
     * 3：遇到屏幕四个边缘后，会发生镜面反弹，即以45°碰撞边缘，再改变方向以45°弹出
     * 4：当Logo和四个角碰撞时，两个边缘同时反弹的效果是Logo会原路返回
     *
     * 输入描述：
     * x y t
     * 第一个数字表示Logo左上角点的初始X坐标；
     * 第二个数字表示Logo左上角点的初始Y坐标；
     * 第三个数字表示时间 t，题目要求即求 t 秒后Logo左上角点的位置。
     *
     * 输出2个数字，以空格分隔:
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x0 = sc.nextInt(); // Logo左上角点的初始X坐标
        int y0 = sc.nextInt(); // Logo左上角点的初始Y坐标
        int t = sc.nextInt(); // 时间t

        int x = x0 + t; // Logo左上角点的X坐标
        int y = y0 + t; // Logo左上角点的Y坐标

        while (y + 25 > 600 || y < 0 || x + 50 > 800 || x < 0) {
            if (y + 25 > 600) {
                y = 1150 - y;
            }

            if (x + 50 > 800) {
                x = 1500 - x;
            }

            if (y < 0) {
                y = -y;
            }

            if (x < 0) {
                x = -x;
            }
        }

        System.out.println(x + " " + y);
    }
}
