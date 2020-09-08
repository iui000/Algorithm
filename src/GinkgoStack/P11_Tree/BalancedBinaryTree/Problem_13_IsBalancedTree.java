package GinkgoStack.P11_Tree.BalancedBinaryTree;

/**
 * 判断二叉树是否是平衡二叉树
 * 树形DP
 * 套路和第7个问题一样
 */
public class Problem_13_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanced(Node head) {
		return process(head).isBalanced;
	}

	public static class ReturnType {
		public boolean isBalanced;
		public int height;

		public ReturnType(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

	public static ReturnType process(Node head) {
	    //基本条件
		if (head == null) {
			return new ReturnType(true, 0);
		}

		//递归左右子树
		ReturnType leftData = process(head.left);
		ReturnType rightData = process(head.right);

		//计算这个树本身的信息
		int height = Math.max(leftData.height, rightData.height) + 1;
		//判断平衡性质
		boolean isBalanced = leftData.isBalanced && rightData.isBalanced
				&& Math.abs(leftData.height - rightData.height) < 2;

		return new ReturnType(isBalanced, height);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalanced(head));

	}

}
