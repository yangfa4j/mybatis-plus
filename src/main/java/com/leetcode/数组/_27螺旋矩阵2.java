package com.leetcode.数组;


/**
 * @Date 2023/8/7
 * @Author yangfa
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],
 *       [8,9,4],
 *       [7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 */

public class _27螺旋矩阵2 {

    public static void main(String[] args) {
        _27螺旋矩阵2 解 = new _27螺旋矩阵2();
        解.generateMatrix(3);
    }

    public int[][] generateMatrix(int n) {
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;
        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;

        }

        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }


}
