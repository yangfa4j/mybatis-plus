package com.leetcode.哈希表;


import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @Date 2023/8/7
 * @Author yangfa 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram" 输出: true 示例 2:
 * <p>
 * 输入: s = "rat", t = "car" 输出: false
 */

public class _242有效的字母异位词 {

    public static void main(String[] args) {
        String s = "aacc", t = "ccac";
        _242有效的字母异位词 有效的字母异位词 = new _242有效的字母异位词();
        System.out.println(有效的字母异位词.isAnagram(s, t));

    }

    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (int k = 0; k < t.length(); k++) {
            if (map.containsKey(t.charAt(k))) {
                map.put(t.charAt(k), map.get(t.charAt(k)) - 1);
            } else {
                map.put(t.charAt(k), 1);
            }
        }
        int sum = 0;
        for (Entry<Character, Integer> entry : map.entrySet()) {
            sum += Math.abs(entry.getValue());
        }

        return sum == 0;
    }


}
