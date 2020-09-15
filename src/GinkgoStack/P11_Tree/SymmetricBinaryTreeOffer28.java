package GinkgoStack.P11_Tree;

/**
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 */
public class SymmetricBinaryTreeOffer28 {

    /**
     * 解题思路：
     * 对称二叉树定义： 对于树中 任意两个对称节点 LL 和 RR ，一定有：
     * L.val = R.valL.val=R.val ：即此两对称节点值相等。
     * L.left.val = R.right.val：即 LL 的 左子节点 和 RR 的 右子节点 对称；
     * L.right.val = R.left.val ：即 LL 的 右子节点 和 RR 的 左子节点 对称。
     * 根据以上规律，考虑从顶至底递归，判断每对节点是否对称，从而判断树是否为对称二叉树。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/solution/mian-shi-ti-28-dui-cheng-de-er-cha-shu-di-gui-qing/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }
        public boolean isSymmetric(TreeNode root) {
            return root == null ? true : recur(root.left, root.right);
        }
        boolean recur(TreeNode L, TreeNode R) {
            if(L == null && R == null) return true;
            if(L == null || R == null || L.val != R.val) return false;
            return recur(L.left, R.right) && recur(L.right, R.left);
        }
    }


}
