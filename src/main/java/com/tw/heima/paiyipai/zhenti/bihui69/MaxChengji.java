package com.tw.heima.paiyipai.zhenti.bihui69;

/**
 * 给定一个元素类型为小写字符串的数组，请计算两个没有相同字符的元素长度乘积的最大值，
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 10:50 下午
 */
import java.util.*;
public class MaxChengji {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            // 将字符串解析为字符串数组
            String[] strArr = input.split(",");
            // 计算每个字符串中出现的字符
            Map<String, Set<Character>> strCharMap = new HashMap<>();
            for (String str : strArr) {
                Set<Character> charSet = new HashSet<>();
                for (char c : str.toCharArray()) {
                    charSet.add(c);
                }
                strCharMap.put(str, charSet);
            }
            // 计算最大的长度乘积
            int ans = 0;
            for (int i = 0; i < strArr.length; i++) {
                Set<Character> set1 = strCharMap.get(strArr[i]);
                for (int j = i + 1; j < strArr.length; j++) {
                    Set<Character> set2 = strCharMap.get(strArr[j]);
                    // 判断两个字符串的字符集是否有交集
                    boolean isDisjoint = true;
                    for (char c : set1) {
                        if (set2.contains(c)) {
                            isDisjoint = false;
                            break;
                        }
                    }
                    if (isDisjoint) {
                        ans = Math.max(ans, strArr[i].length() * strArr[j].length());
                    }
                }
            }
            System.out.println(ans);
        }
    }

