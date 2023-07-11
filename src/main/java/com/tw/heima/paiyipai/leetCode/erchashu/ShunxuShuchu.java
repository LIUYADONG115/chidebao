package com.tw.heima.paiyipai.leetCode.erchashu;


import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-10 5:31 下午
 */
public class ShunxuShuchu {
    public static void main(String[] args) {

    }


    /**
     * 二叉树前序输出
     * 中 左 右
     *
     * @param head
     */
    public static void qianxu(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 二叉树中序输出
     * 左 中 右
     *
     * @param head
     */
    public static void zhongxu(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                cur = node.right;
            }
        }
    }


    /**
     * 二叉树后序输出
     * 左 右 中
     *
     * @param head
     */
    public static void houxu(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

}
