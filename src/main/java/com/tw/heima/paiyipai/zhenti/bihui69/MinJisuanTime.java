package com.tw.heima.paiyipai.zhenti.bihui69;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-05 10:52 下午
 */
import java.util.Scanner;
import java.util.Vector;
public class MinJisuanTime {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int maxCount, tasksLen;
            Vector<Integer> tasks = new Vector<>();
            String line;
            int time = 0;
            int remain = 0;

            while (scanner.hasNextLine()) {
                tasks.clear();
                time = 0;
                remain = 0;

                maxCount = Integer.parseInt(scanner.nextLine());
                tasksLen = Integer.parseInt(scanner.nextLine());
                line = scanner.nextLine();
                Scanner ss = new Scanner(line);
                for (int i = 0; i < tasksLen; i++) {
                    int task = ss.nextInt();
                    tasks.add(task);
                }

                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) + remain > maxCount) {
                        remain = tasks.get(i) + remain - maxCount;
                    } else {
                        remain = 0;
                    }
                    time++;
                }

                while (remain > 0) {
                    remain -= maxCount;
                    time++;
                }

                System.out.println(time);
            }
        }
    }
