package com.leetcode.数组;


import java.util.Arrays;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */


public class _977有序数组的平方 {

    public static void main(String[] args) {
        int[] num = {-1,0,3,5,9,12};
        _977有序数组的平方 解 = new _977有序数组的平方();
        System.out.println(解.sortedSquares(num));
    }

    public int[] sortedSquares(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            nums[i] = nums[j] * nums[j];
            i++;
        }
        Arrays.sort(nums);
        return nums;
    }


    public int[] sortedSquares2(int[] nums) {

        int l = 0;
        int r = nums.length - 1;
        int[] res = new int[nums.length];
        int j = res.length - 1;
        while (l <= r) {
            if (nums[l] * nums[l] > nums[r] * nums[r]) {
                res[j] = nums[l] * nums[l];
                j--;
                l++;
            } else {
                res[j] = nums[r] * nums[r];
                j--;
                r--;
            }
        }
        return res;
    }




}
