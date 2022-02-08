package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description 操作的是当前节点，有 prev、next、curr 三个指针
 */
public class _24反转链表 {

    public static void main(String[] args) {
        ListNode init = ListNode.init(true);
        System.out.println(ListNode.print(init));
        ListNode init2 = ListNode.init(false);
        System.out.println(ListNode.print(init2));
    }

    public static ListNode reversal(ListNode head) {
        ListNode prev, curr, next;
        curr = head;
        prev = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

