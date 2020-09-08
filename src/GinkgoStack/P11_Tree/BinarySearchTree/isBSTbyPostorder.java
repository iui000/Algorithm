package GinkgoStack.P11_Tree.BinarySearchTree;


import java.util.Stack;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 *
 * 提示：
 *
 * 数组长度 <= 1000
 */
public class isBSTbyPostorder {


    /**
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 方法一：递归分治,虽然时间复杂度最坏O(N^2)，但其实运行时间很低
     * 复杂度分析：
     *
     * 时间复杂度 O(N^2)
     * 每次调用 recur(i,j)recur(i,j) 减去一个根节点，因此递归占用 O(N)O(N) ；最差情况下（即当树退化为链表），每轮递归都需遍历树所有节点，占用 O(N)O(N) 。
     * 空间复杂度 O(N)O(N) ： 最差情况下（即当树退化为链表），递归深度将达到 NN 。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution1 {
        public boolean verifyPostorder(int[] postorder) {
            return recur(postorder, 0, postorder.length - 1);
        }


        boolean recur(int[] postorder, int beginIndex, int rootIndex) {
            if(beginIndex >= rootIndex)
                return true;
/**
 * 划分左右子树： 遍历后序遍历的 [i, j][i,j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 mm 。此时，可划分出左子树区间 [i,m-1][i,m−1] 、右子树区间 [m, j - 1][m,j−1] 、根节点索引 jj 。
 *
 * 作者：jyd
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
            int p = beginIndex;
            while(postorder[p] < postorder[rootIndex])
                p++;

            //前面是左子树
            //后面是右子树
            int m = p;
            while(postorder[p] > postorder[rootIndex])
                p++;

            //如果后半段出现了不大于根的数，也就是p != j的话，那就一定不是BST
            return p == rootIndex &&
                    recur(postorder, beginIndex, m - 1) &&
                    recur(postorder, m, rootIndex - 1);
        }
    }


    /**
     * 方法二：辅助单调栈
     */
    class Solution2 {
        public boolean verifyPostorder(int[] postorder) {
            Stack<Integer> stack = new Stack<>();
            int root = Integer.MAX_VALUE;
            for(int i = postorder.length - 1; i >= 0; i--) {
                if(postorder[i] > root)
                    return false;

                while(!stack.isEmpty() && stack.peek() > postorder[i])
                    root = stack.pop();

                stack.add(postorder[i]);
            }
            return true;
        }
    }





}
