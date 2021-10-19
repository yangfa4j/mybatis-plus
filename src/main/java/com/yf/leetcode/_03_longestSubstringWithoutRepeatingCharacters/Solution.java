package com.yf.leetcode._03_longestSubstringWithoutRepeatingCharacters;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 输入: s = "abcabcbb" 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 来源：力扣（LeetCode）无重复字符的最长子串
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */

import java.util.HashMap;

/**
 * 解题思路：双指针，
 */
class Solution {

    public static void main(String[] args) {
        new Solution().lengthOfLongestSubstring("abcabcbb");
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}