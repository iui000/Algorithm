package GinkgoStack.P3_StackProblem.MonotonousStack;

import java.util.*;

/**
 * 字节笔试题10.11
 * leetcode 84题 柱状图中最大的矩形
 *
 * AC，只是换了个说法和表示方式而已：
 * 数组nums,求MAX(min(nums[i...i+k-1]) * k),k为任意小于n的数。
 * 这又是一个极小极大化题目，单调栈
 */
public class P2_MaxValue_MinValueOfKlengthSubarrMutiK {

    /**
     * 自己的解法
     * 和LeftRightNearestLargerValue基本一样
     * 单调栈 递增
     * 以当前这个数为最小值，向左右扩张
     * 找到一个数左边和右边离自己最近的小于自己的数的下标
     *
     * @param arr
     * @param n
     * @return
     */
    public static long solution(long[] arr,int n) {
        Stack<Integer> stack = new Stack<>();
        int[] L = new int[n];//左边比自己更小的数的下标
        int[] R = new int[n];//右边比自己更小的数的下标
        Arrays.fill(L,-1);
        Arrays.fill(R,n);
        stack.push(0);//将第一个数的下标放进去
        for (int i = 1; i < n; i++) {
            int down = stack.peek();
            if(arr[i] > arr[down]){
                L[i] = down;//左边第一个小于自己的数的下标为down
            }else if(arr[i] == arr[down]){
                L[i] = L[down];//左边第一个小于自己的数的下标为L[down]
            }else{
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                    down = stack.pop();
                    R[down] = i;
                }

                if(stack.isEmpty()){//说明前面没有比自己更小的数，那它就能延伸到开头位置
                    L[i] = -1;
                }else if(arr[i] > arr[stack.peek()]){
                    L[i] = stack.peek();
                }else if(arr[i] == arr[stack.peek()]){
                    L[i] = L[stack.peek()];
                }
            }

            stack.push(i);
        }

        long ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long m = (R[i] - L[i] -1 )*arr[i];
            ans = m > ans?m:ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextLong();
        }

        System.out.println(solution(nums,n));
    }
}
