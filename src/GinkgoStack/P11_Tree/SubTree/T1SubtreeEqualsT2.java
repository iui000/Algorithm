package GinkgoStack.P11_Tree.SubTree;



public class T1SubtreeEqualsT2 {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int data) {
			this.val = data;
		}
	}

//	public static boolean isSubtree(TreeNode t1, TreeNode t2) {
//		String t1Str = serialByPre(t1);
//		System.out.println(t1Str);
//
//		String t2Str = serialByPre(t2);
//        System.out.println(t2Str);
//
//		return getIndexOf(t1Str, t2Str) != -1;
//	}

//    void getDfsOrder(TreeNode *o, vector <int> &tar) {
//        if (!o) return;
//        tar.push_back(o->val);
//        if (o->left) getDfsOrder(o->left, tar);
//        else tar.push_back(lNull);
//        if (o->right) getDfsOrder(o->right, tar);
//        else tar.push_back(rNull);
//    }

    public static boolean isSubtree(TreeNode t1, TreeNode t2) {

        StringBuilder t1Str = new StringBuilder();
                serialByPre(t1,t1Str);
//        System.out.println(t1Str);

        StringBuilder t2Str = new StringBuilder();
                serialByPre(t2,t2Str);
//        System.out.println(t2Str);

        return getIndexOf(t1Str.toString(), t2Str.toString()) != -1;
    }

	public static void serialByPre(TreeNode head,StringBuilder stringBuilder) {
		if (head == null) {
			return ;
		}
        stringBuilder.append(head.val);
		if(head.left != null){
            serialByPre(head.left,stringBuilder);
        }else {
            stringBuilder.append("@");
        }

        if(head.right != null){
            serialByPre(head.right,stringBuilder);
        }else {
            stringBuilder.append("#");
        }
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
        //Leetcode上面的这个测试用例有毒，应该是true
        TreeNode t1 = new TreeNode(1);
        t1.right = new TreeNode(2);

        TreeNode t2 = new TreeNode(2);

		System.out.println(isSubtree(t1, t2));

	}

}
