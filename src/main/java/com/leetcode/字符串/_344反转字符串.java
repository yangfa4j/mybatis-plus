package com.leetcode.字符串;


/**
 * @Date 2023/8/7
 * @Author yangfa 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = ['h','e','l','l','o'] 输出：['o','l','l','e','h'] 示例 2：
 * <p>
 * 输入：s = ['H','a','n','n','a','h'] 输出：['h','a','n','n','a','H']
 */

public class _344反转字符串 {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        _344反转字符串 解 = new _344反转字符串();
        解.reverseString(s);

    }

    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;

        char temp ;
        while (i<j){
            temp=s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }


}
