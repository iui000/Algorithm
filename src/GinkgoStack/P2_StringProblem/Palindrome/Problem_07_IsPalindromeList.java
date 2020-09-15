package GinkgoStack.P2_StringProblem.Palindrome;

import java.util.Stack;


/**
 * 判断一个链表是否为回文结构
 * 三种解法
 * 最优解时空 O（n）+O(1)
 */
public class Problem_07_IsPalindromeList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 朴素的解法，栈
     * @param head
     * @return
     */
	// need n extra space
	public static boolean isPalindrome1(Node head) {
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

    /**
     * 解法1的优化
     * 栈中只压一半的数据，这里选择了将右半边压入栈中
     * @param head
     * @return
     */
	// need n/2 extra space
	public static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node right = head.next;
		Node fast = head;//快指针
		while (fast.next != null && fast.next.next != null) {
			right = right.next;
			fast = fast.next.next;
		}

		//快指针走到尾部时，right就刚好到右半区的第一个节点，无论长度奇偶数都适用
		Stack<Node> stack = new Stack<Node>();
		while (right != null) {
			stack.push(right);
			right = right.next;
		}

		//接下来匹配
		while (!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

    /**
     *
     * 先反转右半边，判断完了，再恢复回来
     * 思路虽然也很简单，但是实现并不简单
     *
     * 空间O（1）
     * @param head
     * @return
     */
	// need O(1) extra space
	public static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}

        //快指针走到尾部时，慢指针就刚好到中间节点，无论长度奇偶数都适用
		Node p1 = head;
		Node p2 = head;//走两步,快指针
		while (p2.next != null && p2.next.next != null) { // find mid node
			p1 = p1.next; // p1 -> mid
			p2 = p2.next.next; // p2 -> end
		}

		//p2改为指向有半边的第一个节点
		p2 = p1.next; // p2 -> right part first node
		p1.next = null; // mid.next -> null

        //接下来开始反转右半边
		Node tmpNext = null;
		while (p2 != null) { // right part convert
			tmpNext = p2.next; // tmpNext -> save next node
			p2.next = p1; // p1充当pre的角色，p2充当cur的角色
			p1 = p2; // p1 move
			p2 = tmpNext; // p2 move
		}

		//让tmpNext指向右半边反转后的头结点，就是pre角色的p1
		tmpNext = p1; // tmpNext-> save last node
		p2 = head;// p2 -> left first node ,让p2指向左半边的头结点
		boolean res = true;
		while (p1 != null && p2 != null) { // check palindrome
			if (p1.value != p2.value) {
				res = false;
				break;
			}
			p1 = p1.next; // left to mid
			p2 = p2.next; // right to mid
		}

		//最后得恢复右半边，才能返回结果
		p1 = tmpNext.next;
		tmpNext.next = null;//tmpNext这个时候应该变成尾巴
		while (p1 != null) { // recover list
			p2 = p1.next;//p1此时是cur的角色,p2是next的角色
			p1.next = tmpNext;//tmpNext是pre的角色
			tmpNext = p1;
			p1 = p2;
		}


		return res;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}

}
