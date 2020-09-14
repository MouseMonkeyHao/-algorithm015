package com.run.weekend_02;

import java.util.HashMap;
import java.util.Map;

/**
 105. 从前序与中序遍历序列构造二叉树  https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 根据一棵树的前序遍历与中序遍历构造二叉树。
 注意: 你可以假设树中没有重复的元素。
 例如， 前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7]
 返回如下的二叉树：
            3
          /  \
         9   20
        / \   / \
        1 2  15  7
 */
public class Day20200912_105 {

    public static void main(String[] args) {
        TreeNode node = buildTree(new int[]{3,9,1,2,20,15,7}, new int[]{1,9,2,3,15,20,7});
        System.out.println(node.val);
    }

    static Map<Integer, Integer> midMap = new HashMap<>();

    // 前序遍历：根 + 左 + 右
    // 中序遍历：左 + 根 + 右
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            midMap.put(inorder[i], i);
        }
        int n = preorder.length;
        // 中序遍历区分左右树，在前序遍历中划分
        return dfs(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    // 递归
    private static TreeNode dfs(int[] preorder, int[] inorder, int preLeft, int preRight, int midLeft, int midRight) {
        if (preLeft > preRight){
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        TreeNode node = new TreeNode(preorder[preLeft]);
        // 中序根节点位置
        int midRoot = midMap.get(node.val);
        // 左子树中的节点数目
        int leftSize = midRoot - midLeft;
        node.left = dfs(preorder, inorder, preLeft + 1, preLeft + leftSize, midLeft, midRoot - 1);
        node.right = dfs(preorder, inorder, preLeft + leftSize + 1, preRight, midRoot + 1, midRight);
        return node;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}


