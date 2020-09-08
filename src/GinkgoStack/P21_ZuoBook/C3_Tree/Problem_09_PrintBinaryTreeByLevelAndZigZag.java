package GinkgoStack.P21_ZuoBook.C3_Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层打印和 ZigZag输出
 */
public class Problem_09_PrintBinaryTreeByLevelAndZigZag {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 层序输出
     * @param head
     */
	public static void printByLevel(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		int level = 1;
		Node last = head;
		Node nLast = null;
		queue.offer(head);
		System.out.print("Level " + (level++) + " : ");
		while (!queue.isEmpty()) {
			head = queue.poll();
			System.out.print(head.value + " ");
			if (head.left != null) {
				queue.offer(head.left);
				nLast = head.left;
			}
			if (head.right != null) {
				queue.offer(head.right);
				nLast = head.right;
			}
			if (head == last && !queue.isEmpty()) {
				System.out.print("\nLevel " + (level++) + " : ");
				last = nLast;
			}
		}
		System.out.println();
	}

    /**
     * ZigZag输出
     * 双端队列
     * 假设当前层是需要从左到右打印，那么就从队列头部取节点，孩子节点在尾部入队，先左孩子再右孩子
     * 假设当前层是需要从右往左打印，那么就从队尾取节点，孩子节点在头部入队，先右孩子再左孩子，相当于头插
     *
     *
     * @param head
     */
	public static void printByZigZag(Node head) {
		if (head == null) {
			return;
		}

		Deque<Node> dq = new LinkedList<Node>();

		int level = 1;
		boolean lr = true;//标志位交替，从左到右

        //表示当前这层最后一个打印的节点
        //下一层最后打印的节点怎么找？
        //其实就是下一层最先进入队列的那个孩子节点。
		Node last = head;
		Node nextLevelLast = null;//下一层最后打印的节点

		dq.offerLast(head);
		//调试函数
		pringLevelAndOrientation(level++, lr);

		while (!dq.isEmpty()) {
            Node node = null;
            //当前层是需要从左到右打印，那么就从队列头部取节点，
            // 孩子节点在尾部入队，先左孩子再右孩子
			if (lr) {//从左到右
				node = dq.pollFirst();
				if (node.left != null) {
				    //只会记录第一个进入队列的结点
					nextLevelLast = nextLevelLast == null ?
                            node.left : nextLevelLast;

					dq.offerLast(node.left);
				}
				if (node.right != null) {
                    //只会记录第一个进入队列的结点
					nextLevelLast = nextLevelLast == null ?
                            node.right : nextLevelLast;

					dq.offerLast(node.right);
				}
			} else {
			    //从右到左
                //当前层是需要从右往左打印，那么就从队尾取节点，孩子节点在头部入队，
                // 先右孩子再左孩子，相当于头插
				node = dq.pollLast();
				if (node.right != null) {
                    //只会记录第一个进入队列的结点
					nextLevelLast = nextLevelLast == null ?
                            node.right : nextLevelLast;

					dq.offerFirst(node.right);
				}
				if (node.left != null) {
                    //只会记录第一个进入队列的结点
					nextLevelLast = nextLevelLast == null ?
                            node.left : nextLevelLast;

					dq.offerFirst(node.left);
				}
			}
			//打印
			System.out.print(node.value + " ");
			//接下来，一旦到达当前这一层最后输出的节点，就要反向了
			if (node == last && !dq.isEmpty()) {
				lr = !lr;//反向
				last = nextLevelLast;
				nextLevelLast = null;

				System.out.println();
				pringLevelAndOrientation(level++, lr);
			}
		}
		System.out.println();
	}

	public static void pringLevelAndOrientation(int level, boolean lr) {
		System.out.print("Level " + level + " from ");
		System.out.print(lr ? "left to right: " : "right to left: ");
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
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.right.left.left = new Node(7);
		head.right.left.right = new Node(8);

		printTree(head);

		System.out.println("===============");
		printByLevel(head);

		System.out.println("===============");
		printByZigZag(head);

	}

}
