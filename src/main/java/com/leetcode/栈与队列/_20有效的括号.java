package com.leetcode.栈与队列;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;


/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 每个右括号都有一个对应的相同类型的左括号。 示例 1：
 * <p>
 * 输入：s = "()" 输出：true 示例 2：
 * <p>
 * 输入：s = "()[]{}" 输出：true 示例 3：
 * <p>
 * 输入：s = "(]" 输出：false 栈 先入后出的特点
 */

public class _20有效的括号 {


    public boolean isValid(String s) {
        if (Objects.isNull(s) || s.length() < 2) {
            return false;
        }
        // 括号的对应关系
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        // 栈
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            // 左侧的括号都压入栈底
            if (map.containsKey(c)) {
                stack.push(c);
                continue;
            }
            // 栈非空，遇到右侧的括号时，栈pop出一个元素
            if (!stack.isEmpty() && c == map.get(stack.peek())) {
                // 栈移除元素
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }


}
