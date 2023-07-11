package com.tw.heima.paiyipai.leetCode.node;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 4:15 下午
 */
class Node {
    int value;
    Node next = null;

    Node(int value) {
        this.value = value;
    }

}

public class XiangjiaoNodeSolutions {
    public static void main(String[] args) {

    }

    /**
     * 判断这种A中有B，建议使用Map或者Set集合
     * 方案一：哈希集合
     *
     * @param headA
     * @param headB
     * @return
     */
    public Node getIntersectionNode(Node headA, Node headB) {
        Set<Node> hashSet = new LinkedHashSet<>();
        Node tempNode = headA;
        while (tempNode != null) {
            hashSet.add(tempNode);
            tempNode = tempNode.next;
        }

        tempNode = headB;
        while (tempNode != null) {
            if (hashSet.contains(tempNode)) {
                return tempNode;
            }
            tempNode = tempNode.next;
        }
        return null;
    }

    /**
     * 方案二：双指针
     * <p>
     * 设「第一个公共节点」为 node ，「链表 headA」的节点数量为 a ，「链表 headB」的节点数量为 b ，「两链表的公共尾部」的节点数量为 c ，则有：
     * 头节点 headA 到 node 前，共有 a−c个节点；
     * 头节点 headB 到 node 前，共有 b−c个节点；
     * <p>
     * 此时指针 A , B 重合，并有两种情况
     * a+(b−c)=b+(a−c)
     *
     * @param headA
     * @param headB
     * @return
     */
    public Node getIntersectionNode2(Node headA, Node headB) {
        Node tempA = headA;
        Node tempB = headB;

        while (tempA != tempB) {
            tempA = tempA != null ? tempA.next : headB;
            tempB = tempB != null ? tempB.next : headA;
        }
        return tempA;
    }
}
