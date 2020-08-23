package GinkgoStack.P24_Enterprise.Tencent.SummerInternship2017;
import java.util.*;


public class DeleteMinPalindrome {


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/28c1dc06bc9b4afd957b01acdf046e69
     * 来源：牛客网
     *
     * 提到回文串，自然要利用回文串的特点，想到将源字符串逆转后，“回文串”（不一定连续）相当于顺序没变.
     * 求原字符串和其反串的最大公共子序列（不是子串，因为可以不连续）的长度（使用动态规划很容易求得），
     * 然后用原字符串的长度减去这个最大公共子串的长度就得到了最小编辑长度。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();

            String s2 = new StringBuilder(s1).reverse().toString();

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];////最长公共子序列

            for (int i = 1; i < dp.length; i ++ ) {
                for (int j = 1; j < dp[0].length; j ++ ) {
                    //根据当前这两个字符相等情况，更新dp
                    dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ?
                            dp[i - 1][j - 1] + 1 :
                            Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

            System.out.println(s1.length() - dp[s1.length()][s2.length()]);
        }
    }


}
