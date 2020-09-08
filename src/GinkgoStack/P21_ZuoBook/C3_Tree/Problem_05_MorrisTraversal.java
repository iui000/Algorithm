package GinkgoStack.P21_ZuoBook.C3_Tree;

public class Problem_05_MorrisTraversal {

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) { // 如果当前cur有左子树
				// 找到cur左子树上最右的节点
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				// 从上面的while里出来后，mostRight就是cur左子树上最右的节点
				if (mostRight.right == null) { // 如果mostRight.right是指向null的
					mostRight.right = cur; // 让其指向cur
					cur = cur.left; // cur向左移动
					continue; // 回到最外层的while，继续判断cur的情况
				} else { // 如果mostRight.right是指向cur的
					mostRight.right = null; // 让其指向null
				}
			}

			//有两种情况会执行到这里
			// 第一，cur如果没有左子树，那么cur向右移动
			// 第二，或者cur左子树上最右节点的右指针是指向cur的，那么同样cur也向右移动
			cur = cur.right;
		}
	}

    /**
     * 中序遍历 左-中-右
     * 在Morris遍历的基础上，有两种情况会打印节点：
     * 1.对于cur只能到达一次的节点（无左子树的节点），cur到达时直接打印。
     * 2.对于cur可以到达两次的节点（有左子树的节点），cur第一次到达时不打印，第二次到达时打印
     *
     * @param head
     */
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			//前面说的两种情况都会执行到这里来，所以就一句打印
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		System.out.println();
	}

    /**
     * 先序遍历 中-左-右
     * 在Morris遍历的基础上，有两种情况会打印节点：
     * 1.对于cur只能到达一次的节点（无左子树的节点），cur到达时直接打印。
     * 2.对于cur可以到达两次的节点（有左子树的节点），cur第一次到达时不打印，第二次到达时不打印。【这和中序遍历时相反的】
     *
     * @param head
     */
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {//cur左子树的最右下角节点的右指针时空的，说明这一次是第一访问cur
					mostRight.right = cur;
					System.out.print(cur.value + " ");//根据规则，第一次到达cur时要打印
					cur = cur.left;
					continue;
				} else {//第二次到达时不打印
					mostRight.right = null;
				}
			} else {//cur没有左子树，则直接打印cur
				System.out.print(cur.value + " ");
			}
			cur = cur.right;
		}
		System.out.println();
	}

    /**
     * 后序遍历 左-右-中
     * 1.对于cur只能到达一次的节点（无左子树的节点），直接跳过，转到右子树，没有打印行为。
     * 2.对于cur可以到达两次的任何节点（有左子树的节点）X，cur第一次到达X时没有打印行为；当第二次到达X时，逆序打印X左子树的右边界；
     * 3.cur遍历完成后，逆序打印整棵树的右边界
     * @param head
     */
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					//当第二次到达X时，逆序打印X左子树的右边界；
					printEdge(cur.left);
				}
			}
			cur = cur.right;
		}
        //cur遍历完成后，逆序打印整棵树的右边界
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
	    //先将右边界翻转一次，类似于链表反转
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		//打印完了，再反转回来
		reverseEdge(tail);
	}

	//反转以该节点为头结点的右边界，类似于链表反转
	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary P11_Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head);
		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

}
