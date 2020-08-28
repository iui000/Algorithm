package GinkgoStack.P24_Enterprise.Tencent;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;
public class minRestDays2 {



        public static void main(String args[]) throws Exception{
            //File file = new File("in.txt");
            //Scanner sc = new Scanner(file);
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            int []keep = new int[n+1];
            int []work = new int[n+1];
            for(int i = 1; i <= n; i++) work[i] = sc.nextInt();
            for(int i = 1; i <= n; i++) keep[i] = sc.nextInt();

            int [][] dp = new int[3][n+1];

            //第一天休息
            dp[0][1] = 1;

            //第一天工作
            if(work[1] == 1) dp[1][1] = 0;
            else dp[1][1] = n + 1;

            //第一天健身
            if(keep[1] == 1) dp[2][1] = 0;
            else dp[2][1] = n + 1;

            for(int i = 2; i <= n; i++) {
                dp[0][i] = Math.min(Math.min(dp[1][i-1], dp[2][i-1]), dp[0][i-1]) + 1;

                if (work[i] == 1) {
                    dp[1][i] = Math.min(dp[2][i-1], dp[0][i-1]);
                }else
                    dp[1][i] = n + 1;

                if(keep[i] == 1) {//健身
                    dp[2][i] = Math.min(dp[1][i-1], dp[0][i-1]);
                }else
                    dp[2][i] = n + 1;
            }

            System.out.println(Math.min(Math.min(dp[0][n], dp[1][n]), dp[2][n]));
            sc.close();
        }


}
