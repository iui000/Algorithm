package GinkgoStack.P11_Tree.Path;

import java.util.HashMap;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度
 * 路径是指从某个节点往下，每次最多选择一个孩子节点 或者 不选择所形成的节点链。
 *
 * 思想：类似题目
 * Problem_11_LongestSumSubArrayLength
 *
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * 思路：前缀和 + hash
 */
public class Problem_06_LongestPathSum {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getMaxLength(Node head, int sum) {

        /**
         * sumMap 负责记录从head开始的一条路径上的累加和 出现的最早层数；
         * key 就是某一个前缀和（从head向下）,value就是该前缀和最早出现的层数
         */
		HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
		//表示累加和0不用包括任何节点就可以得到
		sumMap.put(0, 0); // important

        //接下来先序遍历
		return preOrder(head, sum, 0, 1, 0, sumMap);
	}

    /**
     *
     * @param cur
     * @param tarSum
     * @param preSum 从head到cur父节点的累加和记为preSum
     * @param level
     * @param maxLen
     * @param sumMap
     * @return
     */
	public static int preOrder(Node cur, int tarSum, int preSum, int level,
                               int maxLen, HashMap<Integer, Integer> sumMap) {
		if (cur == null) {
			return maxLen;
		}

        /**
         * 这里和数组前缀和的操作一样
         */
		int curSum = preSum + cur.value;
		if (!sumMap.containsKey(curSum)) {
			sumMap.put(curSum, level);
		}
		if (sumMap.containsKey(curSum - tarSum)) {
			maxLen = Math.max(level - sumMap.get(curSum - tarSum), maxLen);
		}

        /**
         * 递归左右子树
         */
		maxLen = preOrder(cur.left, tarSum, curSum, level + 1, maxLen, sumMap);
		maxLen = preOrder(cur.right, tarSum, curSum, level + 1, maxLen, sumMap);
		//在返回前还有一个重要的工作
        //curSum对应的level等于当前层数， 说明curSum是遍历到cur时加上去的，那就把这条记录删除。
		if (level == sumMap.get(curSum)) {
			sumMap.remove(curSum);
		}

		return maxLen;
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
		Node head = new Node(-3);
		head.left = new Node(3);
		head.right = new Node(-9);
		head.left.left = new Node(1);
		head.left.right = new Node(0);
		head.left.right.left = new Node(1);
		head.left.right.right = new Node(6);
		head.right.left = new Node(2);
		head.right.right = new Node(1);
		printTree(head);
		System.out.println(getMaxLength(head, 6));
		System.out.println(getMaxLength(head, -9));

	}

}
