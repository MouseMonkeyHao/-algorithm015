package com.run.week_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 589. N叉树的前序遍历  https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
 给定一个 N 叉树，返回其节点值的前序遍历。

 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class Day20200905_589 {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list = new ArrayList<>();
        list.add(node3);
        list.add(node2);
        list.add(node4);
        node1.children = list;
        List<Node> list1 = new ArrayList<>();
        list1.add(node5);
        list1.add(node6);
        node3.children = list1;
        System.out.println(preorder(node1));
    }

    // 迭代
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node node = stack.pollLast();
            list.add(node.val);
            if (node.children != null) {
                Collections.reverse(node.children);
                for (Node child : node.children) {
                    stack.add(child);
                }
            }
        }
        return list;
    }

    // 递归
    public static List<Integer> preorder_01(Node root) {
        List<Integer> list = new ArrayList<>();
        preShow(root, list);
        return list;
    }

    private static void preShow(Node root, List<Integer> list){
        if (root == null)
            return;
        System.out.println(root.val);
        list.add(root.val);
        if (root.children != null)
            for (Node child : root.children) {
                preShow(child, list);
            }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
