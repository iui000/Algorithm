package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.*;

/**
 * 火星文字典
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 已知一种新的火星文的单词由英文字母（仅小写字母）组成，但是此火星文中的字母先后顺序未知。
 * 给出一组非空的火星文单词，且此组单词已经按火星文字典序进行好了排序（从小到大），请推断出此火星文中的字母先后顺序。
 *
 *
 * 输入描述:
 * 一行文本，为一组按火星文字典序排序好的单词(单词两端无引号)，单词之间通过空格隔开
 *
 *
 * 输出描述:
 * 按火星文字母顺序输出出现过的字母，字母之间无其他字符，
 * 如果无法确定顺序或者无合理的字母排序可能，请输出"invalid"(无需引号)
 *
 *
 *
 *
 * 输入例子1:
 * z x
 *
 * 输出例子1:
 * zx
 *
 * 输入例子2:
 * wrt wrf er ett rftt
 *
 * 输出例子2:
 * wertf
 *
 * 输入例子3:
 * z x z
 *
 * 输出例子3:
 * invalid
 */
public class MarsDictionary {

    /**
     *  * 原题来源：力扣（LeetCode）
     *  * 链接：https://leetcode-cn.com/problems/alien-dictionary
     *  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();//存储以此字母为前驱的后继节点集合
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            //得出相邻两个单词的长度的较大值，主要是为了应对测试用例["abc","ab"]
            int len = Math.max(words[i].length(), words[i + 1].length());

            for (int j = 0; j < len; j++) {
                // 要防止测试用例为["abc","ab"]这种情况
                if (j >= words[i].length()) break;
                if (j >= words[i+1].length()) return "";

                //两个单词从头开始对字母进行比对，直到找到第一个不相同的一对字母，就可得出这两个字母的相对顺序
                if (words[i].charAt(j) == words[i + 1].charAt(j)) {
                    continue;
                }

                Set<Character> set = graph.computeIfAbsent(words[i].charAt(j), k -> new HashSet<>());
                set.add(words[i + 1].charAt(j));
                graph.put(words[i].charAt(j), set);
                break;
            }
        }

        //拓扑排序----------------------------
        LinkedList<Character> queue = new LinkedList<>();
        //入度初始化
        int[] inDegree = new int[26];

        int numChar = 0;//表示单词列表中出现过字母的数量
        Arrays.fill(inDegree, -1);//初始时全部填充为-1表示都还未出现

        for (String word : words) {//然后将出现过的字母的入度都标记为0
            for (char c : word.toCharArray()) {
                inDegree[c - 'a'] = 0;
            }
        }
        //接着更新所有存在前驱的结点的入度更新
        for (Character key : graph.keySet()) {
            for (Character value: graph.get(key)) {
                inDegree[value - 'a']++;
            }
        }

        //将入度为0的字母都加入到队列中，以便后面BFS，入度为0的字母输出的顺序无关紧要，因此，可以按照英文字母顺序加入到队列中
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                queue.add((char) (i + 'a'));
            }
            //顺便统计 出现过的字母的数量
            if (inDegree[i] != -1) {
                numChar++;
            }
        }

        //按照BFS输出到结果集中
        while (!queue.isEmpty()) {
            Character firstChar = queue.poll();
            res.append(firstChar);
            if (graph.containsKey(firstChar)) {
                //每出一个字母，都判断它有没有后继字母，如果有，那么将它们的入度减一
                for (Character nextChar : graph.get(firstChar)) {
                    inDegree[nextChar - 'a']--;
                    if (inDegree[nextChar - 'a'] == 0) {//当某个字母的入度为0，那就加入队列
                        queue.add(nextChar);
                    }
                }
            }
        }
        if (res.length() != numChar) {
            return "";
        }
        return res.toString();
    }

}
