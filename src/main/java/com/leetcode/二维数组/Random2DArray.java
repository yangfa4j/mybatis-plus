package com.leetcode.二维数组;

import java.util.Random;

public class Random2DArray {
    public static void main(String[] args) {
        int rows = 5; // 行数
        int cols = 5; // 列数
        int[][] array = generateRandom2DArray(rows, cols);
        print2DArray(array);
    }

    // 生成随机二维数组
    public static int[][] generateRandom2DArray(int rows, int cols) {
        Random random = new Random();
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(100); // 生成0到99之间的随机数
            }
        }
        return array;
    }

    // 遍历并打印二维数组
    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < 10) {
                    System.out.print(" " + array[i][j] + " ");
                } else {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

}
