package com.tw.heima.paiyipai.zhenti.bihui69;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 5:49 下午
 */
public class YasuoJieya {

    /**
     * 解压报文、压缩报文还原
     * 2[a]
     * 2[ha]liuyadong2[ha]
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String compressedStr = sc.nextLine();

        StringBuilder originalStr = new StringBuilder(); // 存储解压后的原始报文
        LinkedList<Integer> startIndices = new LinkedList<>(); // 存储方括号的起始索引位置
        LinkedList<Integer> repeatCounts = new LinkedList<>(); // 存储方括号内部的重复次数
        StringBuilder tmpRepeatCount = new StringBuilder(); // 临时存储方括号内部的重复次数

        for (int i = 0; i < compressedStr.length(); i++) {
            char c = compressedStr.charAt(i);

            if (c == '[') { // 遇到方括号的起始位置
                int repeatCount = Integer.parseInt(tmpRepeatCount.toString()); // 将临时存储的重复次数转换为整数
                repeatCounts.add(repeatCount); // 将重复次数存入列表
                tmpRepeatCount.setLength(0); // 清空临时存储的重复次数

                startIndices.add(originalStr.length()); // 将方括号的起始位置存入列表
            } else if (c == ']') { // 遇到方括号的结束位置
                int start = startIndices.removeLast(); // 获取方括号的起始位置
                int repeatCount = repeatCounts.removeLast(); // 获取方括号内部的重复次数
                String repeatedStr = originalStr.substring(start); // 获取方括号内部的字符串

                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < repeatCount; j++) {
                    tmp.append(repeatedStr); // 将方括号内部的字符串重复指定次数，并存入临时变量
                }

                originalStr.replace(start, originalStr.length(), tmp.toString()); // 将原始报文中方括号内部的字符串替换为重复后的字符串
            } else if (Character.isDigit(c)) { // 遇到数字字符
                tmpRepeatCount.append(c); // 将数字字符添加到临时存储的重复次数中
            } else { // 遇到字母字符
                originalStr.append(c); // 将字母字符添加到原始报文中
            }
        }

        System.out.println(originalStr.toString()); // 输出解压后的原始报文
    }
}
