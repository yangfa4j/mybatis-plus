package com.yf.leetcode._02_addtwonumbers;


/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 *   遍历链表，记住进位的数，carry，/ 与 % 的运用
 */
class Solution {

    public static void main(String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node2 = new ListNode(2);
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        ListNode node8 = new ListNode(8);
        ListNode node7 = new ListNode(7);
        ListNode node6 = new ListNode(6);
        node8.next = node7;
        node7.next = node6;

        new Solution().addTwoNumbers(node3, node8);


    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        ListNode p = l1, q = l2, curr = headNode;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return headNode.next;
    }
}