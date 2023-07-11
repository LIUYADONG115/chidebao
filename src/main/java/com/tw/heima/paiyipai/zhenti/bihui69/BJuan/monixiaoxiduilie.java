package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:12 上午
 */
public class monixiaoxiduilie {

    /**
     * 让我们来模拟一个消息队列的运作，有一个发布者和若干消费者，发布者会在给定的时刻向消息队列发送消息，
     * 若此时消息队列有消费者订阅，这个消息会被发送到订阅的消费者中优先级最高（输入中消费者按优先级升序排列）的一个；
     * 若此时没有订阅的消费者，该消息被消息队列丢弃。
     *
     * 消费者则会在给定的时刻订阅消息队列或取消订阅。
     * 当消息发送和订阅发生在同一时刻时，先处理订阅操作，即同一时刻订阅的消费者成为消息发送的候选。
     * 当消息发送和取消订阅发生在同一时刻时，先处理取消订阅操作，即消息不会被发送到同一时刻取消订阅的消费者。
     *
     *
     * 输入描述：
     * 输入为两行
     * 第一行为2N个正整数，代表发布者发送的N个消息的时刻和内容（为方便解折，消息内容也用正整数表示）。第一个数字是第一个消息的发送时刻，第二个数字是第一个消息的内容，以此类推。用例保证发送时刻不会重复，但注意消息并没有按照发送时刻排列。
     * 第二行为2M个正整数，代表M个消费者订阅和取消订阅的时刻。第一个数字是第一个消费者订阅的时刻，第二个数字是第一个消费者取消订阅的时刻，以此类推。用例保证每个消费者的取消订阅时刻大于订阅时刻，消费者按优先级升序排列
     *
     * 输出
     * 输出为M行，依次为M个消费者收到的消息内容，消息内容按收到的顺序排列，且由空格分隔；
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读入发布者和订阅者的信息
        int[] publisherSubscriberArray = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] subscriberArray = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int numPublisherSubscriber = publisherSubscriberArray.length;
        int numSubscriber = subscriberArray.length;

        // 将发布者的信息存储到二维数组中
        int[][] publishers = new int[numPublisherSubscriber / 2][2];
        for (int i = 0, k = 0; i < numPublisherSubscriber; i += 2) {
            publishers[k++] = new int[]{publisherSubscriberArray[i], publisherSubscriberArray[i + 1]};
        }

        // 将订阅者的信息存储到二维数组中
        int[][] subscribers = new int[numSubscriber / 2][2];
        for (int j = 0, k = 0; j < numSubscriber; j += 2) {
            subscribers[k++] = new int[]{subscriberArray[j], subscriberArray[j + 1]};
        }

        // 将发布者的信息按照时间升序排序
        Arrays.sort(publishers, Comparator.comparingInt(p -> p[0]));

        // 用一个列表存储每个订阅者收到的消息
        List<List<Integer>> subscriberContents = new ArrayList<>(subscribers.length);
        for (int[] ignored : subscribers) {
            subscriberContents.add(new ArrayList<>());
        }

        // 处理每个发布者发送的消息
        for (int[] pub : publishers) {
            int pubTime = pub[0];
            int pubContent = pub[1];

            // 从优先级最高的订阅者开始遍历
            for (int j = subscribers.length - 1; j >= 0; j--) {
                int subTime = subscribers[j][0];
                int unsubscribeTime = subscribers[j][1];

                // 如果该订阅者在消息发布时已经订阅但未取消订阅，则将消息发送给该订阅者
                if (pubTime >= subTime && pubTime < unsubscribeTime) {
                    subscriberContents.get(j).add(pubContent);
                    break;
                }
            }
        }

        // 输出每个订阅者收到的消息
        for (List<Integer> subscriberContent : subscriberContents) {
            if (subscriberContent.isEmpty()) {
                System.out.println("-1");
            } else {
                StringJoiner sj = new StringJoiner(" ");
                for (Integer content : subscriberContent) {
                    sj.add(content.toString());
                }
                System.out.println(sj.toString());
            }
        }
    }
}

