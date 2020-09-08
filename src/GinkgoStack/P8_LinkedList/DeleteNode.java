package GinkgoStack.P8_LinkedList;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点.
 *
 * 进阶问题：
 *
 */
public class DeleteNode {

    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }

    /**
     *
     * 简单的前后双指针
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/mian-shi-ti-18-shan-chu-lian-biao-de-jie-dian-sh-2/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution {
        /**
         * 待删除节点给的值，那么就必须给出头结点，否则无法删除
         * @param head
         * @param val
         * @return
         */
        public ListNode deleteNode(ListNode head, int val) {
            if(head.val == val)
                return head.next;

            ListNode pre = head, cur = head.next;

            while(cur != null && cur.val != val) {
                pre = cur;
                cur = cur.next;
            }

            if(cur != null) //说明找到这个val了
                pre.next = cur.next;
            return head;
        }
    }

    /**
     * 进阶问题
     * 参数是某个节点，而不是值。
     * 其实只要给出了头结点就能用双指针，但是这个不是最优的。
     *
     * 最优的是信息交换法：把后一个节点的值拿到前面来，然后将指针指向后一个节点的next
     * O(1)
     * 如果题目并不要求返回头节点的话，是不需要头结点这个参数的
     *     作者：z1m
     *     链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/cong-on-dao-o1-by-ml-zimingmeng/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class deleteNode2 {
        public  ListNode deleteNode(ListNode head, ListNode node){
            if (head == null || node == null){
                return null;
            }

            //如果删除的是头结点，并且链表仅仅只有这一个节点
            if(head == node && node.next == null){
                return null;
            }

            if (node.next != null){   // 待删除节点不是尾节点
                //最优的是信息交换法：把后一个节点的值拿到前面来，然后将指针指向后一个节点的next
                ListNode tmp = node.next;
                node.val = tmp.val;
                node.next = tmp.next;
            } else {
                // 否则，说明待删除节点为尾节点,那就不得不从前往后遍历了，目的是找到前指针
                ListNode pre = head;
                while (pre.next != node){
                    pre = pre.next;
                }
                pre.next = null;
            }

            return head;
        }

    }




}
