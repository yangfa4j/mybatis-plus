package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description
 */

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class _04二维数组中的查找 {

    public static void main(String[] args) {
        int[][] array = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        boolean target = _04二维数组中的查找.findTarget2(array, 20);
        System.out.println("target = " + target);

    }

    // 暴力遍历
    public static boolean findTarget1(int[][] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (nums[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  从数组的最左下角位置开始去搜索整个二维数组
     *  1、当发现当前遍历的元素大于 target 时，意味着这个元素后面的所有元素也都大于 target，那么就不用去搜索这一行了
     *  2、当发现当前遍历的元素小于 target 时，意味着这个元素上面的所有元素也都小于 target，那么就不用去搜索这一列了
     *
     *  [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     *  ]
     */
    public static boolean findTarget2(int[][] nums, int target) {
        // 定位最左下角的元素
       int i = nums.length-1;
       int j = 0;

        while (i >= 0 && j < nums[i].length){
            // 当发现当前遍历的元素大于 target 时，意味着这个元素后面的所有元素也都大于 target，此时需要看上一行
            if (nums[i][j] > target) {
                i--;
            }
            // 当发现当前遍历的元素小于 target 时，意味着这个元素上面的所有元素也都小于 target，那么就不用去搜索这一列了，此时需要看后一列
            else if (nums[i][j] < target) {
                j++;
            }else {
                return true;
            }
        }
       return false;
    }
}
