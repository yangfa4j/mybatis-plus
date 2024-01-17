package com.leetcode.数组;


import java.util.HashMap;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * <p>
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * <p>
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。 返回 k 。
 */

public class _26删除有序数组中的重复项 {

    public static void main(String[] args) {


    }

    // 使用map 保证唯一性
    public int removeDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                nums[k] = nums[i];
                k++;
            }
            map.put(nums[i], i);
        }
        return k;
    }

    // 双指针
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int slow = 1, fast = 1;
        while (fast < n) {
            if (nums[slow - 1] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }


}
