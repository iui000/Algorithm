package GinkgoStack.P11_Tree;

import java.util.Stack;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 */
public class MirrorTree {
    /**
     * 方法二：辅助栈（或队列）
     * 利用栈（或队列）遍历树的所有节点 nodenode ，并交换每个 nodenode 的左 / 右子节点。
     * 算法流程：
     * 特例处理： 当 rootroot 为空时，直接返回 nullnull ；
     * 初始化： 栈（或队列），本文用栈，并加入根节点 rootroot 。
     * 循环交换： 当栈 stackstack 为空时跳出；
     * 出栈： 记为 nodenode ；
     * 添加子节点： 将 nodenode 左和右子节点入栈；
     * 交换： 交换 nodenode 的左 / 右子节点。
     * 返回值： 返回根节点 rootroot 。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/solution/mian-shi-ti-27-er-cha-shu-de-jing-xiang-di-gui-fu-/
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
        public TreeNode mirrorTree(TreeNode root) {
            if(root == null) return null;
            Stack<TreeNode> stack = new Stack<>();

            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if(node.left != null) stack.add(node.left);
                if(node.right != null) stack.add(node.right);
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }
            return root;
        }
    }

}
