package GinkgoStack.P6_SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumCoveringSubstring {

    /**
     * 这道题是典型的滑动窗口题目
     * 自己的解法
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int[] target = new int[128];
        int[] NeedToMatch = new int[128];
        int[] redundant = new int[128];
        Arrays.fill(target,-1);
        Arrays.fill(NeedToMatch,-1);
        int tLen = t.length();
        int sLen = s.length();

        for(int i=0;i< tLen;i++){
            int c = t.charAt(i);
            target[c] = target[c] == -1?1:target[c]+1;
            NeedToMatch[c] = NeedToMatch[c] == -1?1:NeedToMatch[c]+1;
        }

        int left=0,right = -1,begin = -1,end = -1;
        int matchNumber = 0;

        int minMatch = Integer.MAX_VALUE;

        while (right < sLen){
            if(matchNumber == tLen){//注意，这里已经可以证明现在的子串已经完全匹配了目标t,甚至这个子串中某些字符的数量 比 目标串中相应的字符的数量还多
                //左边收缩
                while (target[s.charAt(left)] <= 0){//说明这不属于目标串中的字符，直接丢弃
                    left++;
                }
                while (redundant[s.charAt(left)] > 0){//说明当前这个子串中还有多余的字符(目标串是包含此字符的，只是数量没有这么多，多出的字符存在了has中)
                    redundant[s.charAt(left)]--;
                    left++;
                }

                if(right - left+1 < minMatch){//统计长度
                    minMatch = right - left+1;
                    begin = left;
                    end = right;
                }
                //如果此字符并没有多余的了，但仍然要继续往后滑动，只是此时得把它加入到待匹配的集合中去，并且matchNumber减一
                if(target[s.charAt(left)] >0){
                    if(right == sLen-1)//如果右指针都已经到达末尾了，再退的话，剩下的子串其实已经不能满足要求了，直接返回结果
                        return (end >= begin && begin != -1)?s.substring(begin,end+1):"";
                    //否则，就还可以继续退
                    matchNumber--;
                    NeedToMatch[s.charAt(left)]++;
                    left++;
                }
            }else {

                //如果匹配值没有达到tLen ，而且右指针也已经到达末尾，那么就不必再计算了，直接返回结果
                if(right == sLen-1)
                    return (end >= begin && begin != -1)?s.substring(begin,end+1):"";

                if(right < sLen-1){
                    right++;
                    int c = s.charAt(right);

                    if(NeedToMatch[c] == 0){//如果该字符需要匹配的数目已经匹配完，但是又发现了它，就把它加入到多余字符redundant中去
                        redundant[c]++;
                    }
                    if(NeedToMatch[c]  > 0){
                        matchNumber++;
                        NeedToMatch[c]--;
                    }
                }
            }
        }
        return (end >= begin && begin != -1)?s.substring(begin,end+1):"";
    }

    /**
     * labuladong的解法
     *
     * 没有AC
     * @param s
     * @param t
     * @return
     */


    /*


1 : str2.charAt(i)
2: str1.length()  或者 ： Integer.MAX_VALUE
3：count
4 : str1.subString(left,right+1);
5: count
     */
    public static String minWindow2(String s, String t) {

        //当然，对于这道题来说可以用数组，比用Map更方便高效
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        for(int i = 0;i < t.length();i++){
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
        }

        int left = 0, right = 0;
        int valid = 0;//valid变量表示窗口中满足need条件的字符个数，如果valid和need.size的大小相同，则说明窗口已满足条件，已经完全覆盖了串T。
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c) == need.get(c))
                    valid++;
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left ;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d))
                        valid--;

                    if(window.get(d) == 1){
                        window.remove(d);
                    }else {
                        window.put(d,window.get(d)-1);
                    }

                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start+len);
    }



    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
        System.out.println(minWindow2(s,t));
    }
}
