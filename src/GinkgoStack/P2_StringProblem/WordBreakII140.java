package GinkgoStack.P2_StringProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class WordBreakII140 {

    public List<String> wordBreak(String s,List<String> dict){
        return recursive(s,dict,new HashMap<String, List<String>>());
    }

    private List<String> recursive(String s, List<String> dict,
                                   Map<String,List<String>> prevRes){
        //记忆化搜索的精髓，不进行重复计算
        if(prevRes.containsKey(s)){
            return prevRes.get(s);
        }
        //结果
        ArrayList<String> result = new ArrayList<>();

        //基础条件
        if(s.length() == 0){
            result.add("");
            return result;
        }

        //遍历单词字典，判断s是否以字典中某些单词为前缀
        for (String word : dict){
            if(s.startsWith(word)){
                List<String> subRes =
                        recursive(s.substring(word.length()),dict,prevRes);
                for (String sub:subRes){//将子问题的每一个解与当前单词组合，组成当前问题的解
                    result.add(word + (sub.isEmpty() ? "":" ") + sub);
                }
            }
        }

        prevRes.put(s,result);
        return result;
    }
}
