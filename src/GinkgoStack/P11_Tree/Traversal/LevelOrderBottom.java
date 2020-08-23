package GinkgoStack.P11_Tree.Traversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class LevelOrderBottom {
    public class TreeNode {
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> stack = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if(root == null){
            return stack;
        }

        deque.addLast(root);
        List<Integer> levelList = new ArrayList<>();
        TreeNode last = root;
        while (!deque.isEmpty()){
            TreeNode treeNode = deque.pollFirst();
            if(treeNode.left != null){
                deque.addLast(treeNode.left);
            }
            if(treeNode.right != null){
                deque.addLast(treeNode.right);
            }

            levelList.add(treeNode.val);

            if(treeNode == last){
                ((LinkedList<List<Integer>>) stack).addFirst(levelList);
                levelList = new ArrayList<>();
                last = deque.peekLast();
            }
        }
        return stack;
    }

}
