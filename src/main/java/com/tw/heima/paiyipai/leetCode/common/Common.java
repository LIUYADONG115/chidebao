package com.tw.heima.paiyipai.leetCode.common;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 4:11 下午
 */
public class Common {
    public static void main(String[] args) {
        PatternLean5();
    }

    public static void ArrayLearn() {
        int[] intsOld = new int[]{1, 2, 3, 0, 4};
        int[] intsNew = Arrays.stream(intsOld).filter(it -> it != 0).toArray();
        Arrays.toString(intsNew);
    }

    public static void copyOfArray(String[] args) {
        int[] a = new int[]{1,2};
        Map<String,int[]> map =new HashMap<>();
        map.put("a",a);
        int[] result = map.get("a");
        int[] newArray = Arrays.copyOf(result, result.length+2);
        System.out.println(Arrays.toString(newArray));
        newArray[newArray.length-2] = 3;
        newArray[newArray.length-1] = 4;
        System.out.println(Arrays.toString(newArray));
    }

    public static void RegexLearn() {
        String str = "12ab34df56";
        String[] strs = str.split("\\d+");
        System.out.println(Arrays.toString(strs));

        // 去除空字符串 方法1
        Object[] objects = Arrays.stream(strs).filter(it -> !it.isEmpty()).toArray();
        System.out.println(Arrays.toString(objects));

        //去除空字符串 方法2
        //List<String> list = Arrays.asList(strs);
        List<String> list = new ArrayList<>(Arrays.asList(strs));
        list.removeIf(String::isEmpty);
        String[] sts2 = list.toArray(new String[0]);
        System.out.println(Arrays.toString(sts2));
    }

    public static void RegexLearn2() {
        String s1 = "foooofg";
        //[f, fg]
        String[] strs1 = s1.split("o+");
        //[f, , , , fg]
        String[] strs2 = s1.split("o+?");
        System.out.println(Arrays.toString(strs1));

        String s2 = "windows 2000";
    }


    //使用正则表达式提取数字和字符串
    public static void PatternLean1() {
        String record = "20CNY53fen";

        // 匹配数字部分
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(record);
        int amount = 0;
        while (matcher.find()) {
            amount = Integer.parseInt(matcher.group());
            System.out.println("Amount: " + amount);
        }

        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(record);
        while (matcher.find()) {
            System.out.println("Unit: " + matcher.group());
        }
    }

    //使用正则表达式提取日期
    public static void PatternLean2() {
        String input = "Today is 2023-06-20, tomorrow is 2023-06-21.";
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            String str = matcher.group();
            System.out.println(str);
        }
    }

    //使用正则表达式提取url
    public static void PatternLean3() {
        String input = "Visit my website at https://www.example.com. For more information, check out http://www.example.org.";
        String regex = "\\bhttps?://\\S+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            String str = matcher.group();
            System.out.println(str);
        }
    }

    //使用正则表达式格式化文本，将日期格式从"YYYY-MM-DD"转换为"DD/MM/YYYY"：
    public static void PatternLean4() {
        String input = "Today is 2022-06-30.";
        String regex = "(\\d{4})-(\\d{2})-(\\d{2})";
        String outPut = input.replaceAll(regex, "$3/$2/$1");
        System.out.println(outPut);
    }


    //使用正则表达式提取特定短语的例子,\\b 匹配一个字边界，即字与空格间的位置。
    public static void PatternLean5() {
        String input = "The quick brown fox jumps over the lazy dog.";
        // 定义要匹配的单词或短语
        String word = "quick";

        // 构建匹配单词或短语的正则表达式模式
        String regex = "\\b" + Pattern.quote(word) + "\\b";

        String[] outPut = input.split(regex);

        //[The ,  brown fox jumps over the lazy dog.]
        System.out.println(Arrays.toString(outPut));
    }
}
