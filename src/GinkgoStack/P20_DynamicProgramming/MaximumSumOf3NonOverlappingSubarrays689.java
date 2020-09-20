package GinkgoStack.P20_DynamicProgramming;


/**
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
         * 思路：
         * 先求出
         * subArrSum[i] 表示 sum(nums[i],...,nums[i+k])
         *
         * 然后在subArrSum中找到left 和 right
         * int max_idx(int[] arr, int i, int j) -> 表示数组arr在[i,j]范围内的最大值的第一次出现的下标位置
         * left[i] = max_idx(subArrSum, 0, i)，也就是表示arr[0...i]之间最大值第一次出现的位置下标
         * right[i] = max_idx(subArrSum, i, subArrSum.length - 1)，表示arr[i...subArrSum.length - 1]之间最大值第一次出现的位置下标
         *
         * 最后固定一个下标，计算找到一个j,使得
         * (left[j-K], j, right[j+K]) 产生最大的 subArrSum[i] + subArrSum[j] + subArrSum[h] ，就是答案。
         *
         * @param nums
         * @param k
         * @return 返回字典序最小的下标组合
         */
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] subArrSum = new int[nums.length - k + 1];
            int[] left = new int[nums.length - k + 1];
            int[] right = new int[nums.length - k + 1];

            //计算初值subArrSum[0]
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            subArrSum[0] = sum;

            //计算left[]，
            for (int i = 1; i < subArrSum.length; i++) {
                //滑动窗口，计算subArrSum[]
                int tmp = subArrSum[i] = sum = sum + nums[i + k - 1] - nums[i - 1];
                //顺便计算left[]
                if (tmp > subArrSum[left[i - 1]]) {
                    left[i] = i;
                } else {
                    left[i] = left[i - 1];
                }
            }

            //计算right[]，从后往前计算
            right[right.length - 1] = right.length - 1;
            for (int i = subArrSum.length - 2; i >= 0; i--) {
                if (subArrSum[i] >= subArrSum[right[i + 1]]) {
                    right[i] = i;
                } else {
                    right[i] = right[i + 1];
                }
            }


            int[] res = new int[3];
            int max = Integer.MIN_VALUE;
            for (int j = k; j < subArrSum.length - k; j++) {
                /**
                 *  固定下标j，使得
                 *  (left[j-K], j, right[j+K]) 产生最大的 subArrSum[i] + subArrSum[j] + subArrSum[h] ，就是答案。
                 */
                int tmpSum = subArrSum[left[j - k]] + subArrSum[j] + subArrSum[right[j + k]];
                if (tmpSum > max) {//更新答案
                    max = tmpSum;
                    res[0] = left[j - k];
                    res[1] = j;
                    res[2] = right[j + k];
                }
            }
            return res;
        }
    }
}
