package GinkgoStack.P13_GraphTheory.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 问题的本质就是让你在一幅「图」中找到从起点 start 到终点 target 的最近距离，这个例子听起来很枯燥，
 * 但是 BFS 算法问题其实都是在干这个事儿，把枯燥的本质搞清楚了，再去欣赏各种问题的包装才能胸有成竹嘛。
 */
public class BFStemplate {

    // 计算从起点 start 到终点 target 的最近距离

    /**
     *     int BFS(Node start, Node target) {
     *         Queue<Node> q; // 核心数据结构
     *         Set<Node> visited; // 避免走回头路
     *
     *         q.offer(start); // 将起点加入队列
     *         visited.add(start);
     *         int step = 0; // 记录扩散的步数
     *
     *         while (q not empty) {
     *             int sz = q.size();
     *             //将当前队列中的所有节点向四周扩散
     *             for (int i = 0; i < sz; i++) {
     *                 Node cur = q.poll();
     *                 //划重点：这里判断是否到达终点
     *                 if (cur is target)
     *                 return step;
     *                 //将 cur 的相邻节点加入队列
     *                 for (Node x : cur.adj())
     *                     if (x not in visited) {
     *                     q.offer(x);
     *                     visited.add(x);
     *                 }
     *             }
     *             //划重点：更新步数在这里
     *             step++;
     *         }
     *     }
     *
     *
     */


    /**
     * 举例
     * 二叉树的最小深度
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }

}
