package com.leetcode.easy;
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 只会存在一个有效答案 
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？ 
// Related Topics 数组 哈希表 
// 👍 12535 👎 0


import com.google.common.collect.Maps;

import java.util.HashMap;

public class _01两数之和 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] result = twoSum01(array, 8);
        for (int value : result) {
            System.out.println(value);
        }
    }

    /**
     * 暴力法: 双重for循环,时间复杂度 n*n,空间复杂度 1
     */
    public static int[] twoSum01(int[] nums, int target) {
        if (null == nums || target == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 遍历两次 hash,时间复杂度 n,空间复杂度 n
     */
    public int[] twoSum02(int[] nums, int target) {
        if (null == nums || target == 0) {
            return null;
        }
        // 实际值为 key , 下标为 value
        HashMap<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            // 不能是自身，重复计算
            if (map.containsKey(temp) && map.get(temp) != i) {
                return new int[]{i, map.get(temp),};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 遍历一次 hash， 时间复杂度 n 空间复杂度 1
     */
    public int[] twoSum03(int[] nums, int target) {
        if (null == nums || target == 0) {
            return null;
        }
        // 实际值为 key , 下标为 value
        HashMap<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && map.get(temp) != i) {
                return new int[]{i, map.get(temp),};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}