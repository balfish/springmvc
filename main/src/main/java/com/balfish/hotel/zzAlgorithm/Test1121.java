package com.balfish.hotel.zzAlgorithm;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yhm on 2017/11/21 AM11:26.
 */
class Node {
    private Node left;
    private Node right;
    private char c;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}

public class Test1121 {
    private static String str = "abd##eg##h##c#f##";
    private static int count = str.length();

    public static void main(String[] args) {
        Node node = initTree();
        System.out.print("先序遍历结果:");
        preOrder(node);
        System.out.println();
        System.out.print("中序遍历结果:");
        inOrder(node);
        System.out.println();
        System.out.print("后序遍历结果:");
        postOrder(node);
        System.out.println();
        System.out.print("层次遍历结果:");
        levelOrder(node);
        System.out.println();

        System.out.print("二叉树的深度是:");
        System.out.println(getDepth(node));
        System.out.println(getDepth2(node));
    }

    /**
     * 递归初始化树
     */
    private static Node initTree() {
        char c = str.charAt(str.length() - count--);
        if (c == '#') {
            return null;
        }

        Node node = new Node();
        node.setC(c);
        node.setLeft(initTree());
        node.setRight(initTree());
        return node;
    }

    /**
     * 非递归先序遍历树
     */
    private static void preOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            if (top == null) {
                continue;
            }
            System.out.print(top.getC());
            stack.push(top.getRight());
            stack.push(top.getLeft());
        }
    }

    /**
     * 非递归中序遍历树
     */
    private static void inOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.print(node.getC());
                node = node.getRight();
            }
        }
    }

    /**
     * 非递归后序遍历树
     */
    private static void postOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Node flag = new Node();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }

            node = stack.peek();
            if (node.getRight() == null || node.getRight() == flag) {
                System.out.print(node.getC());
                flag = node;
                stack.pop();
                if (stack.isEmpty()) {
                    return;
                }
                node = null;
            } else {
                node = node.getRight();
            }
        }
    }

    /**
     * 层次遍历遍历树
     */
    private static void levelOrder(Node node) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node first = queue.remove();
            System.out.print(first.getC());
            if (first.getLeft() != null) {
                queue.add(first.getLeft());
            }
            if (first.getLeft() != null) {
                queue.add(first.getRight());
            }
        }
    }

    /**
     * 递归求树深度
     */
    private static int getDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int left = getDepth(node.getLeft());
        int right = getDepth(node.getRight());
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 非递归求树深度
     */
    private static int getDepth2(Node node) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(node);
        int cur;
        int last;
        int level = 0;

        while (!queue.isEmpty()) {
            cur = 0;
            last = queue.size();
            while (cur < last) {
                Node first = queue.remove();
                cur++;
                if (first.getLeft() != null) {
                    queue.add(first.getLeft());
                }
                if (first.getLeft() != null) {
                    queue.add(first.getRight());
                }
            }
            level++;
        }
        return level;
    }
}
