package GinkgoStack.P3_StackProblem;

import java.util.Scanner;
import java.util.Stack;

/**
 * 字节笔试题
 *
 * L[i] 表示左边第一个大于自己的数的下标；
 * R[i] 表示右边第一个大于自己的数的下标；
 *
 * 求最大的 L[i] * R[i]
 *
 * 其实这道题很简单，单调栈（递减），可惜当时漏掉了一点细节，只A了60%左右
 */
public class LeftRightNearestLargerValue {

    public static int solution(int[] arr,int n) {
        Stack<Integer> stack = new Stack<>();
        int[] L = new int[n+1];
        int[] R = new int[n+1];
        stack.push(1);
        for (int i = 2; i <= n; i++) {
            int down = stack.peek();
            if(arr[i] < arr[down]){
                L[i] = down;
            }else if(arr[i] == arr[down]){
                L[i] = L[down];
            }else{
                while (!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                    down = stack.pop();
                    R[down] = i;
                }

                if(stack.isEmpty()){
                    L[i] = 0;
                }else if(arr[i] < arr[stack.peek()]){//当时漏掉了这个情况
                    L[i] = stack.peek();
                }else if(arr[i] == arr[stack.peek()]){
                    L[i] = L[stack.peek()];
                }
            }

            stack.push(i);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int m =L[i] * R[i];
            ans = ans < m?m:ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(solution(arr,n));

    }
}
