package com.tw.heima.paiyipai.zhenti.morni.test5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-04 11:08 下午
 */
public class StoreBox {
    /**
     * 有若干个箱子可供使用存放物品。给定若干存放需求，请计算每个需求是否可分配箱子，分配返回true，不能分配返回 false。分配规则如下：
     * 1） 分配的箱子容量要大于等于存放需求，存在满足需求的箱子就必须分配，优先分配箱子容量小的，箱子不可拆分，需求不可拆分；
     * 2） 先申请的需求先分配。
     *
     * 输入：
     * 第一行是箱子列表，每个箱子包含两个信息：箱子容量和箱子数量，用冒号分隔。多个箱子
     * 信息之间用逗号分隔。
     * 第二行是存放需求，需求个数<= 100000
     *
     * 输出：
     * 分配结果字符串，逗号分隔
     *
     * 示例：
     * 输入：
     * 64:2,128:1,32:4,1:128
     * 50,36,64,128,127
     * 输出：
     * true,true,true,false,false
     *
     * 1:128,128:1,64:2,32:4
     * 50,36,64,128,127
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String str1 = sc.nextLine();
            String[] boxs = str.split(",");
            int[][] box = new int[boxs.length][2];
            for (int i = 0; i < boxs.length; i++) {
                int boxSize = Integer.parseInt(boxs[i].split(":")[0]);
                int boxNum = Integer.parseInt(boxs[i].split(":")[1]);
                int[] boxInfo = {boxSize, boxNum};
                box[i] = boxInfo;
            }
            //[[1,128],[128,1],[64,2],[32,4]]
            System.out.println(box);
            Arrays.sort(box, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });
            //[[1,128],[32,4],[64,2],[128,1]]
            System.out.println(box);

            StringBuffer sb = new StringBuffer();
            //50,36,64,128,127
            String[] things = str1.split(",");
            for (String thing : things) {
                int thingSize = Integer.parseInt(thing);
                for (int i = 0; i < box.length; i++) {
                    if (box[i][0] >= thingSize && box[i][1] > 0) {
                        sb.append("true");
                        sb.append(",");
                        --box[i][1];
                        break;
                    }
                    if (i == box.length - 1) {
                        sb.append("false");
                        sb.append(",");
                    }
                }
            }
            String outPut = sb.toString();
            System.out.println(outPut.substring(0,outPut.length()-1));
        }
        sc.close();
    }
}
