package GinkgoStack.P24_Enterprise;


import java.util.Scanner;

/**
 * 可能没有完全AC
 */
public class calories {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int t = 0;

        while (t<T && sc.hasNextInt()){
            int n = sc.nextInt();//n瓶水
            int VWater = sc.nextInt();//至少需要摄入的水量
            int[] volumeWater = new int[n + 1];
            int[] calories = new int[n + 1];
            int maxP = 0;

            for (int i = 1; i <= n; ++i) {
                volumeWater[i] = sc.nextInt();
                calories[i] = sc.nextInt();
                if(volumeWater[i]>maxP && volumeWater[i]<= VWater){
                    maxP = volumeWater[i];
                }
            }
            int M = maxP + VWater-1;
            int[] dp = new int[M + 1];
            dp[0] = 0;//
            for (int j = 1; j <= M; ++j) {
                dp[j] = Integer.MAX_VALUE;
            }

            for (int i = 1; i < n + 1; ++i) {
                for (int j = volumeWater[i]; j <= M; ++j) {

                    if (dp[j] == Integer.MAX_VALUE && dp[j - volumeWater[i]] == Integer.MAX_VALUE) {
                        dp[j] = Integer.MAX_VALUE;

                    } else if (dp[j] == Integer.MAX_VALUE) {
                        dp[j] = dp[j - volumeWater[i]] + calories[i];

                    } else if (dp[j - volumeWater[i]] == Integer.MAX_VALUE) {
                        dp[j] = dp[j];
                    } else {
                        dp[j] = Math.min(dp[j], dp[j - volumeWater[i]] + calories[i]);
                    }
                }
                for (int j = volumeWater[i] - 1; j >= 0; --j) {
                    dp[j] = Math.min(dp[j], calories[i]);
                }
            }

            int mm = VWater;
            while (Integer.MAX_VALUE == dp[mm]){
                mm++;
            }
            System.out.println(dp[mm]);

            t++;
        }
        sc.close();
    }
}