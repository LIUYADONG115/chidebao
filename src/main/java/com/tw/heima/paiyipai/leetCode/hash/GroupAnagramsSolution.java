package com.tw.heima.paiyipai.leetCode.hash;

import lombok.val;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−06-26 10:00 下午
 */
public class GroupAnagramsSolution {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * <p>
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> value = hashMap.getOrDefault(key, new ArrayList<>());
            value.add(str);
            hashMap.put(key, value);
        }
        return new ArrayList<>(hashMap.values());
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int strLength = str.length();
            for (int i = 0; i < strLength; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    sb.append(i + 'a');
                    sb.append(counts[i]);
                }
            }
            List<String> value = hashMap.getOrDefault(sb.toString(), new ArrayList<>());
            value.add(str);
            hashMap.put(sb.toString(), value);
        }
        return new ArrayList<>(hashMap.values());
    }
}
