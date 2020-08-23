package GinkgoStack.P11_Tree.Traversal;

/**
 * 二叉树的层序遍历
 * AC
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;
public class LevelOrder {
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * AC代码
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue  = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        if(root == null){
            return res;
        }
        queue.addLast(root);

        TreeNode last = root;//每一层的最后一个节点要标记
        List<Integer> level = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.pollFirst();

            if(node.left != null){
                queue.addLast(node.left);
            }
            if(node.right != null){
                queue.addLast(node.right);
            }

            level.add(node.val);

            if(node == last){
                //把此层的列表加入到结果中
                res.add(level);

                if(!queue.isEmpty()){
                    //然后新建一个列表，存储下一层
                    level = new ArrayList<>();
                    //下一层的最后一个节点就是目前队尾的结点
                    last = queue.peekLast();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
