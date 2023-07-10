package com.tw.heima.paiyipai.leetCode;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 6:49 下午
 */
public class Test {
    /**
     * 输入：(10,1),(20,1),(30,2),(40,3)
     * 输出：40,30,10,20
     *
     * @param args
     */
    public static void main(String[] args) {

        String str = "(10,1),(20,1),(30,2),(40,3)";
        String[] strArray = str.substring(1, str.length() - 1).split("\\),\\(");

        System.out.println(Arrays.toString(strArray));
        System.out.println(strArray[0]);

        //[10,1, 10,1, 30,2, 40,3]
        //10,1
        int[][] nums = new int[strArray.length][2];
        for (int i = 0; i < strArray.length; i++) {
            nums[i][0] = Integer.parseInt(strArray[i].split(",")[1]); // 1
            nums[i][1] = Integer.parseInt(strArray[i].split(",")[0]); // 10
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i][0], new LinkedHashSet<>());
            map.get(nums[i][0]).add(nums[i][1]);
        }

        StringBuffer sb = new StringBuffer();
        //
        Set<Integer> keySet = map.keySet();

        //[3, 2, 1]
        System.out.println(Arrays.toString(keySet.stream().sorted((a, b) -> b - a).toArray()));
        keySet.stream()
                .sorted((a, b) -> b - a)
                .forEach(key -> map.get(key)
                        .forEach(value -> sb.append(value).append(",")));

        String outPut = sb.toString();
        System.out.println(outPut.substring(0,outPut.length()-1));
    }
}
