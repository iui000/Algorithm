package GinkgoStack.P11_Tree.SubTree;

/**
 * 判断t1 树是否包含t2 树全部的拓扑结构
 * 这个题要求，空树是任意树的子结构
 */
public class Problem_11_T1ContainsT2Topology {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

    /**
     * 思路：
     * 如果t1中某棵树子树头节点的值与t2头节点的值一样，
     * 则从这两个头节点开始匹配，匹配的每一步都让t1上的节点跟着t2的先序遍历移动，
     * 每一移动一步，都检查t1的当前节点是否与t2当前节点的值一样。
     *
     * 如果匹配的过程中发现有不匹配的情况，就直接返回false,
     * 说明t1的当前子树从头节点开始，无法与t2匹配，那么再去寻找t1的下一颗子树。
     * t1的每颗子树都有可能匹配出t2,所以，都要检查一遍。
     *
     * 时间复杂度: O(N*M)
     *
     * @param t1
     * @param t2
     * @return
     */
	public static boolean contains(Node t1, Node t2) {
		if (t2 == null) {
			return true;
		}
		if (t1 == null) {
			return false;
		}

		return check(t1, t2) ||
                contains(t1.left, t2) ||
                contains(t1.right, t2);
	}

	public static boolean check(Node h, Node t2) {
		if (t2 == null) {
			return true;
		}
		if (h == null || h.value != t2.value) {
			return false;
		}
		return check(h.left, t2.left) && check(h.right, t2.right);
	}

	public static void main(String[] args) {
		Node t1 = new Node(1);
		t1.left = new Node(2);
		t1.right = new Node(3);
		t1.left.left = new Node(4);
		t1.left.right = new Node(5);
		t1.right.left = new Node(6);
		t1.right.right = new Node(7);
		t1.left.left.left = new Node(8);
		t1.left.left.right = new Node(9);
		t1.left.right.left = new Node(10);

		Node t2 = new Node(2);
		t2.left = new Node(4);
		t2.left.left = new Node(8);
		t2.right = new Node(5);

		System.out.println(contains(t1, t2));

	}

}
