package GinkgoStack.P2_StringProblem;

import java.util.*;


/**
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {

    /**
     * 我的思路：
     * 在滑动窗口基础上改进查找性能，因为窗口中的字符不需要严格顺序，只是要记住每个字符的下标位置，因此用HashMap来作为滑动窗口
     * 查询性能提高，删除性能也没有下降。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> subs = new HashMap<>();

        char[] arr  = s.toCharArray();

        int len = s.length();
        int curLen = 0,start = 0;
        int res = 0;
        for(int i =0;i<len;i++){
            char c = arr[i];
            if(!subs.containsKey(c)){//如果窗口集合中没有这个字符，就继续往后滑动，往右扩张
                subs.put(c,i);
                curLen = curLen+1;
            }else {//如果已经出现过该字符，那么就应该将旧的那个字符及之前(指的在原字符串中的下标位置)的字符都弹出
                int index = subs.get(c);//记住之前出现的那个字符的位置
                curLen = i - index;//窗口剩余的长度
                for(int j = start;j <= index;j++){//左边收缩，将旧的那个字符和处于它左边(指的在原字符串中的下标位置)的字符都弹出
                    subs.remove(arr[j]);
                }
                subs.put(c,i);//然后把它加入
                start = index+1;//窗口中第一个字符的下标
            }
            if(curLen > res){//找到最大的长度值
                res = curLen;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s= "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }


}
