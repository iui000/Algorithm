package GinkgoStack.P8_LinkedList;

/**
 * 11.两个单链表相交的一系列问题（找环、找交点）（将★★★★）
 *
 * 有三个问题：
 * 1.判断一个链表是否有环，如果有环，返回第一个入环的点，否则返回null
 * 2.两个无环链表，相交则返回第一个公共节点，否则返回空；
 * 3.两个有环链表，相交则返回第一个公共节点，否则返回空；
 *
 * 如果其中一个链表有环，而另外一个无环，那么他们是不可能相交的，直接返回null
 *
 */

public class Problem_11_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 第一个问题：
     * 判断一个链表是否有环，如果有环，返回第一个入环的点，否则返回null
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next; // n1 -> slow
        Node fast = head.next.next; // n2 -> fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        //快指针返回头部，然后两个指针都以1为步长继续走
        fast = head; // n2 -> walk again from head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**
     *      * 第二个问题：
     *      * 判断两个无环链表是否相交，相交则返回第一个公共节点，否则返回空。
     *      先走差值k步
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {//既然都是两个无环链表了，当然最后都是null了，何必要判断？
            return null;
        }


        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 补充第二种写法，简洁的双指针
     * @param headA
     * @param headB
     * @return
     */
    public Node noLoop2(Node headA, Node headB) {
        if (headA == null || headB == null)
            return null;
        Node pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 问题三：加入两个链表都有环，找到相交节点
     * @param head1
     * @param loop1 入环点
     * @param head2
     * @param loop2
     * @return
     */
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if (loop1 == loop2) {
		    //第一种情况，如果loop1 == loop2，那么是这样一个形状  >--O,相交的第一个点在入环点及之前
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}


			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
		    //loop1 ！= loop2 ,那么是可能是这样一个形状 --O--，同一个环。也可能是--O,--O，不相交。
            // 从loop1出发转圈，如果途中没有遇到loop2，那么说明这两个链表不相交，否则相交，任意返回loop1或者loop2
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

    /**
     * 将前面的情况综合起来，应对有环或者无环
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {//都无环
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {//都有环
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
