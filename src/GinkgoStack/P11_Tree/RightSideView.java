package GinkgoStack.P11_Tree;

import java.util.LinkedList;
import java.util.List;

public class RightSideView {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new LinkedList<>();

        TreeNode last = root;
        if(root == null){
            return ans;
        }else {
            queue.addLast(root);
        }

        while (!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            if(node.left != null){
                queue.addLast(node.left);
            }
            if(node.right != null){
                queue.addLast(node.right);
            }

            if(node == last){
                ans.add(node.val);
                last = queue.peekLast();
            }
        }
        return ans;
    }


}
