package com.tw.heima.paiyipai.zhenti.morni.test4;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-03 10:35 下午
 */
public class StrSize {

    /**
     * 给定一个只包含大写字母的字符串，长度小于100，请计算只包含同一字母的子串中长度第k长的子串的长度。
     * 注意：相同字母只取最长的子串。若不存在则输出-1。子串长度相同则依次顺排，
     * 比如字符串ABC,第1长的子串长度是1，第2长的子串长度是1，第3长的子串长度也是 1。再比如字符串AABAAA，第1长的子串长度是3，第二长的子串长度长度1。
     * 示例：
     * 输入：
     * CAACBDDDFF  2
     *
     * 输出：
     * 2
     *
     * CAACBDDDFF
     * D: 3
     * F: 2
     * A: 2
     * C: 1
     * B: 1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int k = scanner.nextInt();
        scanner.close();

        int[] lengths = new int[26];
        int maxLen = 0;
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
                if (i == str.length() - 1) {
                    char c = str.charAt(i - 1);
                    int index = c - 'A';
                    lengths[index] = count;
                }
            } else {
                char c = str.charAt(i - 1);
                int index = c - 'A';
                maxLen = Math.max(maxLen, count);
                lengths[index] = Math.max(lengths[index], maxLen);
                maxLen = 0;
                count = 1;
                if (i == str.length() - 1) {
                    char latest = str.charAt(i);
                    int latestIndex = latest - 'A';
                    int latestIndexNum = Math.max(lengths[latestIndex], count);
                    lengths[latestIndex] = latestIndexNum;
                }
            }
        }
        System.out.println(Arrays.toString(lengths));
        Arrays.sort(lengths);
        System.out.println(Arrays.toString(lengths));
//        Arrays.sort(lengths, new Comparator<Integer>() {
//            public int compare(Integer num1, Integer num2) {
//                // 降序排序，返回负数表示num1在前，正数表示num2在前，0表示相等
//                return num2 - num1;
//            }
//        });

//        Collections.sort(lengths, Collections.reverseOrder());

        if (k > lengths.length) {
            System.out.println(-1);
        } else {
            System.out.println(lengths[lengths.length-k]);
        }
    }
}
