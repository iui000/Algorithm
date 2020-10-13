package GinkgoStack.P4_QueueProblem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 * 示例 1：
 *
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 *
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 *
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LongestContinuSubarrWithAbsoluteDiffLessThanorEqualtoLimit1438 {
    /**
     * 观察1 <= nums.length <= 10^5
     * 所以一看肯定是O(n)的复杂度
     * 滑动窗口 + 单调队列
     * 同时记录窗口内的最大值和最小值即可，因此需要两个双端队列。
     *
     * 这道题也可以用treeMap来解，但是时间复杂度是O（n log n）
     */
     public int longestSubarray(int[] A,int limit){
         //
         Deque<Integer> maxd = new ArrayDeque<>();//记录当前窗口的最大值,递减
         Deque<Integer> mind = new ArrayDeque<>();//递增
         int i = 0;
         int ans = 0;
         for (int j = 0; j < A.length; j++) {
             while (!maxd.isEmpty() && A[j] > maxd.peekLast()){
                 maxd.pollLast();
             }
             while (!mind.isEmpty() && A[j] < mind.peekLast()){
                 mind.pollLast();
             }

             maxd.offerLast(A[j]);
             mind.offerLast(A[j]);

             //左边收缩
             while (maxd.peekFirst() - mind.peekFirst() > limit){
                 if(maxd.peekFirst() == A[i]){//如果后指针指向最大值，那得出队
                     maxd.pollFirst();
                 }
                 if(mind.peekFirst() == A[i]){//同理
                     mind.pollFirst();
                 }
                 ++i;
             }

             ans = Math.max(ans,j - i +1);//记录长度
         }

         return ans;
     }

    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xqez21/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param limit
     * @return
     */
    //Time complexity: O(nlogn)
    public int longestSubarray2(int[] A, int limit) {
        int left = 0;
        int right;
        int ans = 0;
        //For operations like add, remove, containsKey, time complexity is
        // O(log n) where n is number of elements present in TreeMap.
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (right = 0; right < A.length; right++) {
            m.put(A[right], 1 + m.getOrDefault(A[right], 0));

            while(m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                m.put(A[left], m.get(A[left]) - 1);
                if (m.get(A[left]) == 0)
                    m.remove(A[left]);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


}
