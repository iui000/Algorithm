package GinkgoStack.P18_BackTrackingAndRecursion.CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII40 {

    /**
     * 关键点：
     * 1.candidates 中的每个数字在每个组合中只能使用一次。
     * 2.解集不能包含重复的组合。
     */

    /**
     * 官方题解
     *     作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/combination-sum-ii/solution/zu-he-zong-he-ii-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 回溯 + 剪枝 + 深拷贝
     */
    class Solution {
        //在求出组合的过程中就进行去重的操作。我们可以考虑将相同的数放在一起进行处理，
        // 也就是说，如果数x 出现了 y 次，那么在递归时一次性地处理它们，
        // 即分别调用选择 0, 1,...,y 次 x的递归函数。这样我们就不会得到重复的组合
        List<int[]> freq = new ArrayList<int[]>();//充当hash

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> sequence = new ArrayList<Integer>();//一个可行解

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            for (int num : candidates) {
                int size = freq.size();
                if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                    freq.add(new int[]{num, 1});//数字x和相应的次数y
                } else {
                    ++freq.get(size - 1)[1];
                }
            }
            dfs(0, target);
            return ans;
        }

        public void dfs(int pos, int rest) {
            if (rest == 0) {
                ans.add(new ArrayList<Integer>(sequence));
                return;
            }
            if (pos == freq.size() || rest < freq.get(pos)[0]) {
                return;
            }

            dfs(pos + 1, rest);

            //如果数x 出现了 y 次，那么在递归时一次性地处理它们，most就是最多取多少个x
            int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
            //做出选择并递归
            for (int i = 1; i <= most; ++i) {
                sequence.add(freq.get(pos)[0]);
                dfs(pos + 1, rest - i * freq.get(pos)[0]);
            }

            //撤销选择
            for (int i = 1; i <= most; ++i) {
                sequence.remove(sequence.size() - 1);
            }
        }
    }



    /**
     * https://blog.csdn.net/qq_43109561/article/details/90107955
     * 回溯 + 深拷贝 + 剪枝。（效率相对于官方题解低一点，但是代码简洁一些）
     */
    class Solution2 {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            //我们还可以进行什么优化（剪枝）呢？一种比较常用的优化方法是，
            // 我们将 candidates根据数从小到大排序，这样我们在递归时会先选择小的数，
            // 再选择大的数。
            Arrays.sort(candidates);//
            dfs(result,list,candidates,target,0);
            return result;
        }

        /**
         *
         * @param result
         * @param list
         * @param candidates
         * @param target
         * @param curIndex
         */
        private void dfs(List<List<Integer>> result,
                         List<Integer> list, int[] candidates,
                         int target, int curIndex){
            if(target < 0){
                return;
            }else if(target == 0){
                if(!result.contains(list)){//去重
                    result.add(list);
                }
                return;
            }else{
                for(int j=curIndex; j<candidates.length &&
                        candidates[j]<=target; j++){
                    List<Integer> tmp = new ArrayList<>(list);
                    tmp.add(candidates[j]);

                    dfs(result,tmp,candidates,
                            target-candidates[j],j+1);
                }
            }
        }
    }
}
