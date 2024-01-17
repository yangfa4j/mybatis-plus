package com.leetcode.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Date 2023/12/26
 * @Author yangfa
 * @Description 我们以中序遍历为例，在二叉树：听说递归能做的，栈也能做！ (opens new window)中提到说使用栈的话， 无法同时解决访问节点（遍历节点）和处理节点（将元素放进结果集）不一致的情况。
 * <p>
 * 那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。
 * <p>
 * 如何标记呢，就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法也可以叫做标记法。
 */
public class _二叉树统一的遍历代码 {
    public static void main(String[] args) {
        TreeNode init = TreeNode.init();
        _二叉树统一的遍历代码 v = new _二叉树统一的遍历代码();
        v.preorder(init).stream().forEach(System.out::print);
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        // 入栈
        if (root != null) {
            st.push(root);
        }
        // 循环处理
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right != null) {
                    st.push(node.right);  // 添加右节点（空节点不入栈）
                }
                if (node.left != null) {
                    st.push(node.left);    // 添加左节点（空节点不入栈）
                }
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.pop();    // 重新取出栈中元素
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }


    // 中序
    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
            st.push(root);
        }
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right != null) {
                    st.push(node.right);  // 添加右节点（空节点不入栈）
                }
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.left != null) {
                    st.push(node.left);    // 添加左节点（空节点不入栈）
                }
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }


    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
            st.push(root);
        }
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right != null) {
                    st.push(node.right);  // 添加右节点（空节点不入栈）
                }
                if (node.left != null) {
                    st.push(node.left);    // 添加左节点（空节点不入栈）
                }
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }
}


