package com.yf.leetcode.easy;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 2072 👎 0


public class _21合并两个有序链表 {

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node2.next = node3;
        node3.next = node4;

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node5.next = node6;
        node6.next = node7;
        ListNode listNode = _21合并两个有序链表.mergeTwoLists(node2, node5);

//        ListNode listNode = _21合并两个有序链表.mergeTwoLists01(new ListNode(1), null);

        System.out.println("listNode = " + listNode);
    }

    /**
     * 暴力循环 类似于 归并排序中合并两个数组，这里是合并两个链表
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode headNode = new ListNode(0);
        if (null == list1 && null == list2) {
            return null;
        }
        ListNode p = list1, q = list2, curr = headNode;
        while (p != null && q != null) {
            if (p.val < q.val) {
                curr.next = p;
                p = p.next;
            } else {
                curr.next = q;
                q = q.next;
            }
            curr = curr.next;
        }
        // 接上剩下的
        if (null == p) {
            curr.next = q;
        }
        if (null == q) {
            curr.next = p;
        }
        return headNode.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}