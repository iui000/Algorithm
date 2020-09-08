package GinkgoStack.P20_DynamicProgramming;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素,
 * 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 */
public class RegularExpressionMatching {

    /**
     *     作者：jerry_nju
     *     链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * DP
     * 假设主串为 AA，模式串为 BB 从最后一步出发，需要关注最后进来的字符。
     * 假设 AA 的长度为 nn ，BB 的长度为 mm ，关注正则表达式 BB 的最后一个字符是谁，
     * 它有三种可能，正常字符、 .（点）*，那针对这三种情况讨论即可
     *
     * 转移方程
     * primeFactor[i] [j]primeFactor[i][j] 代表 AA 的前 ii 个和 BB 的前 jj 个能否匹配
     *
     * 对于前面两个情况，可以合并成一种情况 primeFactor[i][j] = primeFactor[i-1][j-1]primeFactor[i][j]=primeFactor[i−1][j−1]
     *
     * 对于第三种情况，对于 c*c∗ 分为看和不看两种情况
     *
     * 不看：直接砍掉正则串的后面两个， primeFactor[i][j] = primeFactor[i][j-2]primeFactor[i][j]=primeFactor[i][j−2]
     * 看：正则串不动，主串前移一个，primeFactor[i][j] = primeFactor[i-1][j]primeFactor[i][j]=primeFactor[i−1][j]
     * 初始条件
     * 特判：需要考虑空串空正则
     *
     * 空串和空正则是匹配的，primeFactor[0][0] = truef[0][0]=true
     * 空串和非空正则，不能直接定义 truetrue 和 falsefalse，必须要计算出来。（比如A=A= '' '' ,B=a*b*c*B=a∗b∗c∗）
     * 非空串和空正则必不匹配，primeFactor[1][0]=...=primeFactor[n][0]=falsef[1][0]=...=primeFactor[n][0]=false
     * 非空串和非空正则，那肯定是需要计算的了。
     *
     * 作者：jerry_nju
     * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public boolean isMatch(String A, String B) {
            int n = A.length();
            int m = B.length();
            boolean[][] f = new boolean[n + 1][m + 1];

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    //分成空正则和非空正则两种
                    if (j == 0) {
                        //空串和空正则是匹配的，primeFactor[0][0] = true
                        f[i][j] = i == 0;
                    } else {
                        //非空正则分为两种情况 * 和 非*
                        if (B.charAt(j - 1) != '*') {
                            if (i > 0 && (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '.')) {
                                f[i][j] = f[i - 1][j - 1];
                            }
                        } else {
                            //碰到 * 了，分为看和不看两种情况
                            //不看
                            if (j >= 2) {
                                f[i][j] |= f[i][j - 2];
                            }
                            //看
                            if (i >= 1 && j >= 2 && (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.')) {
                                f[i][j] |= f[i - 1][j];
                            }
                        }
                    }
                }
            }
            return f[n][m];
        }
    }


}
