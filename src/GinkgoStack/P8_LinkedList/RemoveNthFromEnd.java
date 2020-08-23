package GinkgoStack.P8_LinkedList;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 用前后双指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0){
            return head;
        }
        ListNode Head = new ListNode(0);

        Head.next = head;
        ListNode pre = head;
        ListNode end = pre;
        int count = 1;
        while (end != null && count < n){
            end = end.next;
            count++;
        }

        ListNode memory = Head;
        while (end.next != null ){
            memory = pre;
            pre = pre.next;
            end = end.next;
        }

        //删除此时的pre
        memory.next = pre.next;
        return Head.next;
    }
}
