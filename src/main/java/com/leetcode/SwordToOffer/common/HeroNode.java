package com.leetcode.SwordToOffer.common;

/**
 * 二叉树
 */
public class HeroNode {
    public int no;
    public String name;
    /**
     * 左节点
     */
    public HeroNode left;

    /**
     * 右节点
     */
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public static HeroNode init(){
        HeroNode 宋江 = new HeroNode(1, "宋江");
        HeroNode 卢俊义 = new HeroNode(2, "卢俊义");
        HeroNode 吴用 = new HeroNode(3, "吴用");
        宋江.setLeft(卢俊义);
        宋江.setRight(吴用);
        HeroNode 公孙胜 = new HeroNode(4, "公孙胜");
        HeroNode 关胜 = new HeroNode(5, "关胜");
        卢俊义.setLeft(公孙胜);
        卢俊义.setRight(关胜);
        HeroNode 林冲 = new HeroNode(6, "林冲");
        HeroNode 秦明 = new HeroNode(7, "秦明");
        吴用.setLeft(林冲);
        吴用.setRight(秦明);
        return 宋江;
    }
}