package GinkgoStack.P18_BackTrackingAndRecursion.Permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermutationsII47 {

    /**
     * 官方题解：
     * 此题是「46. 全排列」的进阶，序列中包含了重复的数字，
     * 要求我们返回不重复的全排列，那么我们依然可以选择使用搜索回溯的方法来做。
     *
     *     作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/permutations-ii/solution/quan-pai-lie-ii-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        boolean[] vis;//访问标记数组

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            //可行解「路径」
            List<Integer> perm = new ArrayList<Integer>();
            vis = new boolean[nums.length];
            //这里非常重要
            //我们选择对原数组排序，保证相同的数字都相邻，
            // 然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」，即如下的判断条件：
            // if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && vis[i - 1])) {
            Arrays.sort(nums);

            backtrack(nums, ans, 0, perm);
            return ans;
        }

        /**
         * 整个递归函数分为两个情况：
         *
         * @param nums
         * @param ans
         * @param idx 下一个待填入的位置是第 idx 个位置（下标从 0 开始）
         * @param perm 当前排列为perm
         */
        public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
            //第一种情况：
            //说明我们已经填完了 n 个位置，找到了一个可行的解，
            // 我们将perm 放入答案数组中，递归结束。
            if (idx == nums.length) {
                ans.add(new ArrayList<Integer>(perm));
                return;
            }
            //第二种情况：
            //如果idx<n，我们要考虑第 idx 个位置填哪个数。
            for (int i = 0; i < nums.length; ++i) {
                // 排除不合法的选择
                if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && vis[i - 1])) {
                    continue;
                }
                //做出选择
                perm.add(nums[i]);
                vis[i] = true;

                // 进入下一层决策树
                backtrack(nums, ans, idx + 1, perm);

                //撤销选择
                vis[i] = false;
                perm.remove(idx);
            }
        }
    }


}
