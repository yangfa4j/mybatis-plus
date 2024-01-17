package com.leetcode.链表;


/**
 * @Date 2023/8/7
 * @Author yangfa 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 示例 1： 输入：head = [1,2,3,4,5] 输出：[5,4,3,2,1]
 * <p>
 * 示例 2： 输入：head = [1,2] 输出：[2,1]
 * <p>
 * <p>
 * 示例 3： 输入：head = [] 输出：[]
 */

public class _206反转链表 {


    public static void main(String[] args) {


    }


    public ListNode reverseList(ListNode head) {
        ListNode pre = null, tem, cur = head;
        while (cur != null) {
            tem = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tem;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    // 反转链表
    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur==null){
            return prev;
        }
        ListNode temp = cur.next;
        cur.next = prev;
        return reverse(cur,temp);
    }


}
