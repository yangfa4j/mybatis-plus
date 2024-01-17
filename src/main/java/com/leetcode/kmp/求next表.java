package com.leetcode.kmp;

/**
 * @Date 2023/12/24
 * @Author yangfa
 * @Description
 */
public class 求next表 {

    public static void main(String[] args) {

        求next表 解 = new 求next表();
        String s = "abcdabca";
        int[] ints = new int[s.length()];
        解.getNext(ints,s);
        System.out.println(ints);

        String s1 = "aabaaf";
        int[] ints1 = new int[s1.length()];
        解.getNext(ints1,s1);
        System.out.println(ints1);

    }

    // 最长相等前后缀
    public void getNext(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
    }
}
