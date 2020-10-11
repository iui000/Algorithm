package GinkgoStack.P8_LinkedList;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class RemoveDuplicatesFromSortedListII82 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /**
     * 注意，不是保留一个元素，而是将重复的元素全部删除，因此要记住重复元素前面的指针位置
     *     作者：wang_ni_ma
     *     链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/san-chong-jie-fa-duo-tu-zhan-shi-82-shan-chu-pai-x/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if(head==null || head.next==null) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode a = dummy;
            ListNode b = head.next;
            while(b!=null) {
                if(a.next.val!=b.val) {
                    a = a.next;
                    b = b.next;
                }
                else {
                    while(b!=null && a.next.val==b.val) {
                        b = b.next;
                    }

                    a.next = b;
                    //b指针在while中判断完后，可能指向了null，这里需要处理边界问题
                    b = (b==null) ? null : b.next;
                }
            }
            return dummy.next;
        }
    }


}
