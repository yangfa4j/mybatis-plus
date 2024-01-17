package com.leetcode.二维数组;

/**
 * @Date 2024/1/2
 * @Author yangfa
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        int array[][] = new int[][]{

                {1, 2, 3},

                {4, 5, 6},

                {7, 8, 9}

        };

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                System.out.println("null" + " ");
                continue;
            }//当二维数组某一行为空时直接跳过循环遍历下一行
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }


    }
}
