package GinkgoStack.P11_Tree.SubTree;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 */
public class SubTopologyOffer26 {



       static class TreeNode {
           int val;
           TreeNode left;
           TreeNode right;
           TreeNode(int x) { val = x; }
        }

        /**
         *
         * AC
         * (约定空树不是任意一个树的子结构)
         * @param t1
         * @param t2
         * @return
         */
        public static boolean isSubStructure(TreeNode t1, TreeNode t2) {
            if (t2 == null) {//约定空树不是任意一个树的子结构
                return false;
            }
            if (t1 == null) {
                return false;
            }

            return check(t1, t2) ||
                    isSubStructure(t1.left, t2) ||
                    isSubStructure(t1.right, t2);
        }

        public static boolean check(TreeNode h, TreeNode t2) {
            if (h == null) {
                if(t2 == null){//如果这两个节点都是空，返回true
                    return true;
                }else {//如果t1此节点为空，而t2此节点不空
                    return false;
                }
            }
            else if (t2 == null){
                return true;
            }


            if (h.val != t2.val) {
                return false;
            }
            return check(h.left, t2.left) && check(h.right, t2.right);
        }

        public static void main(String[] args) {
            TreeNode t1 = new TreeNode(1);
            t1.left = new TreeNode(2);
            t1.right = new TreeNode(3);
            t1.left.left = new TreeNode(4);
            t1.left.right = new TreeNode(5);
            t1.right.left = new TreeNode(6);
            t1.right.right = new TreeNode(7);
            t1.left.left.left = new TreeNode(8);
            t1.left.left.right = new TreeNode(9);
            t1.left.right.left = new TreeNode(10);

            TreeNode t2 = new TreeNode(2);
            t2.left = new TreeNode(4);
            t2.left.left = new TreeNode(8);
            t2.right = new TreeNode(5);

            System.out.println(isSubStructure(t1, t2));

        }

}
