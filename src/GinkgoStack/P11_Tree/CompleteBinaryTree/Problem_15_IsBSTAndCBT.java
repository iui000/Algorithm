package GinkgoStack.P11_Tree.CompleteBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个算法：
 *
 * 1.判断一棵树是否是二叉搜索树
 * 2.判断一棵树是否为完全二叉树
 */
public class Problem_15_IsBSTAndCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * Morris算法
     * 滴滴的面试题，当时写了这个算法，面试官好像没明白，不知道这是Morris算法，但是我又没解释明白
     * 所以，面试场景下直接写传统中序遍历的解法好点，免得他以为你思路混乱。
     * @param head
     * @return
     */
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

    /**
     * 判断一棵树是否是完全二叉树
     * 思路：
     * 1.按照层序遍历，从每一层的左边向右边依次遍历所有的节点；
     * 2.如果当前节点有右孩子，但却没有左孩子，那么就直接返回false;
     * 3.接着，如果当前节点并不是左右孩子都存在的话，也就它仅仅只有左孩子，那么同一层的之后节点应当都是叶子节点，否则也是false;
     * 4.最后，只要以上两个步骤都没有返回false，那么就返回true
     *
     * @param head
     * @return
     */
	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}

		Queue<Node> queue = new LinkedList<Node>();
		boolean leaf = false;//遍历的上一个节点是否为叶子节点
		Node l = null;
		Node r = null;
		queue.offer(head);//将根节点加入队列
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;

            //如果遍历的上一个节点是叶子节点，但是当前节点又不是叶子，那么就不是完全二叉树；
            //如果当前节点并不是左右孩子都存在的话，也就它仅仅只有左孩子，那么同一层的之后节点应当都是叶子节点，否则也是false;
			if ((leaf && (l != null || r != null))
                    || (l == null && r != null)) {//如果当前节点有右孩子，但却没有左孩子，那么就直接返回false;
				return false;
			}

			if (l != null) {
				queue.offer(l);
			}
			if (r != null) {
				queue.offer(r);
			} else {//否则记录标志位
				leaf = true;
			}
		}
		return true;
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

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));

	}
}