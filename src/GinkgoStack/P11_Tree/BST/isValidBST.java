package GinkgoStack.P11_Tree.BST;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class isValidBST {

     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }


    /**
     * 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，
     * 这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
     * 如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，下面的代码我们使用栈来模拟中序遍历的过程。
     * AC
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();

        if(root == null){
            return true;
        }

        Long inOrder = -Long.MAX_VALUE;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            //先一直往左边走
            while (node != null){
                ((LinkedList<TreeNode>) stack).addLast(node);
                node = node.left;
            }

            //然后弹出来
            node =  stack.pollLast();
            if(node.val <= inOrder){
                return false;
            }
            inOrder = (long) node.val;

            //再往右边走一步
            node = node.right;
        }

        return true;
    }
}
