package GinkgoStack.P20_DynamicProgramming;

import java.util.Stack;

/**
 * 32. 最长有效括号（相同题：最长的有效括号子串,牛客）
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses32 {


    /**
     * leetcode上有三种解法，牛客上的解法基本来源于leetcode,不过好理解一点
     */

    class Solution {

        /**
         * 牛客解法
         * 动态规划
         * dp[i]表示以i结尾最长合法字符串。
         * 如果s[i]=='('时该字符串一定不合法；
         * 
         * 当s[i]==')'时，假设存在解，
         * 那么该右括号与其对应的左括号之间的字符串一定是合法的。
         * 因此对于i-1的位置，以i-1结尾的合法字符串，记作pres,那么pres起始下标为i - dp[i - 1]，记为j
         * 当j的前一个位置 s[i - 1 - dp[i - 1]] == '('时，可以与s[i]进行匹配，dp[i]更新为dp[i - 1] + 2，我们把i - 1 - dp[i - 1]这个位置记为preHead
         * 
         * 此时还需要注意，对于preHead的前一个位置(i - 1 - dp[i - 1]) - 1，
         * 如果以该处（preHead-1）为结尾的最长合法字符串不为0，也需要累加到结果上。例如()()。
         * @param s string字符串
         * @return int整型
         */
        public int longestValidParentheses(String s) {
    
            int[] dp = new int[s.length()];
            
            int ans = 0;
            for(int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    
                    //当j的前一个位置 s[i - 1 - dp[i - 1]] == '('时，可以与s[i]进行匹配，
                    // dp[i]更新为dp[i - 1] + 2，我们把i - 1 - dp[i - 1]这个位置记为preHead
                    int preHead = i - 1 - dp[i - 1];
                    
                    if (preHead >= 0 && s.charAt(preHead) == '('){
                        dp[i] = dp[i - 1] + 2;
                        //如果以该处（preHead-1）为结尾的最长合法字符串不为0，也需要累加到结果上。例如()()。
                        if (preHead - 1 >= 0)
                            dp[i] += dp[preHead - 1];
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }




    class Solution2 {

        /**
         * 牛客解法
         * 辅助栈
         *
         * 维护一个栈，保存左括号的下标，如果遇到右括号，则弹出一个左括号，并且更新长度。
         * 注意到一个特殊情况如(())()，我们需要保存栈空时的起始节点：
         * @param s string字符串
         * @return int整型
         */
        public int longestValidParentheses(String s) {
            // write code here

            Stack<Integer> st = new Stack<>();
            int ans = 0, last = -1;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '(')
                    st.push(i);

                else {
                    if (st.empty())
                        last = i;

                    else {
                        st.pop();
                        ans = st.empty() ?
                                Math.max(ans, i-last) :
                                Math.max(ans, i-st.peek());
                    }
                }
            }
            return ans;
        }
    }


}
