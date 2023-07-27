package com.tw.heima.paiyipai.leetCode.lianxi;

import java.util.Scanner;

public class JuxingXiaojiaoMianji {

    /**
     * 矩形相交面积
     * （x,y,w,h）
     * 左上角坐标、右上角坐标、宽度、高度
     * <p>
     * 1 6 4 4
     * 3 5 3 3
     * 0 3 7 3
     * <p>
     * 每个矩形的位置信息：
     * （x,y）、  (x+w,y)
     * (x,y-h)、(x+w,y-h)
     * <p>
     * 相交矩形位置信息：
     * 左边界：三个矩形左边界的最大值
     * 右边界：三个矩形右边界的最小值
     * <p>
     * 长度 = 右边界-左边界
     * <p>
     * 上边界：三个矩形上边界的最小值
     * 下边界：三个矩形下边界的最大值
     * <p>
     * 宽度 = 上边界-下边界
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] nums = new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                nums[i][j] = sc.nextInt();
            }
        }
        int x1 = nums[0][0];
        int y1 = nums[0][1];
        int w1 = nums[0][2];
        int h1 = nums[0][3];

        int x2 = nums[1][0];
        int y2 = nums[1][1];
        int w2 = nums[1][2];
        int h2 = nums[1][3];

        int x3 = nums[2][0];
        int y3 = nums[2][1];
        int w3 = nums[2][2];
        int h3 = nums[2][3];

        int zuobianjie = Math.max(x1, Math.max(x2, x3));
        int youbinajie = Math.min(x1 + w1, Math.min(x2 + w2, x3 + w3));

        int changdu = youbinajie - zuobianjie;

        int shangbianjie = Math.min(y1, Math.min(y2, y3));
        int xiabianjie = Math.max(y1 - h1, Math.max(y2 - h2, y3 - h3));

        int gaodu = shangbianjie - xiabianjie;

        if(changdu<0 || gaodu<0){
            System.out.println(0);
        }else {
            System.out.println(changdu * gaodu);
        }
    }
}
