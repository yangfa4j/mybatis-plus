package com.yf.sort;

/**
 * @Date 2021/11/8
 * @Author yangfa
 * @Description
 */
public class 选择排序 {

    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 5, 7, 6};
        for (int i = 0; i < 选择排序.sort(arr).length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] sort(int[] array) {
        if (null == array || array.length < 2) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            // 从头到尾最小值的位置
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                // 循环一轮找到最小值的下标
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // 下标不相等，可以交换
            if (i != min) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        return array;
    }
}

