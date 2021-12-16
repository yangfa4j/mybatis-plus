package com.yf.leetcode.easy;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2765 👎 0

// 遍历给定的字符串 ss，当遇到一个左括号时，期望在后续的遍历中，有一个相同类型的右括号将其闭合。
// 由于后遇到的左括号要先闭合，因此可以将这个左括号放入栈顶。


import java.util.*;

public class _20有效的括号 {

    public static void main(String[] args) {
        System.out.println(_20有效的括号.isValid("[["));
    }

    public static boolean isValid(String s) {
        //括号不是成对的直接返回 false
        if (s.length() % 2 != 0) {
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
            if (map.containsKey(c)) {
                stack.push(c);
                continue;
            }
            if (!stack.isEmpty() && c == map.get(stack.peek())) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}