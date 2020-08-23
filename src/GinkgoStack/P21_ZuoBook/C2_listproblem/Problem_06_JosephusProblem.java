package GinkgoStack.P21_ZuoBook.C2_listproblem;

public class Problem_06_JosephusProblem {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 普通的解法
     * 时间复杂度是O(n*m)
     * @param head
     * @param m
     * @return
     */
	public static Node josephusKill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}

		//先走一圈，找到尾节点
		Node last = head;
		while (last.next != head) {
			last = last.next;
		}

		int count = 0;
		while (head != last) {
			if (++count == m) {//数到m，就杀掉该节点
				last.next = head.next;//改变引用链，相当于删除了此时head所指的节点
				count = 0;
			} else {
				last = last.next;
			}
			head = last.next;//更新head为last的下一个节点，
		}
		return head;
	}

    /**
     * 根据新老编号之间的对应关系，递归求得那个幸存者(在最后，链表的大小必然就是1，幸存者的编号也就是 1)
     * 递归一共n层，时间复杂度为O(n)
     * @param head 首节点
     * @param m 报数
     * @return
     */
	public static Node josephusKill2(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node cur = head.next;


		int tmp = 1; // tmp -> list size
		while (cur != head) {//获得总人数
			tmp++;
			cur = cur.next;
		}

		//根据总人数i、报数m、和新老编号的对应关系：老编号 = （新编号 + m-1）% i + 1; 递归即可获得最终的幸存者编号
		tmp = getLive(tmp, m); // tmp -> service node position
		while (--tmp != 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}

    /**
     * 据总人数i、报数m、和新老编号的对应关系：老编号 = （新编号 + m-1）% i + 1;
     * 递归即可获得最终的幸存者编号
     * @param i 人数
     * @param m 报数
     * @return
     */
	public static int getLive(int i, int m) {
		if (i == 1) {
			return 1;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = head1;
		printCircularList(head1);
		head1 = josephusKill1(head1, 3);
		printCircularList(head1);

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3);
		printCircularList(head2);

	}

}