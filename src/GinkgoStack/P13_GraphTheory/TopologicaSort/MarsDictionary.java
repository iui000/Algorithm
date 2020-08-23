package GinkgoStack.P13_GraphTheory.TopologicaSort;

import java.util.*;


/**
 * 269. 火星词典
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 *
 * 假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个 不为空的 单词列表。因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。
 *
 * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 输出: "wertf"
 * 示例 2：
 *
 * 输入:
 * [
 *   "z",
 *   "x"
 * ]
 * 输出: "zx"
 * 示例 3：
 *
 * 输入:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 * 输出: "" 
 * 解释: 此顺序是非法的，因此返回 ""。
 *  
 *
 * 提示：
 *
 * 你可以默认输入的全部都是小写字母
 * 若给定的顺序是不合法的，则返回空字符串即可
 * 若存在多种可能的合法字母顺序，请返回其中任意一种顺序即可
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/alien-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class MarsDictionary {
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
