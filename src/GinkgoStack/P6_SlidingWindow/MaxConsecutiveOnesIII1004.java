package GinkgoStack.P6_SlidingWindow;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class MaxConsecutiveOnesIII1004 {

    /**
     * 滑动窗口题目
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xqpir6/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {

        public int longestOnes(int[] A, int K) {
            int left = 0,right;
            int ans = 0;
            for (right = 0; right < A.length; right++) {
                //移动右指针
                if(A[right] == 0){
                    K--;
                }
                if(K < 0){
                    //左边收缩
                    if(A[left] == 0){//那k就得加回来
                        K++;
                    }
                    left++;
                }

                ans = Math.max(ans,right - left +1);
            }

            return ans;
        }
    }




}
