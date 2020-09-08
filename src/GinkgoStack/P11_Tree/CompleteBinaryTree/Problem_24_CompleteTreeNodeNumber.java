package GinkgoStack.P11_Tree.CompleteBinaryTree;

/**
 * 统计完全二叉树的节点数
 * 请实现一个时间复杂度低于O(N)的算法
 *
 * O((log n)^2)
 */
public class Problem_24_CompleteTreeNodeNumber {

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
     * 找到右子树的最左节点，根据它是否处于最后一层，就可判断左子树 或 右子树是否为满二叉树，然后递归另一颗子树即可
     * 具体来说，
     * 如果右子树的最左节点，它否处于最后一层，那么可以说明左子树是满二叉树，节点数为2^(h-curlevel)-1个节点；
     * 总的节点数为左 + 根+ 右： 2^(h-1)-1 +1 + bs(node.right, curlevel + 1, h)
     *
     * 如果右子树的最左节点，它不处于最后一层，那么可以说明右子树是满二叉树，节点数为2^(h-1)-1个节点；
     * 总的节点数为左 + 根+ 右： 2^(h-1)-1 +1 + bs(node.right, curlevel + 1, h)
     * @param head
     * @return
     */
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

    /**
     *
     * @param node 头节点
     * @param curlevel 头节点的层次，根节点是1
     * @param h  整个数的高度，一直不变
     * @return
     */
	public static int bs(Node node, int curlevel, int h) {
		if (curlevel == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, curlevel + 1) == h) {
			return (1 << (h - curlevel)) + bs(node.right, curlevel + 1, h);
		} else {
			return (1 << (h - curlevel - 1)) + bs(node.left, curlevel + 1, h);
		}
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
