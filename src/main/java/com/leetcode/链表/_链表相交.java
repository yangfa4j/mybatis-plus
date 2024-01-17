package com.leetcode.链表;


import org.springframework.util.StopWatch;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 */

public class _链表相交 {


    public static void main(String[] args) {

        System.out.println(7 / 10);
        System.out.println(7 % 10);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(stopWatch.prettyPrint());

    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return headA;
        }

        ListNode a = headA;
        ListNode b = headB;
        Integer i = 0;
        Integer j = 0;

        // 拿到两个链表的长度
        while (a.next != null) {
            a = a.next;
            j++;
        }
        while (b.next != null) {
            b = b.next;
            i++;
        }
        // 指回头
        a = headA;
        b = headB;

        // 让 a 和 b 只想同一位置
        int gap = Math.abs(i - j);

        if (i > j) {
            for (int k = 0; k < gap; k++) {
                a = a.next;
            }

        } else if (i < j) {
            for (int m = 0; m < gap; m++) {
                b = b.next;
            }
        }

        // 往后遍历，找交点
        while (a.next != null) {
            if (a.val == b.val) {
                return a;
            }
        }
        return null;
    }
}
