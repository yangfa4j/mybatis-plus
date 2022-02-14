package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/8
 * @Author yangfa
 * @Description
 */

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为 1。
 */

public class _11旋转数组的最小数字 {

    public static void main(String[] args) {

    }

    public static int findMin(int[] array) {
        int left = 0;
        int right = array.length - 1;

        int mid = (left + right) / 2;

        while (left < right) {
            if (array[mid] > array[left]) {
                left=mid+1;
            }
            if (array[mid] < array[left]) {
                left=mid+1;
            }
        }
        return 1;

    }
}
