package GinkgoStack.P11_Tree.SubTree;

/**
 * 判断t1树中是否含有与t2树拓扑结构完全相同的子树
 * 左神的代码有点小bug:已经修正
 *
 *          * 提交记录
 *          * 182 / 183 个通过测试用例
 *          * 状态：解答错误
 *          * 提交时间：2 分钟之前
 *          * 输入：
 *          * [12]
 *          * [2]
 *          * 输出：
 *          * true
 *          * 预期：
 *          * false
 *
 *        TreeNode t1=new TreeNode(12);
         *TreeNode t2=new TreeNode(2);
         *System.out.println(isSubtree(t1,t2));
 * 原因是第二个树根节点的值是第一个树根节点值的低位时，会出现bug,因为他们序列化出来的字符串是能够匹配的；
 * 但是他们节点的值其实不一样。因此解决办法就是每个节点的值前面也得加一个特殊字符
 */
public class T1SubtreeEqualsT2 {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int data) {
			this.val = data;
		}
	}

    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    /**
     * 对于这道题
     * @param head
     * @return
     */
    public static String serialByPre(TreeNode head) {

        /**
         * 原代码有bug,
         *  * 原因是第二个树根节点的值是第一个树根节点值的低位时，会出现bug,因为他们序列化出来的字符串是能够匹配的；
         *  * 但是他们节点的值其实不一样。因此解决办法就是每个节点的值前面也得加一个特殊字符,比如*
         */
        if(head == null){
            return "*#!";
        }

	    String res = "*" + head.val + "!";
	    res += serialByPre(head.left);
        res += serialByPre(head.right);

        return res;
    }


	// KMP
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int[] nextArr = getNextArray(ms);
		int index = 0;
		int mi = 0;
		while (index < ss.length && mi < ms.length) {
			if (ss[index] == ms[mi]) {
				index++;
				mi++;
			} else if (nextArr[mi] == -1) {
				index++;
			} else {
				mi = nextArr[mi];
			}
		}
		return mi == ms.length ? index - mi : -1;
	}

    /**
     * 计算nextArray,有动态规划的思想，next[i]表示0 ~ i-1的前缀与后组最长匹配长度
     * @param ms
     * @return
     */
	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
        //它和模式的长度是一样的
		int[] nextArr = new int[ms.length];
		nextArr[0] = -1;//ms[0]之前没有字符，就是-1
		nextArr[1] = 0;//前缀不能包含ms[i-1],后缀不能包含ms[0],因此为0
		int pos = 2;//表示当前位置,从第二个位置开始计算
		int cn = 0;//前缀的最后一个字符的位置下标
        while (pos < nextArr.length) {
			if (ms[pos - 1] == ms[cn]) {
				nextArr[pos++] = ++cn;
			} else if (cn > 0) {
				cn = nextArr[cn];//因为数值上，nextArr[cn]与新的目标cn相等，所以这样赋值
			} else {
				nextArr[pos++] = 0;
			}
		}
		return nextArr;
	}

	public static void main(String[] args) {
//		TreeNode t1 = new TreeNode(1);
//		t1.left = new TreeNode(2);
//		t1.right = new TreeNode(3);
//		t1.left.left = new TreeNode(4);
//		t1.left.right = new TreeNode(5);
//		t1.right.left = new TreeNode(6);
//		t1.right.right = new TreeNode(7);
//		t1.left.left.right = new TreeNode(8);
//		t1.left.right.left = new TreeNode(9);
//
//		TreeNode t2 = new TreeNode(2);
//		t2.left = new TreeNode(4);
//		t2.left.right = new TreeNode(8);
//		t2.right = new TreeNode(5);
//		t2.right.left = new TreeNode(9);

        /**
         * 提交记录
         * 182 / 183 个通过测试用例
         * 状态：解答错误
         * 提交时间：2 分钟之前
         * 输入：
         * [12]
         * [2]
         * 输出：
         * true
         * 预期：
         * false
         */
        TreeNode t1 = new TreeNode(12);
        TreeNode t2 = new TreeNode(2);
		System.out.println(isSubtree(t1, t2));

	}

}
