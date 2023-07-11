package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 11:51 下午
 */
public class RequestAndResponseBaowen {

    /**
     * IGMP 协议中响应报文和查询报文，是维系组播通路的两个重要报文，在一条已经建立的组播通路中两个相邻的 HOST 和 ROUTER，
     * ROUTER 会给 HOST 发送查询报文，HOST 收到查询报文后给 ROUTER 回复一个响应报文，以维持相之间的关系，
     * 一旦这关系断裂，那么这条组播通路就异常”了。
     * 现通过某种手段，抓取到了 HOST 和 ROUTER 两者通讯的所有响应报文和查询报文，请分析该组播通路是否“正常”
     *
     * 输入：
     * <p>第一行抓到的报文数量C (C≤100) ，后续C行依次输入设备节点D1和D2，表示从D1到D2发送了单向的报文，D1和D2用空格隔开。</p>
     *
     * 输出
     * true/false
     *
     * 5
     * 1 2
     * 2 3
     * 3 2
     * 1 2
     * 2 1
     *
     * 输出：
     * true
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt(); // 输入报文数量

        Map<Integer, Set<Integer>> routes = new HashMap<>(); // 用于存储通讯的路由关系，以邻接表的形式存储

        // 输入每一条报文的源节点和目标节点，并将其添加到路由关系中
        for (int i = 0; i < num; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            routes.computeIfAbsent(source, k -> new HashSet<>()).add(destination); // 将目标节点添加到源节点的邻接表中
            routes.putIfAbsent(destination, new HashSet<>()); // 添加目标节点到路由关系中，以防止目标节点没有发送查询报文
        }

        // 遍历路由关系，检查每一个节点的邻接节点是否存在对应的反向连接
        for (Map.Entry<Integer, Set<Integer>> entry : routes.entrySet()) {
            int source = entry.getKey();
            Set<Integer> destinations = entry.getValue();
            for (int destination : destinations) {
                // 如果邻接节点的反向连接不存在，则说明组播通路异常，输出False并结束程序
                if (!routes.containsKey(destination) || !routes.get(destination).contains(source)) {
                    System.out.println("False");
                    return;
                }
            }
        }

        // 如果所有节点的邻接节点的反向连接都存在，则说明组播通路正常，输出True
        System.out.println("True");
    }
}

