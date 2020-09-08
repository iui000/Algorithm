package GinkgoStack.P8_LinkedList;

import java.util.Stack;


/**
 * 两个链表生成相加链表
 * 个位数在末尾
 *
 */
public class Problem_10_AddTwoLinkedLists {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 方法一 利用栈
     * @param head1
     * @param head2
     * @return
     */
	public static Node addLists1(Node head1, Node head2) {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();


		while (head1 != null) {
			s1.push(head1.value);
			head1 = head1.next;
		}
		while (head2 != null) {
			s2.push(head2.value);
			head2 = head2.next;
		}

		int carry = 0;

		Node resHead = null;
		Node resPre = null;
		while (!s1.isEmpty() || !s2.isEmpty()) {
            int v1 = 0;
            int v2 = 0;
            int sum = 0;
		    //取栈顶即可
			v1 = s1.isEmpty() ? 0 : s1.pop();
			v2 = s2.isEmpty() ? 0 : s2.pop();
			sum = v1 + v2 + carry;
            carry = sum / 10;

            //结果链表头插
			resPre = resHead;
			resHead = new Node(sum % 10);
			resHead.next = resPre;
		}

		if (carry == 1) {
			resPre = resHead;
			resHead = new Node(1);
			resHead.next = resPre;
		}
		return resHead;
	}

    /**
     * 利用链表反转，可以节省栈的空间
     * @param head1
     * @param head2
     * @return
     */
	public static Node addLists2(Node head1, Node head2) {
	    //先反转
		head1 = reverseList(head1);
		head2 = reverseList(head2);

		int carry = 0;//进位

		Node cur1 = head1;
		Node cur2 = head2;

		Node resHead = null;
		Node resPre = null;

		while (cur1 != null || cur2 != null) {
            int v1 = 0;
            int v2 = 0;
            int sum = 0;
            v1 = cur1 != null ? cur1.value : 0;
			v2 = cur2 != null ? cur2.value : 0;

			sum = v1 + v2 + carry;
            carry = sum / 10;

			//结果链表头插
			resPre = resHead;
			resHead = new Node(sum % 10);
			resHead.next = resPre;

			//下一个指针
			cur1 = cur1 != null ? cur1.next : null;
			cur2 = cur2 != null ? cur2.next : null;
		}
		if (carry == 1) {//最后别忘了进位
			resPre = resHead;
			resHead = new Node(1);
			resHead.next = resPre;
		}

		//再反转回来
		reverseList(head1);
		reverseList(head2);
		return resHead;
	}

    /**
     * 反转链表
     * @param head
     * @return
     */
	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

    /**
     * 测试
     * @param head
     */
	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(9);
		head1.next = new Node(9);
		head1.next.next = new Node(9);

		Node head2 = new Node(1);

		printLinkedList(head1);
		printLinkedList(head2);
		printLinkedList(addLists1(head1, head2));
		printLinkedList(addLists2(head1, head2));

	}

}
