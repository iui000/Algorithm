package GinkgoStack.P20_DynamicProgramming.WaysOfDecoding;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 网易笔试
 * 莫尔斯编码
 * 爬梯子的变型
 *
 * AC代码
 *         map.put("0","A");
 *         map.put("1","B");
 *         map.put("10","C");
 *         map.put("11","D");
 *
 *         map.put("100","E");
 *         map.put("101","F");
 *         map.put("110","G");
 *         map.put("111","H");
 *
 *         对01进行解码，方案数有多少？
 */
public class MorseCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();
        if(n == 0){
            System.out.println(1);
            return;
        }

        int[] dp  = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        //可以用set，用不着Map,懒得改了哈0.0
        Map<String,String> map = new HashMap<>();
        map.put("0","A");
        map.put("1","B");
        map.put("10","C");
        map.put("11","D");
        map.put("100","E");
        map.put("101","F");
        map.put("110","G");
        map.put("111","H");

        if(n >= 2){
            String x = s.substring(0,2);
            if(map.containsKey(x)){
                dp[2] = 2;
            }else {
                dp[2] = 1;
            }
        }
        for (int i = 2; i < s.length(); i++) {
            int j = i+1;
            String t2 = s.substring(i-1,i+1);
            String t3 = s.substring(i-2,i+1);
            int curV = dp[j-1];
            if(map.containsKey(t2)){
                curV += dp[j-2];
            }
            if(map.containsKey(t3)){
                curV += dp[j-3];
            }
            dp[j] = curV;
        }
        System.out.println(dp[n]);
    }
}

