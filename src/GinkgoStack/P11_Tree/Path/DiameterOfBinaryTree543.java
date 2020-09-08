package GinkgoStack.P11_Tree.Path;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 *
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree543 {

    int res;
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        res = 1;
        DFS(root);
        return res-1;
    }

    private int DFS(TreeNode node){
        if(node == null){// 访问到空节点了，返回0
            return 0;
        }

        //左/右儿子为根的子树的深度
        int leftHeigh =  DFS(node.left);
        int rightHeigh = DFS(node.right);

        // 计算d_node即L+R+1 并更新结果
        res = Math.max(res,leftHeigh + rightHeigh + 1);

        //返回该节点为根的子树的深度
        return Math.max(leftHeigh,rightHeigh)+1;
    }
}
