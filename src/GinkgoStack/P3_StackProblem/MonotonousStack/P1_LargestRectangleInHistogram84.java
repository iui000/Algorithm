package GinkgoStack.P3_StackProblem.MonotonousStack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class P1_LargestRectangleInHistogram84 {
    /**
     * 这道题和字节的两道笔试题是一回事，代码可以复用
     * LeftRightNearestLargerValue
     * MaxValue_MinValueOfKlengthSubarrMutiK
     */
    /**
     * 自己的解法
     * 单调栈 递增
     * 以当前这个数为最小值，向左右扩张
     * 找到一个数左边和右边离自己最近的小于自己的数的下标
     *
     * @param arr
     * @param n
     * @return
     */
    public static int solution(int[] arr,int n) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

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

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int m = (R[i] - L[i] -1 )*arr[i];
            ans = m > ans?m:ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(solution(nums,n));
    }

    /**
     * 写法2
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xu5al5/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            int l = heights.length;
            Stack<Integer> stack = new Stack<>();
            int result = 0;

            //出栈的时候才会计算以出栈柱子为高度的矩形面积，
            // 最后一个柱子如何出栈呢？我们push进去一个-1，
            // 保证最后一个跟柱子也会出栈，并且计算它对应的矩形面积。
            for (int i = 0; i <= l; i++) {
                int cur = i == l ? -1 : heights[i];
                while(!stack.isEmpty() && cur <= heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int left = stack.isEmpty() ? 0: (stack.peek() + 1);
                    int right = i - 1;
                    int erea = height * (right - left + 1);
                    result = Math.max(result, erea);
                }
                stack.push(i);
            }

            return result;
        }
    }


}
