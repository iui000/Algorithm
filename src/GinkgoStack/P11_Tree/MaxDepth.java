package GinkgoStack.P11_Tree;

import java.util.*;


/**
 * 二叉树的最大深度
 * 题目描述
 * 求给定二叉树的最大深度，
 * 最大深度是指树的根结点到最远叶子结点的最长路径上结点的数量。
 */
public class MaxDepth {

   public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;
   }

    /**
     * 层序遍历
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth (TreeNode root) {
        // write code here
        int dept=0;
        if(root!=null){
            Queue<TreeNode> queue=new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                dept++;
                int size=queue.size();
                for(int i=0;i<size;i++){
                    TreeNode temp=queue.poll();
                    if(temp.left!=null){
                        queue.offer(temp.left);
                    }
                    if(temp.right!=null){
                        queue.offer(temp.right);
                    }
                }
            }
        }
        return dept;
    }

}
