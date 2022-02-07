package com.leetcode.easy;
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 3221 👎 0

// / % * 的实际运用
// 有符号类型：最高数称为“符号位”，为1时表示为负值，为0时表示为正值。  无符号数： 11111111    值：255
// 无符号类型：全是表示数的大小                                    有符号数： 01111111    值：127
// 有符号32的数字表示大小为  -2^31 - 2^31-1                       有符号数： 11111111    值：-127

public class _07整数反转 {

    public static void main(String[] args) {
        int reverse = _07整数反转.reverse(123456);
        System.out.println("reverse = " + reverse);
    }

    public static int reverse(int x) {
        long result = 0;
        do {
            // % 取最后一位数
            result = 10 * result + x % 10;
        }
        // / 丢弃最后一位数
        while ((x /= 10) != 0);
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result;
    }
}
