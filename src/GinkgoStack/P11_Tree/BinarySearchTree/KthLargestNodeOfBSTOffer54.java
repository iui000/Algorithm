package GinkgoStack.P11_Tree.BinarySearchTree;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthLargestNodeOfBSTOffer54 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 二叉搜索树的中序遍历为 递增序列 。
     * 中序遍历的倒序 为 “右、根、左” 顺序，
     * 为求第 k个节点，需要实现以下 三项工作 ：
     * 递归遍历时计数，统计当前节点的序号；
     * 递归到第 k个节点时，应记录结果 res ；
     * 记录结果后，后续的遍历即失去意义，应提前终止（即返回）。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/mian-shi-ti-54-er-cha-sou-suo-shu-de-di-k-da-jie-d/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        int count=0, res=0;
        public int kthLargest(TreeNode root, int k) {
            this.count=k;
            dfs(root);
            return res;
        }
        public void dfs(TreeNode root){
            //当root为空或者已经找到了res时，直接返回
            if(root==null||count==0)
                return;
            dfs(root.right);
            if(--count==0){//先--，再判断
                res = root.val;
                return;//这里的return可以避免之后的无效迭代dfs(root.left);
            }
            dfs(root.left);
        }
    }
}
