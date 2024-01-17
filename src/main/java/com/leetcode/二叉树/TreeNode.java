package com.leetcode.二叉树;

/**
 * @Date 2023/12/26
 * @Author yangfa
 * @Description
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    Integer val;

    TreeNode() {

    }

    TreeNode(Integer val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode init() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        b.left = d;
        b.right = e;
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        c.left = f;
        c.right = g;
        return a;

    }


}
