package com.tw.heima.paiyipai.zhenti.morni.test3;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-02 6:44 下午
 */

public class CheckNumberCombination {
    /**
     * 给定一个正整数数组，检查数组中是否存在满足规则X=Y+2Z的数字组合（用例保证最多只有一组解）。
     * 说明：数组元素可重复，但每个元素只能在结果算式中使用一次。如：数组成员为[0,0,1,5],0出现2次是允许的，但结果0=0+2*0虽然满足规则但不匀速，因为算式中使用了3个0.
     * <p>
     * 输入：
     * 第一行是数组长租N，3≤N≤100
     * 第二行是所有数组元素（取值范围为[0,65535]），空格分隔。
     * <p>
     * 输出：
     * 依次输出X Y Z的值，空格分隔。
     * <p>
     * 示例：
     * 输入：
     * 4
     * 2 8 4 0
     * 输出：
     * 8 4 2
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        //checkNumberCombination(nums);
        checkNumberCombination2(nums);
    }

    /**
     * 暴力解法
     * 遍历数组元素，将数组中的每个元素作为 X 进行考虑。
     * 对于每个 X，使用两层循环分别遍历数组元素，找到满足 Y + 2Z = X 的组合。
     * x = 4 y = 0  z = 2
     * <p>
     * X Y Z
     * 8 4 2
     * 8 0 4
     * 4 0 2
     *
     * @param nums
     */
    private static void checkNumberCombination(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int X = nums[i];
            for (int j = 0; j < n; j++) {
                int Y = nums[j];
                for (int k = 0; k < n; k++) {
                    int Z = nums[k];
                    if (Y + 2 * Z == X && X != Y && X != Z && Y != Z) {
                        System.out.println(X + " " + Y + " " + Z);
                        //return;
                    }
                }
            }
        }
    }

    /**
     * 空间换时间，时间复杂度O(N^2),空间复杂度O(N)。用两重遍历存储所有可能的X-Y的值，并将该值、X、Y存入map。再遍历Z，匹配2Z=X-Y，输出结果。
     * <p>
     * Key: -2, Value: [2, 4, 0, 2]
     * Key: 2, Value: [2, 0, 4, 2]
     * Key: -4, Value: [4, 8, 0, 4]
     * Key: 4, Value: [8, 4, 4, 0]
     * Key: -6, Value: [2, 8]
     * Key: 6, Value: [8, 2]
     * Key: -8, Value: [0, 8]
     * Key: 8, Value: [8, 0]
     * <p>
     * 8 4 2
     * 8 0 4
     *
     * @param nums
     */
    private static void checkNumberCombination2(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int X = nums[i];
            for (int j = 0; j < nums.length; j++) {
                int Y = nums[j];
                if (X != Y) {
                    int key = X - Y; // 2Z
                    //List<Integer> value = map.getOrDefault(key, new ArrayList<>());
                    int[] values = map.get(key);
                    if (values == null) {
                        values = new int[2];
                        values[0] = X;
                        values[1] = Y;
                        map.put(key, values);
                    } else {
                        int[] newArray = Arrays.copyOf(values, values.length + 2);
                        newArray[newArray.length - 2] = X;
                        newArray[newArray.length - 1] = Y;
                        map.put(key, newArray);
                    }
                }
            }
        }
        // 打印Map集合
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + Arrays.toString(entry.getValue()));
        }

        for (int i = 0; i < nums.length; i++) {
            int Z = nums[i];
            if (map.containsKey(Z * 2)) {
                int[] pair = map.get(Z * 2);
                //[4, 8, 0, 4]
                for (int n = 0; n < pair.length; n++) {
                    if (2 * n < pair.length) {
                        int X = pair[2 * n];
                        int Y = pair[2 * n + 1];
                        if (X != Y && X != Z && Y != Z) {
                            System.out.println(X + " " + Y + " " + Z);
                            //return;
                        }
                        //return;
                    }

                }
            }
        }
    }
}
