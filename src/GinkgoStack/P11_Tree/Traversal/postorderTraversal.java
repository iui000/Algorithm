package GinkgoStack.P11_Tree.Traversal;

import java.util.LinkedList;
import java.util.List;

public class postorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<Integer> postorder(TreeNode root){
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if(root == null){
            return res;
        }

        stack.addLast(root);
        while (!stack.isEmpty()) {

            TreeNode node = stack.pollLast();
            if (node.left != null) {
                stack.addLast(node.left);
            }

            if (node.right != null) {
                stack.addLast(node.right);
            }
            res.addFirst(node.val);//头插
        }
        return res;
    }
}
