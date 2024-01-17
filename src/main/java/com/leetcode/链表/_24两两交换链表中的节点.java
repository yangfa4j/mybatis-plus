package com.leetcode.链表;


/**
 * @Date 2023/8/7
 * @Author yangfa 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4] 输出：[2,1,4,3] 示例 2：
 * <p>
 * 输入：head = [] 输出：[] 示例 3：
 * <p>
 * 输入：head = [1] 输出：[1]
 */

public class _24两两交换链表中的节点 {


    public static void main(String[] args) {


    }


    public ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode temp; // 临时节点，保存两个节点后面的节点
        ListNode firstnode; // 临时节点，保存两个节点之中的第一个节点
        ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点

        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;
            firstnode = cur.next;
            secondnode = cur.next.next;

            cur.next = secondnode;       // 步骤一
            secondnode.next = firstnode; // 步骤二
            firstnode.next = temp;      // 步骤三
            cur = firstnode; // cur移动，准备下一轮交换

        }


        return dummy.next;
    }


}
