package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description
 */

import java.util.HashMap;

/**
 * 找出数组中重复的数字。
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * <p>
 * 请找出数组中任意一个重复的数字。
 */

// 使用 map 来解决
public class _03数组中重复的数字 {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 2, 5, 3};
        int repetition = _03数组中重复的数字.findRepetition(array);
        System.out.println("repetition = " + repetition);

    }

    // 时间复杂度 O(n)  空间复杂度O(n)
    public static int findRepetition(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return nums[i];
            } else {
                map.put(nums[i], i);
            }
        }
        return -1;
    }
}
