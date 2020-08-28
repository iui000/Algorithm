package GinkgoStack.P24_Enterprise.Tencent.qiu2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PalindromeSegmentation {

    private static int fun(String s){
        int len = s.length();

        if(len < 2){
            return 0;
        }

        int[] dp = new int[len];

        for (int i = 0;i<len;i++){
            dp[i] = i;
        }

        boolean[][] can = new boolean[len][len];
        for (int ri = 0;ri<len;ri++){
            for (int le = 0;le <= ri;le++){
                if(s.charAt(le) == s.charAt(ri)
                        && (ri - le <= 2 || can[le+1][ri-1])
                ){
                    can[le][ri] = true;
                }
            }
        }

        for (int i = 1;i<len;i++){
            if(can[0][i]){
                dp[i] =0;
                continue;
            }

            for (int j = 0;j<i;j++){
                if(can[j+1][i]){
                    dp[i] = Math.min(dp[i],dp[j]+1);
                }
            }
        }

        return dp[len -1];
    }

    /*
    ababa
    4
    1 4
    1 5
    1 2
    1 3

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int Q = sc.nextInt();

        HashMap<String ,Integer> m = new HashMap<>();
        for (int i = 0;i<Q;i++){
            int left = sc.nextInt();
            int right = sc.nextInt();

            if(left == right){
                System.out.println(1);
            }else if(right > left) {

                if(m.containsKey(left+","+right)){
                    System.out.println(m.get(left+","+right));
                }else {
                    String tmp = s.substring(left-1,right);
                    int kk = fun(tmp) +1;
                    System.out.println(kk);
                    m.put(left+","+right,kk);
                }

            }else{
                System.out.println(1);
            }

        }
    }
}
