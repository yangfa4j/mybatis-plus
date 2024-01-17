package com.leetcode.数组;


import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */

public class _169多数元素 {

    public static void main(String[] args) {
        int[] num = {3,2,3,2,2,4,4,4};
        _169多数元素 多数元素 = new _169多数元素();
        System.out.println(多数元素.majorityElement(num));

    }

    // 用map 记住了每个数字出现的次数，然后遍历map找到出现次数最多的
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer integer = map.get(nums[i]);
                map.put(nums[i], ++integer);
            } else {
                map.put(nums[i], 1);
            }
        }

        Entry<Integer, Integer> response = null;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            if (response == null || entry.getValue() > response.getValue()) {
                response = entry;
            }
        }

        return response.getKey();

    }




}
