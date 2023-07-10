package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-08 2:39 下午
 */
public class PriorityQueue {
    /**
     * 实现一个支持优先级的队列，高优先级先出队列；同优先级时先进先出。
     * 如果两个输入数据和优先级都相同，则后一个数据不入队列被丢弃。
     * 队列存储的数据内容是一个整数。
     * <p>
     * 输入：(10,1),(20,1),(30,2),(40,3)
     * 输出：40,30,10,20
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();

        // 解析输入数据
        int[][] tasks =
                Arrays.stream(line.substring(1, line.length() - 1).split("\\),\\("))
                        .map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray())
                        .toArray(int[][]::new);

        // 使用优先级作为键，存储数据内容
        HashMap<Integer, LinkedHashSet<Integer>> map = new HashMap<>();

        // 遍历输入数据，按优先级存储数据内容
        for (int[] task : tasks) {
            int num = task[0];
            int priority = task[1];

            map.putIfAbsent(priority, new LinkedHashSet<>());
            map.get(priority).add(num);
        }

        // 构建输出字符串
        StringJoiner sj = new StringJoiner(",");
        // 按优先级从高到低遍历数据内容
        map.keySet().stream()
                .sorted((a, b) -> b - a)
                .forEach(
                        p -> {
                            // 按输入顺序输出数据内容
                            map.get(p).forEach(num -> sj.add(num + ""));
                        });

        System.out.println(sj.toString());
    }

    public static void test2Function() {

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
