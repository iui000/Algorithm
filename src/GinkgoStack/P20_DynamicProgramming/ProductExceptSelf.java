package GinkgoStack.P20_DynamicProgramming;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ProductExceptSelf {
    /**
     * 这道题不允许用除法，这是很正常的，因为：
     * 假设其中含有0，那么使用除法的话，即便是经过特殊处理而避免报除0异常，但仍然是不能得到正确答案的；
     * 因此这道题的正确解法是：前后缀 + 空间压缩
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        //相当于拿结果自身来作为dp数组
        int[] outPut = new int[length];

        // 第一轮，outPut[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 outPut[0] = 1
        outPut[0] = 1;
        for (int i = 1; i < length; i++) {
            outPut[i] = nums[i - 1] * outPut[i - 1];
        }

        // rightProduct 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 rightProduct = 1
        int rightProduct = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 outPut[i]，右边的乘积为 rightProduct
            //最终结果
            outPut[i] = outPut[i] * rightProduct;
            // rightProduct 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 rightProduct 上
            rightProduct *= nums[i];
        }
        return outPut;
    }


}
