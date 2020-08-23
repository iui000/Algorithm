package GinkgoStack.P24_Enterprise.bilibili;

import java.util.Arrays;


/**
 * 现在有面值为1024的纸币
 * 买一件东西价值为N（<=1024）
 * 有四种硬币面值{1,4,16,64}
 * 每种硬币无限选，是个完全背包问题
 *
 * 求最少的硬币数量
 * 原题是：leetcode
 * 322. 零钱兑换
 */
public class coinChange {

    public static int GetCoinCount (int N) {
        // write code here
        int[] co = new int[]{1,4,16,64};
        int total = 1024-N;
        int m = total+1;
        int[] dp = new int[m];
        Arrays.fill(dp,m);//填充一个不可能达到的数
        dp[0] = 0;
        for(int i = 1;i<=total;i++){
            for (int j = 0;j<co.length;j++){
                if(co[j] <= i){
                    dp[i] = Math.min(dp[i],dp[i - co[j]]+1);
                }
            }
        }
        return dp[total] > total?-1:dp[total];
    }

    public static void main(String[] args) {
        System.out.println(GetCoinCount(200));

    }
}
