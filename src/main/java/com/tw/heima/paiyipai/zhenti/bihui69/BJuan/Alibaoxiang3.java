package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.*;
import java.util.stream.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:19 上午
 */
public class Alibaoxiang3 {

    /**
     * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面贴有箱子中藏有金币的数量
     * 从金币数量中选出一个数字集合，并销毁贴有这些数字的每个箱子，如果能销毁一半及以上的箱子，则返回这个数字集合的最小大小
     *
     * 输入：
     * 一个数字字串，数字之间使用逗号分隔，例如：6,6,6,6,3,3,3,1,1,5
     * 1 ≤ 字串中数字的个数 ≤ 100000
     * 1 ≤ 每个数字 ≤ 100000
     *
     * 输出：
     * 这个数字集合的最小大小，例如: 2
     *
     * 输入：
     * 1,1,1,1,3,3,3,6,6,8
     * 输出：
     * 2
     *
     * 说明：选择集合{1,8}，销毁后的结果数组为[3,3,3,6,6]，长度为5，长度为原数组的一半。
     * 大小为 2 的可行集合还有{1,3},{1,6},{3,6}。
     *  选择 {6,8} 集合是不可行的，它销后的结果数组为[1,1,1,1,3,3,3]，新数组长度大于原数组的二分之一。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int half = (int) Math.ceil(nums.length / 2.0);

        List<Integer> sortedKeys =
                count.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

        int remove = 0;
        int numCount = 0;
        for (int key : sortedKeys) {
            remove += count.get(key);
            numCount++;
            if (remove >= half) {
                System.out.println(numCount);
                return;
            }
        }

        System.out.println(-1);
    }
}

