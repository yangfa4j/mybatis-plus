package com.leetcode.数组;


import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1： 输入：n = 3 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2： 输入：n = 1 输出：["()"]
 * <p>
 * 循环条件：左边的括号数量大于右边括号的数量时，就是正确的括号
 */

public class _22括号生成 {

    public static void main(String[] args) {
        _22括号生成 括号生成 = new _22括号生成();
        List<String> strings = 括号生成.generateParenthesis(3);

    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


}
