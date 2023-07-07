package com.tw.heima.paiyipai.zhenti.morni.test2;

import java.util.*;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-02 4:09 下午
 */
public class ParenthesesLength {
    /**
     * 给定一个仅由'('，')'，'{'，'}'，'['，']' 的字符串，长度为[0,100000]。请计算字符串中括号的最大嵌套深度。
     * 注意：任意一种类型的字符串左右括号数量不相等 或者 存在未先左后右闭合的括号，均视为无效字符串，输出 0。
     *
     * 输入：{[]}()
     * 输出：2
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(length(s));

    }

    /**
     * 对于括号计算类题目，我们往往可以用栈来思考。
     * 遍历字符串，如果遇到了一个左括号，那么就将其入栈；如果遇到了一个与栈顶同类型的右括号，那么就弹出栈顶的左括号，与该有括号匹配。这一过程中的栈的大小的最大值，即为
     * S的嵌套深度。
     * 若遍历完之后，栈中元素不为0，则说明存在无效字符串，输出0.
     * @param s
     * @return
     */
    public static int length(String s) {
        int maxDepth = 0;
        int currentDepth = 0;
        int n = s.length();
        if (n % 2 == 1) {
            return 0;
        }

        Map<Character, Character> map = new HashMap<>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return 0;
                }
                stack.pop();
                currentDepth--;
            } else {
                stack.push(ch);
                currentDepth++;
            }
            maxDepth = Math.max(maxDepth,currentDepth);
        }

        return maxDepth;

    }
}
