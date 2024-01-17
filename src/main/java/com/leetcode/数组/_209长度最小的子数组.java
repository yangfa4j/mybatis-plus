package com.leetcode.数组;


/**
 * @Date 2023/8/7
 * @Author yangfa 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4] 输出：1 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1] 输出：0
 */


public class _209长度最小的子数组 {

    public static void main(String[] args) {
        int[] num = {2, 3, 1, 2, 4, 3};
        _209长度最小的子数组 解 = new _209长度最小的子数组();
        System.out.println(解.minSubArrayLen(7, num));
    }

    // 暴力双重循环解决
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 滑动窗口的思想
    public int minSubArrayLen2(int target, int[] nums) {
        int left = 0;
        int sum = 0; // 窗口里面元素的集合
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                int length = right - left + 1;
                result = Math.min(result, length);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


}
