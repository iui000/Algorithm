package GinkgoStack.P7_Sort.WiggleSort;

/**
 * 280. 摆动排序
 * 给你一个无序的数组 nums, 将该数字 原地 重排后
 * 使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。
 *
 * 示例:
 *
 * 输入: nums = [3,5,2,1,6,4]
 * 输出: 一个可能的解答是 [3,5,1,6,2,4]
 */
public class WiggleSort280 {

    class Solution {
        public void wiggleSort(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                // 需要交换的情况：
                // 下标为奇数时且nums[i] < nums[i - 1]
                // 或下标为偶数时且nums[i] > nums[i - 1]
                if ((i % 2 == 1 && nums[i] < nums[i-1]) ||
                        (i % 2 == 0 && nums[i] > nums[i-1])) {
                    int tmp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }
}
