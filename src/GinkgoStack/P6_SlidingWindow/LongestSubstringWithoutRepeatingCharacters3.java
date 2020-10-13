package GinkgoStack.P6_SlidingWindow;

import java.util.*;


/**
 * 3.无重复字符的最长子串
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
public class LongestSubstringWithoutRepeatingCharacters3 {

    /**
     * 我的思路：
     * 在滑动窗口基础上改进查找性能，因为窗口中的字符不需要严格顺序，
     * 只是要记住每个字符的下标位置，因此用HashMap来作为滑动窗口
     * 查询性能提高，删除性能也没有下降。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        //每个字符的下标位置
        Map<Character,Integer> window = new HashMap<>();

        char[] arr  = s.toCharArray();

        int len = s.length();
        int curLen = 0,start = 0;
        int res = 0;
        for(int i =0;i<len;i++){
            char c = arr[i];
            //如果窗口集合中没有这个字符，就继续往后滑动，往右扩张
            if(!window.containsKey(c)){
                window.put(c,i);
                curLen = curLen+1;
            }else {//如果已经出现过该字符，那么就应该将旧的那个字符及之前(指的在原字符串中的下标位置)的字符都弹出.
                //记住之前出现的那个字符的位置
                int index = window.get(c);
                //窗口剩余的长度
                curLen = i - index;
                //左边收缩，将旧的那个字符和处于它左边(指的在原字符串中的下标位置)的字符都弹出
                for(int j = start;j <= index;j++){
                    window.remove(arr[j]);
                }
                //然后把它加入
                window.put(c,i);
                //更新窗口中第一个字符的下标
                start = index+1;
            }
            //找到最大的长度值
            if(curLen > res){
                res = curLen;
            }
        }
        return res;
    }


    /**
     * labuladong
     * need和valid都不需要，而且更新窗口内数据也只需要简单的更新计数器window即可。
     *
     * 当window[c]值大于 1 时，说明窗口中存在重复字符，不符合条件，就该移动left缩小窗口了嘛。
     *
     * 唯一需要注意的是，在哪里更新结果res呢？我们要的是最长无重复子串，
     * 哪一个阶段可以保证窗口中的字符串是没有重复的呢？
     *
     * 这里和之前不一样，要在收缩窗口完成后更新res，因为窗口收缩的 while 条件是存在重复元素，
     * 换句话说收缩完成后一定保证窗口中没有重复嘛。
     *
     * c++代码
     * int lengthOfLongestSubstring(string s) {
     *     unordered_map<char, int> window;
     *
     *     int left = 0, right = 0;
     *     int res = 0; // 记录结果
     *     while (right < s.size()) {
     *         char c = s[right];
     *         right++;
     *         // 进行窗口内数据的一系列更新
     *         window[c]++;
     *         // 判断左侧窗口是否要收缩
     *         while (window[c] > 1) {
     *             char d = s[left];
     *             left++;
     *             // 进行窗口内数据的一系列更新
     *             window[d]--;
     *         }
     *         // 在这里更新答案
     *         res = max(res, right - left);
     *     }
     *     return res;
     * }
     */
    public int lengthOfLongestSubstring2(String s){
        Map<Character,Integer> window = new HashMap<>();
        int left = 0,right = 0;
        int res = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            right++;
            //进行窗口内数据的一系列更新
            window.put(c,window.getOrDefault(c,0) +1);
            //判断左侧窗口是否要收缩
            while (window.get(c) > 1){
                char d = s.charAt(left);
                left++;
                //进行窗口内数据的一系列更新
                window.put(d,window.get(d)-1);
            }
            //最后更新答案
            res = Math.max(res,right - left);
        }

        return res;
    }

    public static void main(String[] args) {
        String s= "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }


}
