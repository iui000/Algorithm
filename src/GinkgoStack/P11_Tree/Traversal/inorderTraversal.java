package GinkgoStack.P11_Tree.Traversal;

import java.util.LinkedList;
import java.util.List;

public class inorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<Integer> inorder(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.addLast(cur);
                cur = cur.left;
            }

            cur = stack.pollLast();
            res.addLast(cur.val);
            cur = cur.right;
        }

        return res;
    }
}
