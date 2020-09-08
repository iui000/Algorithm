package GinkgoStack.P17_BitOperation;


/**
 * 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 *
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 */
public class MissingNumber268 {

    /**
     * 方法三：位运算
     * 分析
     *
     * 由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，
     * 因此我们可以通过异或运算找到缺失的数字。
     *
     * 算法
     *
     * 我们知道数组中有 nn 个数，并且缺失的数在 [0..n][0..n] 中。因此我们可以先得到 [0..n][0..n] 的异或值，
     * 再将结果对数组中的每一个数进行一次异或运算。未缺失的数在 [0..n][0..n] 和数组中各出现一次，因此异或后得到 0。而缺失的数字只在 [0..n][0..n] 中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。
     *
     * 在编写代码时，由于 [0..n][0..n] 恰好是这个数组的下标加上 nn，因此可以用一次循环完成所有的异或运算，
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/missing-number/solution/que-shi-shu-zi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution {
        public int missingNumber(int[] nums) {
            int missing = nums.length;
            for (int i = 0; i < nums.length; i++) {
                missing ^= i ^ nums[i];
            }
            return missing;
        }
    }


}
