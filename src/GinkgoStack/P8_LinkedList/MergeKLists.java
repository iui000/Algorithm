package GinkgoStack.P8_LinkedList;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKLists {

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 1.顺序合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list: lists) {
            res = merge2Lists(res, list);
        }
        return res;
    }

    /**
     * 2.分治法
     */
    public ListNode mergeKListsByDiv(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {
            int idx = 0;
            for (int i = 0; i < k; i += 2) {
                if (i == k - 1) {
                    lists[idx++] = lists[i];
                } else {
                    lists[idx++] = merge2Lists(lists[i], lists[i + 1]);
                }
            }
            k = idx;
        }
        return lists[0];
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null? l2: l1;

        return dummyHead.next;
    }

    /**
     * 3.借助优先队列（小顶堆）
     * 思路：
     * 1.K 指针：K 个指针分别指向 K 条链表
     * 2. 使用小根堆求 K个指针的min
     * 时间复杂度：O(KN logK)
     * 空间复杂度：O(K)
     */

    public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node: lists) {//把他们的头结点都加入到优先队列中去
            if (node != null) {
                pq.offer(node);
            }
        }

        //伪头结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            //如果有它有后继，那么加入到堆中
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        //返回实际的头结点
        return head.next;
    }
}
