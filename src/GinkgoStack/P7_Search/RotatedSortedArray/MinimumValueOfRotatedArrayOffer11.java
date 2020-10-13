package GinkgoStack.P7_Search.RotatedSortedArray;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 相同题目：154. 寻找旋转排序数组中的最小值 II
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class MinimumValueOfRotatedArrayOffer11 {
    /**
     * 注意点，数组中可能存在值相同元素。
     * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
     * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
     *
     * 思考三个问题：
     * 时间复杂度：
     * 一般情况是O（log n）
     * 最坏情况是O（n）
     */
    class Solution {
        public int findMin(int[] numbers) {
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (numbers[mid] < numbers[high]) {//说明分界点再左半边，右边是升序
                    high = mid;
                } else if (numbers[mid] > numbers[high]) {//说明分界点再右半边，左半边是升序
                    low = mid + 1;
                } else {//非常妙，应对左右相等的情况，有边界收缩一个单位即可
                    high -= 1;
                }
            }

            return numbers[low];
        }
    }

}
