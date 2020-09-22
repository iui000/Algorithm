package GinkgoStack.P2_StringProblem.RepeatingSubstring;

import java.util.Arrays;

public class ShortestLoopSubstring {
    /**
     * 字节笔试AC代码
     * 相同题目：459. 重复的子字符串
     *
     * 相关帖子：
     * POJ 2406 Power Strings最短循环子串
     * https://blog.csdn.net/hao_zong_yin/article/details/75168313
     *
     * 题意：给你一个字符串，如果这个字符串可以由某个子串重复多次构成，那么输出最大重复次数。
     *
     * 如abcd 输出 1；
     *
     *     aaaa 输出 4；
     *
     *     ababab 输出 3；
     *
     * 定理：假设S的长度为len，则S存在循环子串，当且仅当，len可以被len - next[len]整除，最短循环子串为S[len - next[len]]
     * 例子证明：
     *
     * 设S=q1q2q3q4q5q6q7q8，并设next[8] = 6，此时str = S[len - next[len]] = q1q2，
     * 由字符串特征向量next的定义可知，q1q2q3q4q5q6 = q3q4q5q6q7q8，即有q1q2＝q3q4，q3q4＝q5q6，q5q6＝q7q8，
     * 即q1q2为循环子串，且易知为最短循环子串。由以上过程可知，若len可以被len - next[len]整除，则S存在循环子串，否则不存在。
     * 解法：利用KMP算法，求字符串的特征向量next，若len可以被len - next[len]整除，则最大循环次数n为len/(len - next[len])，否则为1
     */
    public String shortestLoopSubstring(String s) {
        int n = s.length();
        if(n <= 1){
            return s;
        }

        int[] next = getNext(s);
        int loopStrLen = n - next[n-1];
        if(n % loopStrLen == 0){
            return  s.substring(0,loopStrLen);
        }else {
            return s;
        }

    }

    /**
     * KMP求模式串的next数组
     * @param p
     * @return
     */
    public static int[] getNext(String p) {
        int n = p.length();
        int[] next = new int[n];
        Arrays.fill(next, -1);
        for (int i = 1; i < n; ++i) {
            int j = next[i - 1];
            while (j != -1 && p.charAt(j + 1) != p.charAt(i)) {
                j = next[j];
            }
            if (p.charAt(j + 1) == p.charAt(i)) {
                next[i] = j + 1;
            }
        }
        return next;
    }
}
