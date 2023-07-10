package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-08 2:29 下午
 */
public class SuperStrstr {
    /**
     * 现要求实现一个strstr的增强函数，可以使用带可选段的字符串来模糊查询，与strstr一样返回首次查找到的字符串位置。
     * 可选段使用“[]”标识，表示该位置是可选段中任意一个字符即可满足匹配条件。比如“a[bc]”表示可以匹配“ab”或“ac”。
     * 注意目标字符串中可选段可能出现多次。
     * 
     * 输出：与strstr函数不同，返回的是源字符串中，匹配子字符串相对于源字符串地址的偏移（从0开始算），如果没有匹配返回-1。
     *
     * abeabc
     * b[cd]
     *
     * 1
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String source = sc.nextLine(); // 输入源字符串
        String target = sc.nextLine(); // 输入目标字符串


        Matcher matcher = Pattern.compile(target).matcher(source); // 使用正则表达式匹配

        if (matcher.find()) {
            System.out.println(source.indexOf(matcher.group())); // 输出匹配子字符串相对于源字符串地址的偏移
        } else {
            System.out.println(-1); // 如果没有匹配则输出-1
        }
    }
}

