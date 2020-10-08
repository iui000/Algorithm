package GinkgoStack.P6_SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum239andOffer59 {


    /**
     * 思路：单调非严格递减队列
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums.length == 0 || k == 0)
                return new int[0];

            Deque<Integer> deque = new LinkedList<>();
            int[] res = new int[nums.length - k + 1];

            //注意i的初值
            for(int j = 0, i = 1 - k; j < nums.length; i++, j++) {
                if(i > 0 && deque.peekFirst() == nums[i - 1])
                    deque.removeFirst(); // 删除 deque 中对应的 nums[i-1]

                while(!deque.isEmpty() && deque.peekLast() < nums[j])
                    deque.removeLast(); // 保持 deque 递减
                deque.addLast(nums[j]);

                if(i >= 0)//统计
                    res[i] = deque.peekFirst();  // 记录窗口最大值
            }
            return res;
        }
    }

}
