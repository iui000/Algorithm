package GinkgoStack.P6_SlidingWindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 *
 * 进阶：
 *
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class MinimumSizeSubarraySum209 {

    /**
     * 滑动窗口
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xi1h4t/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int result = Integer.MAX_VALUE;
            int start = -1, i = 0, localSum = 0;

            while (i < nums.length) {
                localSum += nums[i];
                while (localSum >= s && start < i){
                    result = Math.min(result, i - start);
                    localSum -= nums[++start];
                }
                i++;
            }

            return result == Integer.MAX_VALUE ? 0 : result;
        }
    }


}
