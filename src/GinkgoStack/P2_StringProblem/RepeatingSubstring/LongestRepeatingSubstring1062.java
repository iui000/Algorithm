package GinkgoStack.P2_StringProblem.RepeatingSubstring;

/**
 * 1062. 最长重复子串
 *
 * 子串下标可以有交集
 *
 * 给定字符串 S，找出最长重复子串的长度。如果不存在重复子串就返回 0。
 *
 *
 * 示例 1：
 *
 * 输入："abcd"
 * 输出：0
 * 解释：没有重复子串。
 * 示例 2：
 *
 * 输入："abbaba"
 * 输出：2
 * 解释：最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
 * 示例 3：
 *
 * 输入："aabcaabdaab"
 * 输出：3
 * 解释：最长的重复子串为 "aab"，出现 3 次。
 * 示例 4：
 *
 * 输入："aaaaa"
 * 输出：4
 * 解释：最长的重复子串为 "aaaa"，出现 2 次。
 *
 *
 * 提示：
 *
 * 字符串 S 仅包含从 'a' 到 'z' 的小写英文字母。
 * 1 <= S.length <= 1500
 */
public class LongestRepeatingSubstring1062 {


    /**
     * 我的AC代码
     * 和子串之间的下标不能有交集的区别有这么几点：
     * 第一：remaining的起始位置
     * 第二：结束循环的长度条件
     *
     */
    class Solution {
        public int longestRepeatingSubstring(String s) {
            int maxLen = 0;
            String pattern = "";
            String remaining = "";

            for (int begin = 0; begin < s.length(); begin++) {
                //在余下的串remaining中不可能包含一个长度比maxLen更大的重复串了
                if(begin + (maxLen + 1) +1 > s.length()){
                    break;
                }

                /**
                 * len起始值 设为maxLen + 1，是为了提高效率，
                 * 因为目标是找到更长的串，而更短的串没有必要计算了，也避免一部分冗余计算
                 */
                for (int len = maxLen + 1; begin + len + 1 <= s.length(); len++) {
                    pattern = s.substring(begin,begin+len);
                    //因为下标可以有交集，所以余下的串起始位置是begin+1
                    remaining = s.substring(begin+1);

                    if(remaining.contains(pattern)){
                        if(pattern.length() > maxLen){
                            maxLen = pattern.length();
                        }
                    }else {
                        //如果在后面的串中不包含当前的pattern，
                        // 那pattern继续增大，而remaining缩短，
                        // 就更不可能包含pattern，因此直接结束内层循环
                        break;
                    }
                }
            }

            return maxLen;
        }
    }
}
