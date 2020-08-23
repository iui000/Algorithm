package GinkgoStack.P11_Tree;

import java.util.LinkedList;

class MinDepth {
    // Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        int level = 1;
        TreeNode last = root;
        if(root == null){
            return 0;
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

            if(node.left == null && node.right == null){
                return level;
            }

            if(node == last){
                ++level;
                last = queue.peekLast();
            }
        }
        return level;
    }
}