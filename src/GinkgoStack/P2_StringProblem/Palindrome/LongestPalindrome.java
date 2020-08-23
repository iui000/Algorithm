package GinkgoStack.P2_StringProblem.Palindrome;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindrome {

    /**
     * 解法1：DP
     *      * 时间复杂度：O(n^2)
     *      * 空间复杂度：O(n^2)
     * 对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
     *
     * 根据这样的思路，我们就可以用动态规划的方法解决本题。
     * 我们用 DP(i,j)表示字符串 s的第 i 到 j个字母组成的串（下文表示成 s[i:j]是否为回文串.
     * 状态转移方程分三种情况：
     *
     * 1.只有 s[i+1:j-1]是回文串，并且 s的第 i 和 j个字母相同时，s[i:j]才会是回文串；
     * 2.动态规划中的边界条件，即子串的长度为 1 或 2。
     *      对于长度为 1的子串，它显然是个回文串；
     *      对于长度为 2的子串，只要它的两个字母相同，它就是一个回文串。
     *
     *
     * 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，
     * 因此一定要注意动态规划的循环顺序。
     * @param s
     * @return
     */
    public static String longestPalindromeByDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        String ans = "";
        for (int len = 0; len < n; ++len) {

            for (int i = 0; i + len < n; ++i) {
                int j = i + len;
                if (len == 0) {//单个字母当然是回文
                    dp[i][j] = true;
                }
                else if (len == 1) {//如果连续两个字母相同，也是回文
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                else {//长度大于2的串
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }

                if (dp[i][j] && len + 1 > ans.length()) {//找最长的回文串
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }


    /**
     * 方法二：中心扩展算法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
     * 我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * 可以发现，所有的状态在转移的时候的可能性都是唯一的。也就是说，我们可以从每一种边界情况开始「扩展」，也可以得出所有的状态对应的答案。
     *
     * 边界情况即为子串长度为 11 或 22 的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。如果两边的字母相同，我们就可以继续扩展，例如从 P(i+1,j-1)P(i+1,j−1) 扩展到 P(i,j)P(i,j)；如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
     *
     * 「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * @param s
     * @return
     */
    public String longestPalindromeByCenterExpand(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    /**
     * 方法三：Manacher 算法
     * 还有一个复杂度为 O(n)的 Manacher 算法
     * 看官方题解
     * @param args
     */

    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindromeByDP(s));
    }

}
