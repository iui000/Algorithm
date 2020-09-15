package GinkgoStack.P1_ArrayProblem;
import java.util.*;
/**
 * 牛客
 * 子数组的最大累加和
 * 题目描述
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * [要求]
 * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
 *
 * 示例1
 * 输入
 * 复制
 * [1, -2, 3, 5, -2, 6, -1]
 * 输出
 * 复制
 * 12
 * 备注:
 * 1 \leq N \leq 10^51≤N≤10
 * 5
 *
 * |arr_i| \leq 100∣arr
 * i
 * ​
 *  ∣≤100
 *
 *  https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=188&rp=1&ru=%2Fta%2Fjob-code-high-week&qru=%2Fta%2Fjob-code-high-week%2Fquestion-ranking
 */
public class MaximumSumOfSubarrays {

    /**
     * 先从左向右计算，获取最大值的下标，然后从右往左计算，算出左边的下标
     * 最后将2个下标之间的值计算出来
     * max sum of the subarray
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxsumofSubarray (int[] arr) {
        // write code here
        int left=getLeftIndex(arr);
        int right=getRightIndex(arr);
        int result=0;
        for(int i=left;i<=right;i++){
            result+=arr[i];
        }
        return result;
    }

    public int getRightIndex (int[] arr) {
        int right=0;
        int max=0;
        int temp=0;
        for(int i=0;i<arr.length;i++){
            temp+=arr[i];
            if(temp>max){
                right=i;
                max=temp;
            }
        }
        return right;
    }


    public int getLeftIndex(int[] arr) {
        int length=arr.length;
        int left=arr.length;
        int max=0;
        int temp=0;
        for(int i=length-1;i>=0;i--){
            temp+=arr[i];
            if(temp>max){
                left=i;
                max=temp;
            }
        }
        return left;
    }

}
