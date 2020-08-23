package GinkgoStack.P24_Enterprise.DiDi;

/**
 * 数字和为sum的方法数
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 给定一个有n个正整数的数组A和一个整数sum,求选择数组A中部分数字和为sum的方案数。
 * 当两种选取方案有一个数字的下标不一样,我们就认为是不同的组成方案。
 *
 * 输入描述:
 * 输入为两行:
 *  第一行为两个正整数n(1 ≤ n ≤ 1000)，sum(1 ≤ sum ≤ 1000)
 *  第二行为n个正整数A[i](32位整数)，以空格隔开。
 *
 * 输出描述:
 * 输出所求的方案数
 *
 * 输入例子1:
 * 5 15
 * 5 5 10 2 3
 *
 * 输出例子1:
 * 4
 */
import java.util.*;
public class waysOfArrSumK {

    /**
     * DP 0-1背包变型
     * 如果用递归回溯的话，应该会超时
     * dp[j]表示的是前i个物数的和为j的方案数
     */
    public static long Knapsack01(int[] weight, int n, int sum){
        long[] dp=new long[sum+1];
        dp[0]=1;//和为0的方案数为1
        int i,j;

        for(i=0;i<n;i++){

            for(j = sum;j>=weight[i];j--){
                //前i-1个数之和达到j的方案数 + 前i-1个数和为j-weight[i]的方案数
                dp[j] = dp[j] + dp[j-weight[i]];
            }
        }
        return dp[sum];
    }

    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int sum=s.nextInt();
        int i,j;
        int arr[]=new int[n];

        for(i=0;i<n;i++){
            arr[i]=s.nextInt();
        }

        System.out.println(Knapsack01(arr,n,sum));
    }
}
