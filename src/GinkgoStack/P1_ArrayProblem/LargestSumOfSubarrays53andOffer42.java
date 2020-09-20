package GinkgoStack.P1_ArrayProblem;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 同一题：53. 最大子序和；牛客：连续最大和
 *
 *
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class LargestSumOfSubarrays53andOffer42 {

    /**
     * 普通的DP
     */
    class Solution {
        public int maxSubArray(int[] num) {
            int max = num[0];
            int sum = num[0];
            for(int i=1;i<num.length;i++){
                if(sum>=0){
                    sum += num[i];
                }else{
                    sum=num[i];
                }

                if(sum>max)
                    max=sum;
            }

            return max;
        }
    }
}
