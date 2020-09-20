package GinkgoStack.P2_StringProblem.Palindrome;

/**
 * 132. 分割回文串 II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 */
public class PalindromePartitioningII132 {

    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xqtnqm/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int minCut(String s) {
            int len = s.length();
            //用中心扩张法 把所有的回文子串给找出来
            boolean[][] palin = allPalins(s, len);

            int[] dp = new int[len + 1];//表示前i个字符最少可以划分成的回文串个数
            dp[0] = 0;//初始状态，空串

            //转移方程：dp[i] = min(dp[j] +1),其中j满足条件，substring[j,i]是回文，且j = 0...i-1
            for (int i = 1; i <= len; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {//j <= i-1
                    if (palin[j][i - 1]) {//假设按照j这个地方分割，后面的串是否为回文串
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp[len] - 1;//分割次数是回文串数量-1
        }

        /**
         * 用中心扩张法 把所有的回文子串给找出来
         * @param s
         * @param len
         * @return
         */
        boolean[][] allPalins(String s, int len) {
            boolean[][] palins = new boolean[len][len];
            int i, j, mid;

            for (mid = 0; mid < len; mid++) {

                //回文中心是一个字符
                i = j = mid;
                while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
                    palins[i][j] = true;
                    i--;
                    j++;
                }

                //回文中心是两个字符
                i = mid - 1; j = mid;
                while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
                    palins[i][j] = true;
                    i--;
                    j++;
                }
            }
            return palins;
        }
    }


}
