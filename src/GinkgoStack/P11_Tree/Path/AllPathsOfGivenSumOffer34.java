package GinkgoStack.P11_Tree.Path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 10000
 */
public class AllPathsOfGivenSumOffer34 {


    /**
     * 解法2
     * leetcode非官方题解，自己已修改
     * 解题思路：
     * 本问题是典型的二叉树方案搜索问题，使用回溯法解决，
     * 其包含 先序遍历 + 路径记录 两部分。
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }


        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            preOrder(root, sum);
            return res;
        }

        /**
         * 先序回溯
         * @param node
         * @param tarSum
         */
        void preOrder(TreeNode node, int tarSum) {
            if(node == null)
                return;
            path.add(node.val);

            tarSum -= node.val;

            if(tarSum == 0 &&
                node.left == null && node.right == null)//和为目标，并且是直到叶子节点
                res.add(new LinkedList(path));

            preOrder(node.left, tarSum);
            preOrder(node.right, tarSum);

            path.removeLast();
        }
    }



}
