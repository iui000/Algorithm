package GinkgoStack.P20_DynamicProgramming.SubSequence;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. 最长定差子序列
 * 这个算法可以当作模板，可以应对 最长连续上升子序列（步长为1） 和 最长连续下降子序列（步长为-1）
 *
 *
 * 给你一个整数数组 arr 和一个整数 difference，请你找出 arr 中
 * 所有相邻元素之间的差等于给定 difference 的等差子序列，并返回其中最长的等差子序列的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 */
public class LongestArithmeticSubsequenceOfGivenDifference1218 {


    /**
     *     作者：acw_weian
     *     链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/solution/ji-he-dp-by-acw_weian/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {

        /**
         * 集合DP
         * 哈希表
         * @param arr
         * @param difference
         * @return
         */
        public int longestSubsequence(int[] arr, int difference) {
            int len = arr.length;
            //dp[arr[i]] = dp[arr[i] - difference] + 1
            int ans =0;
            Map<Integer,  Integer> dp = new HashMap<>();
            for(int i =0; i < len; i++){
                int dpI = dp.getOrDefault(arr[i] - difference, 0) + 1;
                dp.put(arr[i], dpI);
                ans = Math.max(ans, dpI);
            }
            return ans;
        }
    }


}
