package com.tw.heima.paiyipai.zhenti.morni.test1;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-21 9:25 下午
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyConversion {
    /**
     * 假设货币转换关系：
     * 100CNY=1825JPY=123HKD=14EUR=12GBP,
     * 1CNY=100fen, 1HKD=100cents, 1JPY=100sen, 1EUR=100eurocents, 1GBP=100pence.
     * 将给定的带单位金额转换为 fen 汇总后输出，结果只保留整数，小数舍弃。
     *
     * 输入：
     * 第一行是N，N 表示记录数。O<N<100。
     * 之后N行每行表示一个带单位金额，格式为数字＋单位，可能是单独元，或者单独分，或者元与分的组合。
     *
     * 输出：。
     * 一个整数数字，不带小数，不带单位。
     *
     * 示例1：
     * 输入：
     * 3
     * 1CNY
     * 20CNY53fen
     * 53HKD87cents
     * 输出：
     * 6532
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // 记录数
        double totalFen = 0;  // 总的 fen 数

        Pattern numPattern = Pattern.compile("[0-9]+");
        Pattern charPattern = Pattern.compile("[a-zA-Z]+");

        List<Integer> numList = new ArrayList<Integer>();
        List<String> charList = new ArrayList<String>();
        // 处理每条记录
        for (int i = 0; i < N; i++) {
            String record = scanner.next();  // 读取带单位金额

            // 匹配数字部分
            Matcher matcher = numPattern.matcher(record);
            while (matcher.find()) {
                int amount = Integer.parseInt(matcher.group());
                System.out.println("Amount: " + amount);
                numList.add(amount);
            }

            // 匹配字符部分
            matcher = charPattern.matcher(record);
            while (matcher.find()) {
                System.out.println("Unit: " + matcher.group());
                charList.add(matcher.group());
            }

            System.out.println(numList);
            System.out.println(charList);
        }
        for (int j = 0; j < charList.size(); j++) {
            totalFen  += jisuan(charList.get(j),numList.get(j));
        }

        System.out.println(Math.floor(totalFen));
    }
    
    public static double jisuan(String unit, int amount){
        double fenNum = 0;
        // 根据单位进行金额转换
        switch (unit) {
            case "CNY":
                fenNum = amount * 100.0;
                break;
            case "JPY":
                fenNum = amount * (100.0/1825) * 100;
                break;
            case "HKD":
                fenNum = amount * (100.0/123) * 100;
                break;
            case "EUR":
                fenNum = amount * (100.0/14) * 100;
                break;
            case "GBP":
                fenNum = amount * (100.0/12) * 100;
                break;
            case "fen":
                fenNum = amount;
                break;
            case "cents":
                fenNum = amount * (100.0/123);
                break;
            case "sen":
                fenNum = amount * (100.0/1825);
                break;
            case "eurocents":
                fenNum = amount * (100.0/14);
                break;
            case "pence":
                fenNum = amount * (100.0/12);
                break;
            default:
                break;
        }
        return fenNum;
    }
}
