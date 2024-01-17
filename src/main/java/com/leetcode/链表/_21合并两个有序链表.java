package com.leetcode.链表;




/**
 * @Date 2023/8/7
 * @Author yangfa
 * <p></p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4] 输出：[1,1,2,3,4,4]
 * <p></p>
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = [] 输出：[]
 * <p></p>
 */

public class _21合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode p = list1, q = list2, curr = head;
        while (p != null && q != null) {
            int x = p.val;
            int y = q.val;
            if (x <= y) {
                curr.next = new ListNode(x);
                p = p.next;
            } else {
                curr.next = new ListNode(y);
                q = q.next;
            }
            curr = curr.next;
        }
        if (p != null) {
            curr.next = p;
        }
        if (q != null) {
            curr.next = q;
        }
        return head.next;
    }

}
