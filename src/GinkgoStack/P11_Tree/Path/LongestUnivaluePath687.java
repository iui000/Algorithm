package GinkgoStack.P11_Tree.Path;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
 * 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class LongestUnivaluePath687 {

//      Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x36af1/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    // 递推方程: helper（root）表示以root为顶点的最大同值边长。

    // 基础值：root == null, 返回 0;

    // 每次function call记录全局最大路径

    // 时间: O(n), n = number of tree nodes; 空间: O(1)
    class Solution {
        private int ans = 0;
        public int longestUnivaluePath(TreeNode root) {
            if (root == null) {
                return 0;
            }
            helper(root);
            return this.ans;
        }

        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int l = helper(root.left);
            int r = helper(root.right);

            int pl = 0;
            int pr = 0;
            //路径长度是计算边的数量，因此根节点与左子树的边加入结果pl
            if (root.left != null && root.val == root.left.val) pl = l + 1;
            //根节点与右子树的边加入结果pr
            if (root.right != null && root.val == root.right.val) pr = r + 1;
            //注意，更新ans
            this.ans = Math.max(this.ans, pl + pr);
            //返回的是左右的较大值
            return Math.max(pl, pr);
        }
    }


}
