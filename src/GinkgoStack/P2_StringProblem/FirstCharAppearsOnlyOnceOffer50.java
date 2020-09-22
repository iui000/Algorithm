package GinkgoStack.P2_StringProblem;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 */
public class FirstCharAppearsOnlyOnceOffer50 {


    /**
     * 用Map来解决，其实没有直接用数组作为哈希性能好
     */
    class Solution {
        public char firstUniqChar(String s) {
            Map<Character, Boolean> dic = new LinkedHashMap<>();
            char[] sc = s.toCharArray();
            for(char c : sc)
                dic.put(c, !dic.containsKey(c));
            for(Map.Entry<Character, Boolean> d : dic.entrySet()){
                if(d.getValue()) return d.getKey();
            }
            return ' ';
        }
    }
}
