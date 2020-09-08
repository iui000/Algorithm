package GinkgoStack.P8_LinkedList;

public class OddEvenLinkedList328 {
    /**
     *     作者：LeetCode
     *     链接：https://leetcode-cn.com/problems/odd-even-linked-list/solution/qi-ou-lian-biao-by-leetcode/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) {
           this.val = val;
           this.next = next;
       }
    }

    public class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null)
                return null;

            ListNode odd = head, even = head.next, evenHead = even;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }

            //将奇链表的尾巴与偶链表头部链接起来
            odd.next = evenHead;
            return head;
        }
    }


}
