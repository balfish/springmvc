package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2017/12/28 AM10:53.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Test1218 {
    public static void main(String[] args) {

        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        node11.next = node12;
        node12.next = node13;

        ListNode n1 = node11;
//        while (node11 != null) {
//            System.out.println(node11.val);
//            node11 = node11.next;
//        }

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        ListNode n2 = node21;
//        while (node21 != null) {
//            System.out.println(node21.val);
//            node21 = node21.next;
//        }

        ListNode listNode = mergeTwoLists(n1, n2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ret = null;
        ListNode head = null;
        ListNode tmp;
        int count = 1;
        boolean flag = false;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }

            if (l1 == null) {
                int var2 = l2.val;
                tmp = new ListNode(var2);
                l2 = l2.next;
            } else if (l2 == null) {
                int var1 = l1.val;
                tmp = new ListNode(var1);
                l1 = l1.next;
            } else {
                int val2 = l2.val;
                int val1 = l1.val;
                if (val1 <= val2) {
                    tmp = new ListNode(val1);
                    l1 = l1.next;
                } else {
                    tmp = new ListNode(val2);
                    l2 = l2.next;
                }
            }

            if (count == 1) {
                ret = tmp;
                if (!flag) {
                    head = tmp;
                    flag = true;
                }
            } else {
                ret.next = tmp;
                ret = ret.next;
            }
            count++;
        }
        return head;
    }
}
