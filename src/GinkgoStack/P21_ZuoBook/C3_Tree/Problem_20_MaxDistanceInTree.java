package GinkgoStack.P21_ZuoBook.C3_Tree;


/**
 * 20.二叉树节点间的最大距离问题（或者说最长路径的长度）
 * 从二叉树的结点A出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点B的时候，路径上节点的数量叫做A到B的距离。（包括起点和终点）
 *
 * 树形DP套路
 */
public class Problem_20_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getMaxDistance(Node head) {
		return process(head).maxDistance;
	}

	public static class ReturnType {
		public int maxDistance;//树的最大距离
		public int height;//树的高度

		public ReturnType(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}
	}

	public static ReturnType process(Node head) {
		if (head == null) {
			return new ReturnType(0, 0);
		}
		ReturnType leftData = process(head.left);
		ReturnType rightData = process(head.right);

		//树的高度
		int height = Math.max(leftData.height, rightData.height) + 1;


		//最大距离根据三个方面来判断：左子树的最大距离、右子树的最大距离、和左右子树的高度之和+1
		int maxDistance = Math.max(leftData.height + rightData.height + 1,
				Math.max(leftData.maxDistance, rightData.maxDistance));

		return new ReturnType(maxDistance, height);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(getMaxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(getMaxDistance(head2));

	}

}
