package GinkgoStack.P2_StringProblem.RepeatingSubstring;


/**
 * 1044. 最长重复子串
 * 子串下标可以有交集；
 * 和1066没区别，只是这道题是把任意一个具有最长长度的重复子串输出，不是输出长度，还是用我的板子
 *
 * 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
 *
 * 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 ""。）
 *
 *
 *
 * 示例 1：
 *
 * 输入："banana"
 * 输出："ana"
 * 示例 2：
 *
 * 输入："abcd"
 * 输出：""
 *
 *
 * 提示：
 *
 * 2 <= S.length <= 10^5
 * S 由小写英文字母组成。
 */
public class LongestRepeatingSubstring1044 {


    /**
     * 我的解法，字符串太长时10^5，可能会超时
     */
    class Solution1 {




        public String longestDupSubstring(String s) {
            int maxLen = 0;
            String pattern = "";
            String remaining = "";
            String ans = "";
            for (int begin = 0; begin < s.length(); begin++) {
                //在余下的串remaining中不可能包含一个长度比maxLen更大的重复串了
                if(begin + (maxLen + 1) +1 > s.length()){
                    break;
                }

                /**
                 * len起始值 设为maxLen + 1，是为了提高效率，
                 * 因为目标是找到更长的串，而更短的串没有必要计算了，也避免一部分冗余计算。
                 * 这里len是线性增长，更优的办法是二分：因为有这样一个事实：
                 * 如果字符串中存在长度为 L 的重复子串，那么一定存在长度为 L0 < L 的重复子串，因此可以使用二分查找的方法，找到最大的 L。
                 */
                for (int len = maxLen + 1; begin + len +1 <= s.length(); len++) {
                    pattern = s.substring(begin,begin+len);
                    //因为可以重叠，所以余下的串起始位置是
                    remaining = s.substring(begin+1);
//                    if(KMP(remaining,pattern) != -1){//KMP不一定高效，这道题关键在于len增长是线性很耗时
                    if(remaining.contains(pattern)){
                        if(pattern.length() > maxLen){
                            maxLen = pattern.length();
                            ans = pattern;
                        }
                    }else {
                        //如果在后面的串中不包含当前的pattern，
                        // 那pattern继续增大，而remaining缩短，
                        // 就更不可能包含pattern，因此直接结束内层循环
                        break;
                    }
                }
            }

            return maxLen == 0 ? "":ans;
        }


        private int[] getNextArray(String s){
            if(s.length() == 1){
                return new int[]{-1};
            }
            char[] model = s.toCharArray();
            int len = s.length();
            int[] next = new int[len];//表示的是前缀和后缀相等的最大长度
            next[0] = -1;
            next[1] = 0;
            int pre = 0;
            int cur = 2;
            while (cur < len){
                if(model[cur-1] == model[pre]){
                    next[cur++] = ++pre;
                }else if(pre > 0) {
                    pre = next[pre];
                }else {//最左位置
                    next[cur++] = 0;
                }
            }

            return next;
        }

        private int KMP(String s,String t){
            int[] next = getNextArray(t);
            int i = 0;
            int j = 0;
            while (i < s.length() && j<t.length() ){
                if (s.charAt(i) == t.charAt(j)){
                    i++;
                    j++;
                }else if(next[j] == -1){//next[0] = -1;
                    i++;
                }else {
                    j = next[j];//滑动
                }
            }
            return  j == t.length() ? i-j:-1;
        }
    }


    /**
     * 方法2：二分查找 + Rabin-Karp 字符串编码
     * 官方题解
     *
     * 我们可以把这个问题分解成两个子问题：
     *
     * 从 1 到 N 中选取子串的长度 L；
     *
     * 检查字符串中是否存在长度为 L 的重复子串。
     *
     * 子任务一：二分查找
     * 如果字符串中存在长度为 L 的重复子串，那么一定存在长度为 L0 < L 的重复子串，因此可以使用二分查找的方法，找到最大的 L。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring/solution/zui-chang-zhong-fu-zi-chuan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


}
