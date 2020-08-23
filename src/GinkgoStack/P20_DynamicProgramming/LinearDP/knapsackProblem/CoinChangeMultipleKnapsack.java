package GinkgoStack.P20_DynamicProgramming.LinearDP.knapsackProblem;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 每种面值得硬币都有给定数量
 * 求出最小的硬币数量，使其面值总和不小于给定值
 */
public class CoinChangeMultipleKnapsack {

    private static int co(int[] c,int[] number,int sum){
        int n = c.length-1;

        int[] dp = new int[sum+1];
        Arrays.fill(dp,sum+1);
        dp[0] = 0;
        for(int i = 1;i<=n;i++){//每个物品
            for(int j=sum;j>=c[i];j--){
                //表示i这个物品的数量
                for(int k = 0;k <= number[i];k++){
                    if(j - k * c[i] < 0)//如果当前容量并不能容纳k个物品i,则结束这个循环，去看看容量增大一个单元后行不行
                        break;
                    dp[j] = Integer.min(dp[j],dp[j-k*c[i]]+k);
                }

            }
        }

        return dp[sum] == (sum+1) ?-1:dp[sum];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i =1;
        int[] co= new int[]{0,1,5,10,50,100};
        int[] number =  new int[6];
        int sum = 0;
        while (i<=5){
            number[i] = scanner.nextInt();
            i++;
        }

        sum = scanner.nextInt();
        if(sum < 1){
            System.out.print(-1);
        }else {
            int res = co(co,number,sum);
            System.out.print(res);
        }
    }
}
