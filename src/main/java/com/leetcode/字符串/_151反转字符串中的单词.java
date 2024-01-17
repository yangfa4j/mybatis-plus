package com.leetcode.字符串;


/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * <p>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * <p>
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * <p>
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "the sky is blue" 输出："blue is sky the" 示例 2：
 * <p>
 * 输入：s = "  hello world  " 输出："world hello" 解释：反转后的字符串中不能存在前导空格和尾随空格。 示例 3：
 * <p>
 * 输入：s = "a good   example" 输出："example good a" 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 */

public class _151反转字符串中的单词 {

    public static void main(String[] args) {
        String s = "a good   example";
        _151反转字符串中的单词 解 = new _151反转字符串中的单词();
        System.out.println(解.reverseWords(s));

    }

    public String reverseWords(String s) {

        int start = 0;
        int end = s.length() - 1;
        // 去前后空格
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }

        // 去中间的空格  ab_bc_ac
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
        }

        // 反转字符串  ca_cb_ba
        reverse(0, sb.length() - 1, sb);

        // 反转单词 eulb si yks eht -> the sky is blue

        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            while (count < sb.length() && sb.charAt(count) != ' ') {
                count++;
            }
            reverse(i, count - 1, sb);
            i = count;
            count++;
        }
        return sb.toString();
    }

    private void reverse(int start, int end, StringBuilder sb) {
        char temp;
        while (start < end) {
            temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }


}
