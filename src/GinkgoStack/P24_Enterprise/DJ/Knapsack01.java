package GinkgoStack.P24_Enterprise.DJ;


import java.util.Scanner;

public class Knapsack01 {

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

    /**
     * 样例输入
    2 2
    10 1
    20 2

     * 20
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] items = new int[n][2];
        for(int i = 0;i<n;i++){
            items[i][0] = sc.nextInt();
            items[i][1] = sc.nextInt();
        }


        System.out.print(Knapsack01Optimize(items,m));

    }
}
