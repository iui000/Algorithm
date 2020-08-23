package GinkgoStack.P2_StringProblem;


/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {
    /**
     * 第一种思路：字典数，
     * 每个节点是记录了一个数量值count，表示的是存在多少个单词经过该字符；
     *
     * 在插入每一个单词过程中，
     *      就判断节点是否count == k，
     *          如果不等：如果是第一个节点，那就返回“”空串，否则就不必往里面加入字符了
     *          如果等，就将该节点继续加入到字典树中；
     * 直到最后一个单词，
     *      判断节点是否count == n，
     *          如果不等：如果是第一个节点，那就返回“”空串，否则就输出结果；
     *          如果等，就将该节点加入到结果中；
     * @param strs
     * @return
     */


    /**
     * 第二种思路：代码简洁一些
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length < 1){
            return "";
        }else if( strs[0].isEmpty()){
            return "";
        }
        char[] prefix = strs[0].toCharArray();
        int maxLen = prefix.length;
        int cur = 0;
        for(String s : strs){
            if(s.isEmpty()){
                return "";
            }
            char[] t = s.toCharArray();
            for(int i = 0;i < t.length && i < maxLen;i++){
                if(t[i] == prefix[i]){
                    cur = i;
                }else {
                    if(i == 0){
                        return "";
                    }
                    break;
                }
            }
            maxLen = cur+1;
        }
//        System.out.println(cur);
        return strs[0].substring(0,maxLen);
    }



    public static void main(String[] args) {
        String[] strings = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strings));
    }
}
