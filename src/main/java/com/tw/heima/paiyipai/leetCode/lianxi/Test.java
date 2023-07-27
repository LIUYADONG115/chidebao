package com.tw.heima.paiyipai.leetCode.lianxi;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Test {
    /**
     * 取出100万数据里，最小的1000个数
     * @param args
     */
    public static void main(String[] args) {
        int toatl = 1000000;
        int num = 1000;
        int[] numbers = new int[toatl];

        Random random = new Random();
        for (int i = 0; i < toatl; i++) {
            numbers[i] = random.nextInt(toatl);
        }

        //构建最小队，数量位num
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < num; i++) {
            priorityQueue.offer(numbers[i]);
        }

        //遍历剩余的数据，更改堆中的内容
        for (int i = num; i < toatl; i++){
            if (numbers[i]>priorityQueue.peek()){
                priorityQueue.poll();
                priorityQueue.offer(numbers[i]);
            }
        }

        //转为数组
        int[] jieguo = new int[num];
        for (int i = num-1; i >=0 ; i--) {
            jieguo[i] = priorityQueue.poll();
        }

        System.out.println(Arrays.toString(jieguo));

    }
}
