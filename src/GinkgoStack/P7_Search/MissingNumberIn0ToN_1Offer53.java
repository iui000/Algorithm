package GinkgoStack.P7_Search;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 */
public class MissingNumberIn0ToN_1Offer53 {

    class Solution {
        public int missingNumber(int[] nums) {
            int i = 0, j = nums.length - 1;
            while(i <= j) {
                int m = (i + j) / 2;

                //若 nums[m] = m，则 “右子数组的首位元素” 一定在闭区间 [m + 1, j]中，因此执行 i = m + 1;
                if(nums[m] == m)
                    i = m + 1;
                //若 nums[m] != m ，则 “左子数组的末位元素” 一定在闭区间 [i, m - 1]中，因此执行 j = m - 1;
                else j = m - 1;
            }
            //跳出时，变量 i和 j分别指向 “右子数组的首位元素” 和 “左子数组的末位元素” 。因此返回 i即可。
            return i;
        }
    }
}
