package GinkgoStack.P1_ArrayProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntervalMerging {

    /**
     * 思路1：排序 + 前后双指针
     * 1.先按照起始位置排序(并不需要结束位置也有序，当然，区间起始和结束位置稳定排序也是可以的)
     * 2.然后再从前往后根据起始和结束位置合并即可
     *
     * 时间复杂度o(N log N)
     * 空间复杂度O(n)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> theIntervals = Arrays.asList(intervals);
        theIntervals.sort((o1, o2) -> o1[0] - o2[0]);

        List<int[]> res = new ArrayList<>();
        for(int i = 0; i < theIntervals.size(); )
        {
            int end = theIntervals.get(i)[1];
            int begin = theIntervals.get(i)[0];

            int j = i + 1;
            //连续合并区间
            while(j < theIntervals.size() && theIntervals.get(j)[0] <= end)
            {
                end = Math.max(end, theIntervals.get(j)[1]);
                j++;
            }
            //直到某个区间的起始位置大于了end，就结束这次合并
            res.add(new int[]{begin, end});
            //设置下一个初始区间
            i = j;
        }

        //最后的答案
        int[][] ans = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++)
        {
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;
    }
}
