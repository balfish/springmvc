package com.balfish.hotel.train.zzlocal;

import java.util.Stack;

/**
 * Created by yhm on 2018/1/2 AM11:36.
 * <p>
 * 专为leetcode刷题的类
 * 好好算法，天天向上 -v-
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(3);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = null;
        treeNode4.right = null;

        treeNode5.left = null;
        treeNode5.right = null;

        treeNode6.left = null;
        treeNode6.right = null;

        treeNode7.left = null;
        treeNode7.right = null;

        print(treeNode1);
        System.out.println("---------");
        print1(treeNode1);
    }


    public static void print(TreeNode node) {
        System.out.println(node.val);

        if (node.left != null) {
            print(node.left);
        }

        if (node.right != null) {
            print(node.right);
        }

    }

    public static void print1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.empty()) {
            TreeNode tmpNode = stack.pop();
            System.out.println(tmpNode.val);

            if (tmpNode.right != null) {
                stack.push(tmpNode.right);
            }
            if (tmpNode.left != null) {
                stack.push(tmpNode.left);
            }
        }
    }


    public static boolean isSymmetric(TreeNode root) {
        return false;
    }

}
