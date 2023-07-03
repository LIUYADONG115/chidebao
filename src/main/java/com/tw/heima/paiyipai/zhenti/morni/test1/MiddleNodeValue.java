package com.tw.heima.paiyipai.zhenti.morni.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-01 6:20 下午
 */
class Node {
    int value;
    Node next = null;

    Node(int value) {
        this.value = value;
    }
}

public class MiddleNodeValue {
    private static Node head;
    /**
     * 输出单链表的中间节点的值。如果有两个中问节点，则输出第二个中间节点的值。
     *
     * 输入：
     * 第一行是链表首节点的地址、节点总数N（[1,100000]）。地址为5位非负整数，-1表示NULL
     * 地址。
     * 之后N行，每行格式为
     * 地址 值 下一个节点地址。
     * 其中值为10,10^8]之间的任意整数。
     * 说明：存在部分不属于单链表的节点。
     *
     * 输出：
     * 一个整数值。
     *
     * 示例：
     * 输入：
     * 11000 3
     * 86892 8 22309
     * 22309 6 - 1
     * 11000 3 86892
     * 输出：
     * 8
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 链表首节点的地址、节点总数N
        String firstLine = sc.nextLine();
        System.out.println(firstLine);
        String[] firstLines = firstLine.split("\\s");
        System.out.println(Arrays.toString(firstLines));

        // 输入示例数据
        String headAddress = firstLines[0];
        int N = Integer.parseInt(firstLines[1]);

        // 构建链表
        Map<String, Node> nodes = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String nextLine = sc.nextLine();
            String[] nextLines = nextLine.split("\\s");
            String firstAddress = nextLines[0];
            int value = Integer.parseInt(nextLines[1]);
            String nextAddress = nextLines[2];

            Node node = new Node(value);
            nodes.put(firstAddress, node);

            if (Integer.parseInt(nextAddress) != -1) {
                node.next = nodes.get(nextAddress);
            }
        }

        // 遍历链表找到中间节点
        head = nodes.get(headAddress);
        Node slow = nodes.get(headAddress);
        Node fast = nodes.get(headAddress);

        printList(slow);

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

         //输出中间节点的值
        System.out.println(slow.value);
    }


    public static void printList(Node tmp) {
        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    /**
     *返回节点总长度
     * @return
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     *
     * @param index:删除第index个节点
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public Node ReverseIteratively(Node head) {
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;
        while (pNode != null) {
            Node pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReversedHead;
        return this.head;
    }

    /**
     * 查找单链表的中间节点
     *
     * @param head
     * @return
     */
    public Node SearchMid(Node head) {
        Node p = this.head, q = this.head;
        while (p != null && p.next != null && p.next.next != null) {
            p = p.next.next;
            q = q.next;
        }
        System.out.println("Mid:" + q.value);
        return q;
    }

    /**
     * 查找链表倒数第k个元素
     * 采用两个指针P1,P2，P1先前移K步，然后P1、P2同时移动，当p1移动到尾部时，P2所指位置的元素即倒数第k个元素 。
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head, int k) {
        if (k < 1 || k > this.length()) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;
        for (int i = 0; i < k; i++)// 前移k步
            p1 = p1.next;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
