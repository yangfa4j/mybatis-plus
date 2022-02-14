package com.leetcode.SwordToOffer;

import com.leetcode.SwordToOffer.common.HeroNode;

import java.util.Objects;

/**
 * @Date 2022/2/11
 * @Author yangfa
 * @Description
 */
public class _25二叉树遍历 {

    public static void main(String[] args) {
        HeroNode heroNode = HeroNode.init();
        System.out.println("先序遍历结果");
        _25二叉树遍历.preOrder(heroNode);

        System.out.println("中序遍历结果");
        _25二叉树遍历.midOrder(heroNode);

        System.out.println("后序遍历结果");
        _25二叉树遍历.afterOrder(heroNode);

    }

    /**
     * 先序指的是 头 左 右
     *
     * @param heroNode
     */
    public static void preOrder(HeroNode heroNode) {
        System.out.println("heroNode = " + heroNode);
        if (Objects.nonNull(heroNode.getLeft())) {
            _25二叉树遍历.preOrder(heroNode.getLeft());
        }
        if (Objects.nonNull(heroNode.getRight())) {
            _25二叉树遍历.preOrder(heroNode.getRight());
        }
    }

    /**
     * 中序指的是 左 头 右
     *
     * @param heroNode
     */
    public static void midOrder(HeroNode heroNode) {
        if (Objects.nonNull(heroNode.getLeft())) {
            _25二叉树遍历.midOrder(heroNode.getLeft());
        }
        System.out.println("heroNode = " + heroNode);
        if (Objects.nonNull(heroNode.getRight())) {
            _25二叉树遍历.midOrder(heroNode.getRight());
        }
    }

    /**
     * 中序指的是 左 右 头
     *
     * @param heroNode
     */
    public static void afterOrder(HeroNode heroNode) {
        if (Objects.nonNull(heroNode.getLeft())) {
            _25二叉树遍历.afterOrder(heroNode.getLeft());
        }
        if (Objects.nonNull(heroNode.getRight())) {
            _25二叉树遍历.afterOrder(heroNode.getRight());
        }
        System.out.println("heroNode = " + heroNode);
    }
}
