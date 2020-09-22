package GinkgoStack.P20_DynamicProgramming.WaysOfDecoding;

import java.math.BigInteger;
import java.util.Scanner;


/**
 * 剑指 Offer 46. 把数字翻译成字符串
 *      * 类似题目：网易的笔试题，莫尔斯解码
 *      * 都是爬梯子的变型，只不过是有条件的加dp[j-2]
 *
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *
 * 提示：
 *
 * 0 <= num < 231
 */
public class HowManyWaysOfDecodingOffer46 {

    /**
     * 自己的解法，超过100%用户
     * AC
     * 类似题目：网易的笔试题，莫尔斯解码
     * 都是爬梯子的变型，只不过是有条件的加dp[j-2]
     */
    class Solution {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int n = s.length();
            if(n <= 1){
                return 1;
            }

            int[] dp  = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 1; i < n; i++) {
                int j = i+1;
                String t2 = s.substring(i-1,i+1);//s[i-1...i]子串，长度为2
                int curV = dp[j-1];
                if(s.charAt(i-1) != '0' && Integer.parseInt(t2) <= 25){//注意 02 ，03 这种有前导0不算，不能重复计算
                    curV += dp[j-2];
                }
                dp[j] = curV;
            }

            return dp[n];
        }
    }
}
