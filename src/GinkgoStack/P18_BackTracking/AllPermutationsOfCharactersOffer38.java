package GinkgoStack.P18_BackTracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 */
public class AllPermutationsOfCharactersOffer38 {

    /**
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     *
     * 自己的修改
     */
    class Solution {
        List<String> res = new LinkedList<>();
        char[] charArr;

        public String[] permutation(String s) {
            charArr = s.toCharArray();
            dfs(0);
            return res.toArray(new String[res.size()]);
        }


        /**
         * 重复方案与剪枝： 当字符串存在重复字符时，排列方案中也存在重复方案。为排除重复方案，需在固定某位字符时，
         * 保证 “每种字符只在此位固定一次” ，即遇到重复字符时不交换，直接跳过。从 DFS 角度看，此操作称为 “剪枝” 。
         *
         * @param index 当前固定位 index ；
         */
        void dfs(int index) {

//            终止条件： 当 index = len(c) - 1时，代表所有位已固定（最后一位只有 11 种情况），
//            则将当前组合 charArr 转化为字符串并加入 res，并返回；
            if(index == charArr.length - 1) {
                res.add(String.valueOf(charArr)); // 添加排列方案
                return;
            }

            //将第index 字符分别与它之后的字符交换，并进入下层递归；
            //初始化一个 Set ，用于排除交换到index位置的重复字符，作用是剪枝；
            Set<Character> set = new HashSet<>();
            for(int i = index; i < charArr.length; i++) {
                if(set.contains(charArr[i]))
                    continue; // 重复，因此剪枝

                set.add(charArr[i]);

                swap(i, index); // 交换，将 charArr[i] 固定在第 index 位
                dfs(index + 1); // 开启固定第 index + 1 位字符
                swap(i, index); // 恢复交换
            }
        }


        void swap(int a, int b) {
            char tmp = charArr[a];
            charArr[a] = charArr[b];
            charArr[b] = tmp;
        }
    }


}
