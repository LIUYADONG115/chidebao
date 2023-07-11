package com.tw.heima.paiyipai.zhenti.morni.test6;

import java.util.*;
import java.io.*;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:30 下午
 */
public class Lajiduanxin {
    // 将字符串按空格分割成整数数组
    public static List<Integer> split(String input_str) {
        List<Integer> v = new ArrayList<>();
        while (input_str.indexOf(" ") != -1) { // 找到空格
            int found = input_str.indexOf(" "); // 找到空格的位置
            v.add(Integer.parseInt(input_str.substring(0, found))); // 截取空格前的字符串并转换为整数，加入数组
            input_str = input_str.substring(found + 1); // 更新字符串，去掉已处理的部分
        }
        v.add(Integer.parseInt(input_str)); // 处理最后一个数字
        return v;
    }

    /**
     * 垃圾短信
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入处理
        String N_str = sc.nextLine();
        int n = Integer.parseInt(N_str);

        List<List<Integer>> id_pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input_str = sc.nextLine();
            id_pairs.add(split(input_str)); // 将字符串按空格分割成整数数组，加入二维数组
        }

        String T_str = sc.nextLine();
        int id = Integer.parseInt(T_str);

        // 发送短信和收到短信的统计信息
        List<Integer> send_list = new ArrayList<>(); // 发送给指定ID的人员列表
        List<Integer> receive_list = new ArrayList<>(); // 从指定ID接收短信的人员列表

        // key为指定ID, value为其send的个数
        Map<Integer, Integer> send_map = new HashMap<>();
        // key为指定ID, value为其receive 的个数
        Map<Integer, Integer> receive_map = new HashMap<>();

        for (List<Integer> id_pair : id_pairs) { // 遍历二维数组
            int sender = id_pair.get(0);
            int receiver = id_pair.get(1);

            if (sender == id) { // 如果发送者为指定ID
                send_list.add(receiver); // 将接收者加入发送给指定ID的人员列表
                send_map.put(receiver, send_map.getOrDefault(receiver, 0) + 1); // 发送给该接收者的短信数+1
            }

            if (receiver == id) { // 如果接收者为指定ID
                receive_list.add(sender); // 将发送者加入从指定ID接收短信的人员列表
                receive_map.put(sender, receive_map.getOrDefault(sender, 0) + 1); // 从该发送者接收的短信数+1
            }
        }

        // 去重结果
        Set<Integer> send_set = new HashSet<>(send_list); // 将发送给指定ID的人员列表去重
        Set<Integer> receive_set = new HashSet<>(receive_list); // 将从指定ID接收短信的人员列表去重

        // 交集
        List<Integer> intersect = new ArrayList<>();
        for (Integer i : send_list) {
            if (receive_list.contains(i)) {
                intersect.add(i);
            }
        }

        // 两个指标 L & M
        int L = send_set.size() - intersect.size(); // L为发送给指定ID的人员列表去重后减去交集的大小
        int M = send_list.size() - receive_list.size(); // M为发送给指定ID的人员列表的大小减去从指定ID接收短信的人员列表的大小

        boolean flag = false;
        if (L > 10 || M > 5){ // 如果L大于10或M大于5
            flag = true; // 设置标志为true
        }

        if (!flag) { // 如果标志为false
            // 指标 N
            for (int single_id : intersect) { // 遍历交集
                if (send_map.get(single_id) - receive_map.get(single_id) > 5) { // 如果发送给该人员的短信数减去从该人员接收的短信数大于5
                    flag = true; // 设置标志为true
                    break; // 结束循环
                }
            }
        }

        System.out.println(flag + " " + L + " " + M); // 输出结果
    }
}

