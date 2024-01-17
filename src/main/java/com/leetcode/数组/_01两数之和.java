package com.leetcode.数组;

import org.springframework.util.StopWatch;

import java.util.HashMap;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 【两数之和】 给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * @Author jiawei huang
 * @Since 2019年8月8日
 * @Version 1.0
 */

public class _01两数之和 {
    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int[] array = {2, 7, 11, 15};
        int[] ints = twoSum(array, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

        System.out.println(stopWatch.prettyPrint());

    }

    // 暴力循环
    public static int[] twoSumBF(int[] nums, int target) {
        int[] response = new int[2];
        if (nums == null || nums.length == 0) {
            return response;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    response[0] = i;
                    response[1] = j;
                }
            }
        }
        return response;

    }

    // hash 表法
    public static int[] twoSum(int[] nums, int target) {
        int[] response = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int num : nums) {
            int key = target - num;
            if (map.get(key) != null) {
                int x = map.get(num);
                int y = map.get(key);
                if (x == y) {
                    continue;
                }
                response[0] = x;
                response[1] = y;
                return response;
            }
        }
        return null;
    }


}
