package GinkgoStack.P20_DynamicProgramming.LinearDP.knapsackProblem;

import java.util.Scanner;

/**
 *
 * 这类问题样子看起来和传统的背包问题很像，但是更为困难
 * 它是求最小损耗，分别有两种情况：
 * 1.被选择物品的总大小必须 >= 背包大小
 * 2.被选择物品的总大小必须 <= 背包大小
 *
 * 然后上面两种情况又分别有，0-1，完全，多重
 *
 * 例子：
 * 百度编程大赛老是有这种类型的题目
 *
 */
public class MinValueKnapsack {

    /**
     * 链接：https://ac.nowcoder.com/acm/contest/3405/C?&headNav=acm
     * 来源：牛客网
     * 总体积不小于V的前提下，物品的总价值最小是多少。
     */
    private static int  minValueKnapsack01(int[] volume,int[] value,int V,int valueSum){
        int n = volume.length-1;

        int[] dp = new int[valueSum+1];

        for(int i=1;i<=n;i++)
        {
            //01背包是从后往前
            for(int j = valueSum;j >= value[i];j--)
            {
                dp[j]=Math.max(dp[j],
                        dp[j-value[i]] + volume[i]);
            }
        }

        //找到第一个满足 体积不小于V 的价值
        for(int i=1;i<= valueSum;i++)
        {
            if(dp[i] >= V)
            {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();//n个物品
        int V = sc.nextInt();//体积

        int[] volume = new int[n+1];
        int[] value = new int[n+1];

        int valueSum = 0;
        for (int i = 1; i <= n; ++i) {
            volume[i] = sc.nextInt();
            value[i] = sc.nextInt();
            valueSum += value[i];
        }

        System.out.println(minValueKnapsack01(volume,value,V,valueSum));
    }

}
