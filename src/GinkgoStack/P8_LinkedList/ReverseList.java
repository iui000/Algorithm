package GinkgoStack.P8_LinkedList;

/**
 * 206. 反转链表
 */
public class ReverseList {
    /**
     * Definition for singly-linked list.
     */
     class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    /**
     * 前后双指针
     * @param head
     * @return
     */
     public ListNode reverseList(ListNode head) {
         if(head == null){
             return null;
         }
         ListNode pre = null;
         ListNode cur = head;
         while (cur.next != null){
             //注意，必须要这个临时指针记住cur的后继
             ListNode tmp = cur.next;
             cur.next = pre;
             pre = cur;
             cur = tmp;
         }
         //注意，把最后一个节点的next指向原来的倒数第二个节点
         cur.next = pre;
         return cur;
     }

}
