package com.leetcode.easy;
//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 121
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3： 
//
// 
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 示例 4： 
//
// 
//输入：x = -101
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
//
// 
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1682 👎 0


import java.util.Objects;

public class _09回文数 {

    public static void main(String[] args) {
        System.out.println(_09回文数.isPalindrome(121));

    }

    public static boolean isPalindrome(int x) {
        // 排除负数
        if (x < 0) {
            return Boolean.FALSE;
        }
        int y = x;
        int result = 0;
        do {
            result = result * 10 + y % 10;
        } while ((y /= 10) != 0);
        return Objects.equals(result, x) ? Boolean.TRUE : Boolean.FALSE;
    }
}
