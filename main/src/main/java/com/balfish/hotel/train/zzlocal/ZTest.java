package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2018/2/28 PM2:39.
 *
 * @See http://blog.csdn.net/gu_solo/article/details/48847427
 * @See https://www.cnblogs.com/qiuyong/p/6561205.html
 */
class Node {
    public int num;
    public Node next;

    public Node(int num) {
        this.num = num;
    }
}

public class ZTest {

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

//        Node cur = delete(node1, 5);
        Node cur = insert(node1, 2, 999);

        while (cur != null) {
            System.out.println(cur.num);
            cur = cur.next;
        }
    }

    /**
     * 假设在anchor数据之后添加一个node, node.num = insertNum
     */
    private static Node insert(Node n, int anchor, int insertNum) {
        Node preN = n;

        while (n != null) {
            if (n.num == anchor) {
                Node newNode = new Node(insertNum);
                newNode.next = n.next.next;
                n.next = newNode;
                break;
            }
            n = n.next;
        }
        return preN;
    }

    /**
     * 删除指定元素
     */
    private static Node delete(Node n, int deleteNum) {

        Node preN = n;
        if (n == null) { // 空的话特殊处理
            return null;
        }

        if (n.num == deleteNum) { // 如果是第一个，返回下一个指针
            return n.next;
        }

        while (n != null) {
            Node next = n.next;
            if (next != null && next.num == deleteNum) {
                n.next = n.next.next; // 第三个的值赋值给第二个
            }
            n = n.next;
        }
        return preN;
    }
}
