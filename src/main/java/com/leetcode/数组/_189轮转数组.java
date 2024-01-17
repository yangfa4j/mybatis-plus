package com.leetcode.数组;


/**
 * @Date 2023/8/7
 * @Author yangfa
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */


public class _189轮转数组 {

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7};
        _189轮转数组 解 = new _189轮转数组();
        解.rotate(num, 3);
    }

    // 数学规律。每一个数字 经过 k 次轮转后，其数组下标为： (i+k)%n
    // 做数学题，找规律
    public void rotate(int[] nums, int k) {
        {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; ++i) {
                newArr[(i + k) % n] = nums[i];
            }
            System.arraycopy(newArr, 0, nums, 0, n);

        }


    }
}
