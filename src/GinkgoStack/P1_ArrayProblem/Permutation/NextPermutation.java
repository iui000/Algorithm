package GinkgoStack.P1_ArrayProblem.Permutation;

import java.util.Arrays;

/**
 * AC代码
 * 31 下一个排列   (字典序排列)
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    private static void reverse(int[] nums,int begin,int end){
        for(int i = begin,j= end;i<=j;++i,--j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n-1;
        for(;i>=1;--i){
            if(nums[i] > nums[i-1]){
                if(i == n-1){
                    //先交换
                    int tmp = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = tmp;
                    break;
                }
                else {//说明存在一个更大的字典序列
                    //在i~n-1之间，从后往前找到第一个大于nums[i-1]的数
                    int j = n-1;
                    while (nums[j] <= nums[i-1] && j>i-1)
                        --j;

                    //先交换
                    int tmp = nums[j];
                    nums[j] = nums[i-1];
                    nums[i-1] = tmp;

                    //再将i~n-1这一段翻转
                    reverse(nums,i,n-1);
                    break;
                }
            }else if(i == 1){//说明走到头了，序列是一个逆序，不存在更大的字典序列，将其翻转即可
                reverse(nums,0,n-1);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2};
        nextPermutation(nums);
        System.out.print(Arrays.toString(nums));
    }
}


