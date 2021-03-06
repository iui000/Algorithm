package GinkgoStack.P2_StringProblem.RepeatingSubstring;

import java.util.Scanner;

/**
 * 贝壳笔试题
 *
 * 题目意思是，构造一个字符串，只能有两种操作：
 * 1.增加一个字母；
 * 2.复制之前的某一个子串，但是这个操作最多只能有一次。
 *
 * 思路：本质上就是找一个串中的最长的重复子串，子串之间的下标不能有交集
 9
 ababababc

 13
 fxababjkababc

 6
 abcdff

 */



public class LongestRepeatingSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();

        int ans = LongestRepeatSubstr(s);
        if(ans == 0){
            System.out.println(n);
        }else {
            System.out.println(n - ans +1);
        }

    }


    /**
     * 找一个串中的最长的重复子串，子串之间的下标不能有交集
     *  13
     *  fxababjkababc
     *
     *  输出：4   （两个abab没有下标交集）
     * @param s
     * @return
     */
    static int LongestRepeatSubstr(String s) {
        int maxLen = 0;
        String pattern = "";
        String remaining = "";

        for (int begin = 0; begin < s.length(); begin++) {
            //在余下的串remaining中不可能包含一个长度比maxLen更大的重复串了
            if(begin + (maxLen + 1)*2 > s.length()){
                break;
            }

            /**
             * len起始值 设为maxLen + 1，是为了提高效率，
             * 因为目标是找到更长的串，而更短的串没有必要计算了，也避免一部分冗余计算
             */
            for (int len = maxLen + 1; begin + len * 2 <= s.length(); len++) {
                pattern = s.substring(begin,begin+len);
                remaining = s.substring(begin+len);

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
