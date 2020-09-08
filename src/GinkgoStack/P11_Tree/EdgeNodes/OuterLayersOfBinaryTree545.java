package GinkgoStack.P11_Tree.EdgeNodes;

import java.util.*;

/**
 * 545. 二叉树的边界
 * 给定一棵二叉树，以逆时针顺序从根开始返回其边界。边界按顺序包括左边界、叶子结点和右边界而不包括重复的结点。
 * (结点的值可能重复)
 *
 * 左边界的定义是从根到最左侧结点的路径。右边界的定义是从根到最右侧结点的路径。
 * 若根没有左子树或右子树，则根自身就是左边界或右边界。注意该定义只对输入的二叉树有效，而对子树无效。
 *
 * 最左侧结点的定义是：在左子树存在时总是优先访问，如果不存在左子树则访问右子树。
 * 重复以上操作，首先抵达的结点就是最左侧结点。
 *
 * 最右侧结点的定义方式相同，只是将左替换成右。
 *
 * 示例 1
 *
 * 输入:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * 输出:
 * [1, 3, 4, 2]
 *
 * 解析:
 * 根不存在左子树，故根自身即为左边界。
 * 叶子结点是3和4。
 * 右边界是1，2，4。注意逆时针顺序输出需要你输出时调整右边界顺序。
 * 以逆时针顺序无重复地排列边界，得到答案[1,3,4,2]。
 *
 *
 * 示例 2
 *
 * 输入:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * 输出:
 * [1,2,4,7,8,9,10,6,3]
 *
 * 解析:
 * 左边界是结点1,2,4。(根据定义，4是最左侧结点)
 * 叶子结点是结点4,7,8,9,10。
 * 右边界是结点1,3,6,10。(10是最右侧结点)
 * 以逆时针顺序无重复地排列边界，得到答案 [1,2,4,7,8,9,10,6,3]。
 */
public class OuterLayersOfBinaryTree545 {

    public static class  TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 上一层弹出最右边节点时，
     * 此时队头的元素就是下一层的最左节点，队尾就是下一层的最右节点
     * @param root
     * @return
     */
    public static List<Integer> outerLayers(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();

        LinkedList<Integer> rightEdge = new LinkedList<>();//用来存储右边界节点


        LinkedList<Integer> ans = new LinkedList<>();//左边界和叶子

        TreeNode last = root;//每层最后一个节点
        TreeNode first = root;//每层第一个节点

        int curLevel  = 0;

        if(root == null){
            return ans;
        }else {
            queue.addLast(root);
        }

//        ans.add(first.val);//先把根节点加入结果

        while (!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            if(node.left != null){
                queue.addLast(node.left);
            }
            if(node.right != null){
                queue.addLast(node.right);
            }

            if(node == first){
                ans.add(node.val);
            }

            //不是边界节点，但它是叶子
            if(node != first && node != last &&
               node.left == null && node.right == null){
                ans.add(node.val);
            }

            if(node == last){
                //将有边界元素暂时压栈
                //一般根节点都被认为是左边界，所以根节点不压栈
                if(node != root) rightEdge.addFirst(node.val);//相当于一个栈，头插
                last = queue.peekLast();//队尾就是下一层的最右节点

                //此时队头的元素就是下一层的最左节点
                first = queue.peekFirst();
            }

        }

        //最后将右边界拼接到结果集
        return ans;
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.right = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(8);
        head.right.left.left = new TreeNode(9);
        head.right.left.right = new TreeNode(10);
        head.left.right.right.right = new TreeNode(11);
        head.right.left.left.left = new TreeNode(12);
        head.left.right.right.right.left = new TreeNode(13);
        head.left.right.right.right.right = new TreeNode(14);
        head.right.left.left.left.left = new TreeNode(15);
        head.right.left.left.left.right = new TreeNode(16);


        List<Integer> ans = new LinkedList<>();
        outerLayers(head);
//        printEdge2(head);

    }
}
