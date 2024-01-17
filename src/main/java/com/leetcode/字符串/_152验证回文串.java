package com.leetcode.字符串;


/**
 * @Date 2023/8/7
 * @Author yangfa 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * <p>
 * 字母和数字都属于字母数字字符。
 * <p>
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "A man, a plan, a canal: Panama" 输出：true 解释："amanaplanacanalpanama" 是回文串。 示例 2：
 * <p>
 * 输入：s = "race a car" 输出：false 解释："raceacar" 不是回文串。 示例 3：
 * <p>
 * 输入：s = " " 输出：true 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。 由于空字符串正着反着读都一样，所以是回文串。
 */

public class _152验证回文串 {

    public static void main(String[] args) {



    }

    public boolean isPalindrome(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)){
                stringBuffer.append(ch);
            }
        }
        StringBuffer reverse = stringBuffer.reverse();
        return reverse.toString().equals(stringBuffer.toString());
    }

    // 双指针 由两端一起向中间移动，两个指针相遇时，则是回文串
    public boolean isPalindrome2(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }

        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;

    }


}
