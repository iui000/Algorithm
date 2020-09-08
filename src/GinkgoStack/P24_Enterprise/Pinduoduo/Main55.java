package GinkgoStack.P24_Enterprise.Pinduoduo;

import java.util.HashMap;
import java.util.Scanner;


public class Main55 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] orders = new int[N][2];
        for (int i = 0; i < N; i++) {
            orders[i][1] = scanner.nextInt();
            orders[i][0] = scanner.nextInt();
        }
        // 委托 solution 进行求解
        solution(orders,M);
    }

    static void solution(int[][] orders,int m) {
        // 排除一些基本的边界情况
        if (orders.length == 0) {
            System.out.println(0);
            return;
        }
        // 委托 dp 函数执行具体的算法逻辑
        int res = Knapsack01Optimize(orders, m);
        // 负责输出结果
        System.out.println(res);
    }

    public static int Knapsack01Optimize(int[][] items,int m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }
        int[] dp = new int[m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;

        for(int i = 1;i<=n;i++){//每个物品

            for(int j=m;j>=items[i-1][1];j--){

                remainingCapacity =  j-items[i-1][1];//剩余容量
                theValueIfSelected = items[i-1][0] + dp[remainingCapacity];

                dp[j] = Integer.max(dp[j],theValueIfSelected);
            }
        }
        return dp[m];
    }
}

