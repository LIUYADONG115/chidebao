package com.tw.heima.paiyipai.zhenti.morni.test4;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-03 11:53 下午
 */
public class FindLongestVowelSubstring {
    /**
     * 首尾都是元音字母 (aeiouAEIOU)的字符串为元音字符串，元音字符串中混杂的非元音字母数量为瑕疵度。
     * 给定一个字符串，请计算指定瑕疵度的最长元音字符子串的长度。若找不到则输出 0
     * <p>
     * 输入：
     * 第一行是一个整数，表示瑕疵度，取值[0,65535]
     * 第二行是一个仅由a-z 和 A-Z构成的字符串，长度为[1,65535]
     * <p>
     * 输出：
     * 一个整数，表示指定瑕疵度的最长元音字符子串长度。
     * <p>
     * 示例：
     * 输入：
     * 1
     * Aabiidoo
     * 输出：
     * 5
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int flaw = Integer.parseInt(sc.nextLine());
            String input = sc.nextLine().toLowerCase(Locale.ROOT);
            int maxLength = 0;

            for (int i = 0; i < input.length(); i++) {
                char currentChar1 = input.charAt(i);
                if (!isVowel(currentChar1)) {
                    continue;
                }
                Stack<Character> stack = new Stack<>();
                // 统计当前元音子串
                // 统计当前瑕疵个数
                int currentXiaoChi = 0;
                for (int j = i; j < input.length(); j++) {
                    char currentChar2 = input.charAt(j);
                    stack.push(currentChar2);
                    if (!isVowel(currentChar2)) {
                        currentXiaoChi++;
                        if (currentXiaoChi > flaw) {
                            stack.clear();
                            currentXiaoChi = 0;
                        }
                    }
                    if (!stack.isEmpty() && currentXiaoChi == flaw && isVowel(stack.peek())) {
                        maxLength = Math.max(maxLength, stack.size());
                    }
                }
            }
            System.out.println(maxLength);
        }
        sc.close();
    }

    /**
     * 从头开始遍历字符串，将元素加入队列，并记录队列中非元音字母的个数。
     * 当队列中非元音字母的个数等于瑕疵度时，记录此时的队列长度，更新最大长度；
     * 当队列中非元音字母的个数大于瑕疵度时，从队头开始丢弃元素，直到不大于瑕疵度或队列为空；
     * 这样，所有元素都会过一遍队列，遍历结束。此时的最大长度即为所求。
     *
     * @param str
     * @param flawDegree
     * @return
     */
    public static int findLongestVowelSubstring(String str, int flawDegree) {
        return 0;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

