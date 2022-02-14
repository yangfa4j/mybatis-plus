package com.leetcode.SwordToOffer.common;

import java.util.ArrayList;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description 单链表
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static String print(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode curr = listNode;
        while (null != curr) {
            list.add(curr.val);
            curr = curr.next;
        }
        return list.toString();
    }

    public static ListNode init(Boolean isASC) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        ListNode result;
        if (isASC) {
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;
            node5.next = node6;
            node6.next = node7;
            result = node1;
        } else {
            node7.next = node6;
            node6.next = node5;
            node5.next = node4;
            node4.next = node3;
            node3.next = node2;
            node2.next = node1;
            result = node7;
        }
        return result;
    }


}
