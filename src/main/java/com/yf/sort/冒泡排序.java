package com.yf.sort;

/**
 * @Date 2021/11/8
 * @Author yangfa
 * @Description
 */
public class 冒泡排序 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 1, 5, 7, 6};
        for (int i = 0; i < 冒泡排序.sort(arr).length; i++) {
            System.out.println(arr[i]);
        }
    }

    // 每一次循环，最大的数都放在最右边
    public static int[] sort(int[] array) {
        if (null == array || array.length < 2) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            // -i 是因为比较的数每一次循环都在变少
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    // swap
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
