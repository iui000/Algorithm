package GinkgoStack.P11_Tree.Traversal;


import java.util.LinkedList;
import java.util.List;

public class preorderTraversal {

    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<Integer> preOrder(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            res.addLast(node.val);//结果列表尾插
            if(node.right != null){//一定要先对右孩子入栈
                stack.addLast(node.right);
            }

            if(node.left != null){
                stack.addLast(node.left);
            }
        }

        return res;
    }


    /**
     * 莫里斯算法
     * @param root
     * @return
     */
    public List<Integer> preorderByMorris(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            }
            else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                }
                else{
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }

}
