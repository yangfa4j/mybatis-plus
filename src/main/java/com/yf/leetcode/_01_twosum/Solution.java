package com.yf.leetcode._01_twosum;

import java.util.HashMap;

/**
 * @Date 2021/6/28
 * @Author yangfa
 * @Description
 */
public class Solution {

    public static void main(String[] args) {
        int[] ints = new int[]{3, 2, 4};
        twoSum02(ints, 6);

    }


    /**
     * 暴力法 双重for循环 时间复杂度 n*n 空间复杂度 1
     */
    public static int[] twoSum01(int[] nums, int target) {
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
     * 遍历两次 hash，key value 为当前元素 时间复杂度 n 空间复杂度 n
     */
    public static int[] twoSum02(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // 不能是自身，重复计算
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 遍历一次 hash，key value 为当前元素 时间复杂度 1 空间复杂度 n
     */
    public static int[] twoSum03(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
