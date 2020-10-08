package GinkgoStack.P20_DynamicProgramming;

import java.util.Scanner;

/**
 * 1 求n个节点 高度不超过m的二叉树的个数 结果%1e9+7
 *
 * dp[i][j]代表i个结点不超过j的方案数
 */
public class numbersTreesHighM {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();

        int mod = 1_000_000_007;
        int[][] dp = new int[55][55];
        for(int i=1;i<=n;i++)
        {
            dp[0][i-1] = 1;
            for(int j=1;j<=n;j++)
            {
                for(int k=0;k<j;k++)
                {
                    dp[j][i] =(dp[j][i]%mod+ dp[k][i-1] * dp[j-k-1][i-1]%mod)%mod;
                }
            }
        }

        System.out.println(dp[n][h]);
    }
}

