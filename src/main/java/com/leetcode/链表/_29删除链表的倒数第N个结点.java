package com.leetcode.链表;


/**
 * @Date 2023/8/7
 * @Author 示例 1： 输入：head = [1,2,3,4,5], n = 2 输出：[1,2,3,5] 示例 2：
 * <p>
 * 输入：head = [1], n = 1 输出：[] 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1 输出：[1]
 */

public class _29删除链表的倒数第N个结点 {


    public static void main(String[] args) {


    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);

        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }


}
