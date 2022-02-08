package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description
 */

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 使用 StringBuilder 拼接字符串
 */
public class _05替换空格 {

    public static void main(String[] args) {
        String replace = _05替换空格.replace("We are happy.");
        System.out.println("replace = " + replace);
    }

    public static String replace(String str) {
        StringBuilder response = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (' '==c){
                response.append("%20");
            }else {
                response.append(c);
            }
        }
        return response.toString();
    }
}
