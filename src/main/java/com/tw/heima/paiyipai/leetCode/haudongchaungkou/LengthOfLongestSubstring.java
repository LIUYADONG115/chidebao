package com.tw.heima.paiyipai.leetCode.haudongchaungkou;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-07 5:47 下午
 */
public class LengthOfLongestSubstring {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "pwwkew";
        Set<Character> set = new HashSet<Character>();

        int right = 0;
        int length = 0;

        for (int i = 0; i < str.length(); i++) {
            if (i != 0) {
                set.remove(str.charAt(i - 1));
            }
            while (right < str.length() && !set.contains(str.charAt(right))) {
                set.add(str.charAt(right));
                right++;
            }
            length = Math.max(length, right - i);
        }

        System.out.println(length);
    }

    public static int lengthOfLongestSubstring(String str) {
        Set<Character> set = new HashSet<Character>();

        int right = 0;
        int length = 0;

        for (int i = 0; i < str.length(); i++) {
            if (i != 0) {
                set.remove(str.charAt(i - 1));
            }
            while (right < str.length() && !set.contains(str.charAt(right))) {
                set.add(str.charAt(right));
                right++;
            }
            length = Math.max(length, right - i);
        }

        System.out.println(length);
        return length;
    }
}

