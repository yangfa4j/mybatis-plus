package com.leetcode.数组;


import java.util.HashSet;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2] 输出：[2] 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出：[9,4] 解释：[4,9] 也是可通过的
 */

public class _349两个数组的交集 {

    public static void main(String[] args) {


    }

    // 输出的结果是去除的
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();

        for (int i : nums1) {
            set.add(i);
        }


        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }

        return res.stream().mapToInt(x -> x).toArray();

    }

}
