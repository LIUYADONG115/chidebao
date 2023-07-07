package com.tw.heima.paiyipai.zhenti.morni.test6;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 10:44 下午
 */
import java.util.*;
public class Huoguo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        List<Pair> dishes = new ArrayList<>();

        // Read the pairs of P and Q values for the N dishes
        for (int i = 0; i < N; i++) {
            int P = scanner.nextInt();
            int Q = scanner.nextInt();
            dishes.add(new Pair(P, Q));
        }

        // Sort the dishes based on the Q values in ascending order
        Collections.sort(dishes);

        int count = 0;
        int currentTime = 0;

        // Iterate through the sorted dishes and count the number of dishes that can be eaten
        for (Pair dish : dishes) {
            if (currentTime + K <= dish.Q) {
                count++;
                currentTime = dish.Q;
            }
        }

        System.out.println(count);
    }

    static class Pair implements Comparable<Pair> {
        int P;
        int Q;

        public Pair(int P, int Q) {
            this.P = P;
            this.Q = Q;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.Q, other.Q);
        }
    }
}
