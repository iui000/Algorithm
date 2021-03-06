package GinkgoStack.P21_ZuoBook.C3_Tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 1.原问题：
 * 给定一颗二叉树的头节点head，以及这颗树中的两个节点o1,o2，
 * 请返回o1和o2的最近公共祖先节点
 */
public class Problem_18_LowestCommonAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 1.原问题：
     * 给定一颗二叉树的头节点head，以及这颗树中的两个节点o1,o2，
     * 请返回o1和o2的最近公共祖先节点。
     * 后序遍历
     */
	public static Node lowestAncestor(Node cur, Node o1, Node o2) {
	    //如果发现cur等于null,或者等于o1/o2，那么就直接返回
		if (cur == null || cur == o1 || cur == o2) {
			return cur;
		}


		Node left = lowestAncestor(cur.left, o1, o2);
		Node right = lowestAncestor(cur.right, o1, o2);

		//说明左边发现过o1或者o2，右边发现过o1或者o2
        //他们首次在cur相遇
		if (left != null && right != null) {
			return cur;
		}

		//如果left和right有一个为空，另一个不为空
        //假设不为空的那个节点记作node,node是什么？
        //有两种可能，要么node是o1或o2中的一个，
        //要么node已经是o1和o2的公共祖先。
        //不管是哪种情况，都直接返回node即可
		return left != null ? left : right;
	}

    /**
     * 2.进阶问题：如果查询两个节点最近公共祖先的操作十分频繁，
     * 想办法让单条查询的查询时间减少
     * 第一种结构，子：父，哈希
     */
	public static class Record1 {
		private HashMap<Node, Node> map;

		public Record1(Node head) {
			map = new HashMap<Node, Node>();
			if (head != null) {
				map.put(head, null);
			}
			setMap(head);
		}

		private void setMap(Node head) {
			if (head == null) {
				return;
			}
			if (head.left != null) {
				map.put(head.left, head);
			}
			if (head.right != null) {
				map.put(head.right, head);
			}
			setMap(head.left);
			setMap(head.right);
		}

		public Node query(Node o1, Node o2) {
			HashSet<Node> path = new HashSet<Node>();
			while (map.containsKey(o1)) {
				path.add(o1);
				o1 = map.get(o1);
			}
			while (!path.contains(o2)) {
				o2 = map.get(o2);
			}
			return o2;
		}

	}

	//第二种记录方式：
    //直接建立任意两个节点之间的最近公共祖先记录
	public static class Record2 {
		private HashMap<Node, HashMap<Node, Node>> map;

		public Record2(Node head) {
			map = new HashMap<Node, HashMap<Node, Node>>();
			initMap(head);
			setMap(head);
		}

		private void initMap(Node head) {
			if (head == null) {
				return;
			}
			map.put(head, new HashMap<Node, Node>());
			initMap(head.left);
			initMap(head.right);
		}

		private void setMap(Node head) {
			if (head == null) {
				return;
			}
			headRecord(head.left, head);
			headRecord(head.right, head);
			subRecord(head);
			setMap(head.left);
			setMap(head.right);
		}

		private void headRecord(Node n, Node h) {
			if (n == null) {
				return;
			}
			map.get(n).put(h, h);
			headRecord(n.left, h);
			headRecord(n.right, h);
		}

		private void subRecord(Node head) {
			if (head == null) {
				return;
			}
			preLeft(head.left, head.right, head);
			subRecord(head.left);
			subRecord(head.right);
		}

		private void preLeft(Node l, Node r, Node h) {
			if (l == null) {
				return;
			}
			preRight(l, r, h);
			preLeft(l.left, r, h);
			preLeft(l.right, r, h);
		}

		private void preRight(Node l, Node r, Node h) {
			if (r == null) {
				return;
			}
			map.get(l).put(r, h);
			preRight(l, r.left, h);
			preRight(l, r.right, h);
		}

		public Node query(Node o1, Node o2) {
			if (o1 == o2) {
				return o1;
			}
			if (map.containsKey(o1)) {
				return map.get(o1).get(o2);
			}
			if (map.containsKey(o2)) {
				return map.get(o2).get(o1);
			}
			return null;
		}

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
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.left = new Node(8);
		printTree(head);
		System.out.println("===============");

		Node o1 = head.left.right;
		Node o2 = head.right.left;

		// ���β�ѯ--ԭ����
		System.out.println("o1 : " + o1.value);
		System.out.println("o2 : " + o2.value);
		System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
		System.out.println("===============");

		// ����map�󷽱��β�ѯ--��������
		Record1 record1 = new Record1(head);
		Record2 record2 = new Record2(head);

		System.out.println("o1 : " + o1.value);
		System.out.println("o2 : " + o2.value);
		System.out.println("ancestor : " + record1.query(o1, o2).value);
		System.out.println("ancestor : " + record2.query(o1, o2).value);

		o1 = head.left.left;
		o2 = head.left;
		System.out.println("o1 : " + o1.value);
		System.out.println("o2 : " + o2.value);
		System.out.println("ancestor : " + record1.query(o1, o2).value);
		System.out.println("ancestor : " + record2.query(o1, o2).value);

		o1 = head.right.left;
		o2 = head.right.right.left;
		System.out.println("o1 : " + o1.value);
		System.out.println("o2 : " + o2.value);
		System.out.println("ancestor : " + record1.query(o1, o2).value);
		System.out.println("ancestor : " + record2.query(o1, o2).value);

	}

}
