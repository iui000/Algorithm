package GinkgoStack.P11_Tree.FullBinaryTree;

import java.util.*;

/**
 * 894. 所有可能的满二叉树（国内外定义不同，有歧义）
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 *
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 *
 * 答案中每个树的每个结点都必须有 node.val=0。
 *
 * 你可以按任何顺序返回树的最终列表。
 *
 *
 * ---------------
 * 除最后一层无任何子节点外，每一层上的所有结点都有两个子结点的二叉树。
 * 国内教程定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树。
 * 国外(国际)定义:a binary tree T is full if each node is either a leaf or possesses exactly two childnodes.
 * 大意为：如果一棵二叉树的结点要么是叶子结点，要么它有两个子结点，这样的树就是满二叉树。
 * (一棵满二叉树的每一个结点要么是叶子结点，要么它有两个子结点，但是反过来不成立，因为完全二叉树也满足这个要求，但不是满二叉树)
 */
public class AllPossibleFullBinaryTrees894 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 官方题解
     */
    class Solution {
        Map<Integer, List<TreeNode>> memo = new HashMap<>();

        public List<TreeNode> allPossibleFBT(int N) {
            if (!memo.containsKey(N)) {
                List<TreeNode> ans = new LinkedList();
                if (N == 1) {
                    ans.add(new TreeNode(0));
                } else if (N % 2 == 1) {
                    for (int x = 0; x < N; ++x) {
                        int y = N - 1 - x;
                        for (TreeNode left: allPossibleFBT(x))
                            for (TreeNode right: allPossibleFBT(y)) {
                                TreeNode bns = new TreeNode(0);
                                bns.left = left;
                                bns.right = right;
                                ans.add(bns);
                            }
                    }
                }
                memo.put(N, ans);
            }

            return memo.get(N);
        }
    }

    /**
     * 解法2
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x3ismr/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution2 {
        public List<TreeNode> allPossibleFBT(int N) {
            List<TreeNode> result = new ArrayList<>();
            if (N%2 == 0) {//即便是满二叉树数的两种定义，节点个数都不可能为偶数
                return result;
            }

            if (N == 1) {//一个节点，边界条件
                TreeNode node = new TreeNode(0);
                result.add(node);
                return result;
            }

            //去掉root
            N -= 1;

            /**
             * 子问题：
             * 左子树和右子树均为满二叉树
             * 左子树的节点数的取值就为1，3，5，...
             */
            for (int i = 1; i < N; i += 2) {
                //左右子问题的解
                List<TreeNode> left = allPossibleFBT(i);
                List<TreeNode> right = allPossibleFBT(N-i);

                //left里面存储的就是左子树不同结构的根节点，right同理
                //因此，接下来要将左右两边不同结构的子树组合起来，加入到结果集合中
                for (TreeNode lnode: left) {
                    for (TreeNode rnode: right) {
                        TreeNode root = new TreeNode(0);
                        root.left = lnode;
                        root.right = rnode;

                        result.add(root);
                    }
                }
            }
            return result;
        }
    }


}
