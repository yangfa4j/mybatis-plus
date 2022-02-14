package com.leetcode.SwordToOffer;

/**
 * @Date 2022/2/7
 * @Author yangfa
 * @Description
 */

import com.leetcode.SwordToOffer.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 */
public class _06从尾到头打印链表 {

    public static void main(String[] args) {
        ListNode init = ListNode.init(true);
        Integer[] integers = reversalToArray2(init);
        System.out.println("Arrays.toString(integers) = " + Arrays.toString(integers));
    }

    // 解法1 反转链表，然后遍历转换成数组
    public static Integer[] reversalToArray(ListNode listNode) {
        // 先反转链表
        ListNode prev, curr, next;
        prev = null;
        curr = listNode;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        // 遍历反转后的链表，转换成数组
        ArrayList<Integer> arr = new ArrayList<>();
        while (prev != null) {
            arr.add(prev.val);
            prev = prev.next;
        }
        return arr.toArray(new Integer[0]);
    }

    // 解法2 使用 stack
    public static Integer[] reversalToArray2(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        // 遍历链表，把数组放在栈里面
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            arr.add(pop);
        }
        return arr.toArray(new Integer[0]);
    }

}


