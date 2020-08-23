package GinkgoStack.P6_SlidingWindow;

import javafx.util.Pair;

import java.util.*;

/**
 * 727. 最小窗口子序列
 * 给定字符串 S and T，找出 S 中最短的（连续）子串 W ，使得 T 是 W 的 子序列 。
 *
 * 如果 S 中没有窗口可以包含 T 中的所有字符，返回空字符串 ""。如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。
 *
 * 示例 1：
 *
 * 输入：
 * S = "abcdebdde", T = "bde"
 * 输出："bcde"
 * 解释：
 * "bcde" 是答案，因为它在相同长度的字符串 "bdde" 出现之前。
 * "deb" 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。
 *
 *
 * 注：
 *
 * 所有输入的字符串都只包含小写字母。All the strings in the input will only contain lowercase letters.
 * S 长度的范围为 [1, 20000]。
 * T 长度的范围为 [1, 100]。
 */
public class MinimumWindowSubsequence {

    /**
     * 自己的思路
     * 先正向扩张
     * 再反向匹配找到当前最小窗口的起始位置，并找到新的滑动位置i
     * 滑动窗口
     * AC代码
     * 超过50%用户,性能和官方的DP解法一样，空间效率更高一些
     * @return
     */
    public static String minWindow(String s, String t) {

        Deque<Pair<Character,Integer>> queue = new LinkedList<>();

        String ans = "";
        int len = Integer.MAX_VALUE;
        for (int i = 0;i<s.length();){

            //向右扩张，正向匹配
            int j = 0;
            while (j<t.length() && i<s.length()){
                queue.addLast(new Pair<>(s.charAt(i),i));
                if(s.charAt(i) == t.charAt(j)){
                    j++;
                }
                i++;
            }

            if(j != t.length())break;

            //说明找到了一个序列
            //从右边往左找到这个最小的串
            int begin = 0;//子串的起始位置
            int end = queue.peekLast().getValue();//子串的结束位置

            //获取一个逆向的迭代器
            Iterator<Pair<Character,Integer>> iterator = queue.descendingIterator();
            j--;
            /**
             * 反向匹配，找到当前最小窗口的起始位置begin
             * 并且找到新的滑动位置i
             */
            while (j >= 0){
                Pair<Character,Integer> pair = iterator.next();
                if(pair.getKey() == t.charAt(0) && j != 0){
                    //下一轮新的i值，相当于队列左边收缩
                    i = pair.getValue();
                }

                //反向匹配
                if(pair.getKey() == t.charAt(j)){
                    j--;
                }
                begin = pair.getValue();
            }

            if(end+1 - begin < len){
                len = end - begin +1;
                ans = s.substring(begin,end+1);
            }

            //如果已经遍历到s末尾，就直接结束
            if(end == s.length()-1)
                break;

            //最后清楚队列
            queue.clear();
        }

        return ans;
    }

    /**
     *
     * 官方的DP
     * 超过50%用户
     * @param S
     * @param T
     * @return
     */
    public String minWindowByDP(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] next = new int[N][26];
        Arrays.fill(last, -1);

        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                next[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); ++j) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && next[window[1]+1][letterIndex] >= 0) {
                    window[1] = next[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};
        for (int[] window: windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }

        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }


    public static void main(String[] args) {
       String  s = "abcdebdde", t = "bde";
       System.out.println(minWindow(s,t));
    }
}
