package com.leetcode.kmp;

/**
 * @Date 2023/12/24
 * @Author yangfa
 * @Description
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */
public class _28找出字符串中第一个匹配项的下标KMP {

    public static void main(String[] args) {
        _28找出字符串中第一个匹配项的下标KMP 解 = new _28找出字符串中第一个匹配项的下标KMP();
        String haystack = "sadbutsad", needle = "sad";
        System.out.println(解.strStr(haystack, needle));

        String haystack1 = "leetcode", needle1 = "leeto";
        System.out.println(解.strStr(haystack1, needle1));

    }


    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    public void getNext(int [] next, String s) {
        int j = 0;
        next[0] = 0;
        for(int i = 1; i < s.length(); i++) {
            // 处理前后缀不相同的情况
            while (j > 0 && s.charAt(i) != s.charAt(j)) { // j要保证大于0，因为下面有取j-1作为数组下标的操作
                j = next[j - 1]; // 注意这里，是要找前一位的对应的回退位置了
            }
            // 处理前后缀相同的情况
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }
}
