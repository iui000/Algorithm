package GinkgoStack.P11_Tree;

import java.util.HashMap;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * Definition for a binary CutDownTree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BuildTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 原理：先序遍历的第一个节点就是根，在中序遍历中通过该根节点(题干给了节点值不重复的条件)，分割出左子树和右子树；
     * 然后通过该左右子树，又可以再先序中找到各自的根节点，如此左右子树，递归
     */

    //记录每一个节点值对应的中序索引值
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;//保留的先序遍历

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        //初始化map
        for (int i = 0,len = preorder.length; i < len; i++) {
            map.put(inorder[i], i);
        }

        //递归
        return recursive(0,0,inorder.length-1);
    }

    /**
     * @param pre_root_idx  先序遍历的索引
     * @param in_left_idx  中序遍历的索引
     * @param in_right_idx 中序遍历的索引
     */
    public TreeNode recursive(int pre_root_idx, int in_left_idx, int in_right_idx) {
        //相等就是自己
        if (in_left_idx > in_right_idx) {
            return null;
        }
        //创建根节点，root_idx是在先序里面的
        TreeNode root = new TreeNode(preorder[pre_root_idx]);
        //得到该根节点的值后，到中序里去查找该值对应的索引值
        int in_root_idx = map.get(preorder[pre_root_idx]);

        //左子树的根节点就是 左子树的(前序遍历）第一个，就是+1,左边边界就是left，右边边界是中间区分的in_root_idx-1
        root.left = recursive(pre_root_idx + 1, in_left_idx, in_root_idx - 1);

        //由根节点在中序遍历的in_root_idx 区分成2段,in_root_idx 就是根

        //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
        //在中序里先计算出左子树的长度 = 左子树的右索引-左索引+1 (in_root_idx-1 - in_left_idx +1) 。
        //然后在先序里就能找到右子树的根节点了，即：pre_root_idx + 左子树长度 + 1。
        //中序里当然就很好确定右子树的索引范围了，即：in_root_idx + 1 与 in_right_idx的闭区间
        root.right = recursive(pre_root_idx + (in_root_idx-1 - in_left_idx +1)  + 1, in_root_idx + 1, in_right_idx);
        return root;
    }
}


