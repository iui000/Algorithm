package GinkgoStack.P20_DynamicProgramming;


/**
 * 689. 三个无重叠子数组的最大和
 * 689. 三个无重叠子数组的最大和
 * 给定数组 nums 由正整数组成，找到三个互不重叠的子数组的最大和。
 *
 * 每个子数组的长度为k，我们要使这3*k个项的和最大化。
 *
 * 返回每个区间起始索引的列表（索引从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * 示例:
 *
 * 输入: [1,2,1,2,6,7,5,1], 2
 * 输出: [0, 3, 5]
 * 解释: 子数组 [1, 2], [2, 6], [7, 5] 对应的起始索引为 [0, 3, 5]。
 * 我们也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 注意:
 *
 * nums.length的范围在[1, 20000]之间。
 * nums[i]的范围在[1, 65535]之间。
 * k的范围在[1, floor(nums.length / 3)]之间。
 */
public class MaximumSumOf3NonOverlappingSubarrays689 {

    class Solution {
        /**
         * cache[i] = sum(nums[i],...,nums[i+k])
         * int max_idx(int[] arr, int i, int j) -> 数组arr在[i,j]范围内的最大值的第一次出现的位置
         * left[i] = max_idx(cache, 0, i)
         * right[i] = max_idx(cache, i, cache.lenght - 1)
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] cache = new int[nums.length - k + 1];
            int[] left = new int[nums.length - k + 1];
            int[] right = new int[nums.length - k + 1];
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            cache[0] = sum;
            for (int i = 1; i < cache.length; i++) {
                int v = cache[i] = sum = sum + nums[i + k - 1] - nums[i - 1];
                if (v > cache[left[i - 1]]) {
                    left[i] = i;
                } else {
                    left[i] = left[i - 1];
                }
            }
            right[right.length - 1] = right.length - 1;
            for (int i = cache.length - 2; i >= 0; i--) {
                if (cache[i] >= cache[right[i + 1]]) {
                    right[i] = i;
                } else {
                    right[i] = right[i + 1];
                }
            }
            int interval = k << 1;
            int[] res = new int[]{0, k, interval};
            int max = Integer.MIN_VALUE;
            int max_m = cache.length - k;
            for (int m = k; m < max_m; m++) {
                int v = cache[left[m - k]] + cache[m] + cache[right[m + k]];
                if (v > max) {
                    max = v;
                    res[0] = left[m - k];
                    res[1] = m;
                    res[2] = right[m + k];
                }
            }
            return res;
        }
    }
}
