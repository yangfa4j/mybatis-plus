package com.yf.leetcode.easy;
//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1864 👎 0


class _14最长公共前缀 {

    public static void main(String[] args) {
        String[] strs = new String[]{"abc","ab", "a"};
        String s = _14最长公共前缀.longestCommonPrefix(strs);
        System.out.println("s = " + s);

    }

    public static String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length < 1) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                // 当 i 自增 到某一个数组的长度时，此时就应该截断
                if (i == strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
