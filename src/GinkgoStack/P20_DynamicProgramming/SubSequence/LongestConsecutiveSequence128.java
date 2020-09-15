package GinkgoStack.P20_DynamicProgramming.SubSequence;


import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * (注意，不是子序列，所以这并不是一道DP题,而是一道Hash题，当然也可以用dp解决)
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutiveSequence128 {

    /**
     *     作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        /**
         * 增加了判断跳过的逻辑之后，时间复杂度是多少呢？外层循环需要 O(n)的时间复杂度，只有当一个数是连续序列的第一个数的情况下才会进入内层循环，然后在内层循环中匹配连续序列中的数，因此数组中的每个数只会进入内层循环一次。根据上述分析可知，总时间复杂度为 O(n)O(n)，符合题目要求。
         *
         * 作者：LeetCode-Solution
         * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * @param nums
         * @return
         */
        public int longestConsecutive(int[] nums) {
            Set<Integer> num_set = new HashSet<Integer>();
            for (int num : nums) {
                num_set.add(num);
            }

            int longestStreak = 0;

            for (int num : num_set) {
                if (!num_set.contains(num - 1)) {// 增加了判断跳过的逻辑之后，O(n)
                    int currentNum = num;
                    int currentStreak = 1;

                    while (num_set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }
    }



}
