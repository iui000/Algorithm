package GinkgoStack.P1_ArrayProblem.IntervalProblems;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. 区间列表的交集
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，
 * 而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，
 * 要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *  
 *
 * 提示：
 *
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoIntervalListIntersections986 {
    /**
     * 方法：归并区间
     * 思路
     *
     * 我们称 b 为区间 [a, b] 的末端点。
     *
     * 在两个数组给定的所有区间中，假设拥有最小末端点的区间是 A[0]。（为了不失一般性，该区间出现在数组 A 中)
     *
     * 然后，在数组 B 的区间中， A[0] 只可能与数组 B 中的至多一个区间相交。
     * （如果 B 中存在两个区间均与 A[0] 相交，那么它们将共同包含 A[0] 的末端点，但是 B 中的区间应该是不相交的，所以存在矛盾）
     *
     * 算法
     *
     * 如果 A[0] 拥有最小的末端点，那么它只可能与 B[0] 相交。然后我们就可以删除区间 A[0]，因为它不能与其他任何区间再相交了。
     *
     * 相似的，如果 B[0] 拥有最小的末端点，那么它只可能与区间 A[0] 相交，然后我们就可以将 B[0] 删除，因为它无法再与其他区间相交了。
     *
     * 我们用两个指针 i 与 j 来模拟完成删除 A[0] 或 B[0] 的操作。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/interval-list-intersections/solution/qu-jian-lie-biao-de-jiao-ji-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            List<int[]> ans = new ArrayList<>();
            int i = 0, j = 0;

            while (i < A.length && j < B.length) {
                // Let's check if A[i] intersects B[j].
                // lo - the startpoint of the intersection
                // hi - the endpoint of the intersection
                int low = Math.max(A[i][0], B[j][0]);
                int high = Math.min(A[i][1], B[j][1]);
                if (low <= high)
                    ans.add(new int[]{low, high});

                // Remove the interval with the smallest endpoint
                //如果 A[0] 拥有最小的末端点，那么它只可能与 B[0] 相交。然后我们就可以删除区间 A[0]，因为它不能与其他任何区间再相交了。
                if (A[i][1] < B[j][1])
                    i++;
                else//相似的，如果 B[0] 拥有最小的末端点，那么它只可能与区间 A[0] 相交，然后我们就可以将 B[0] 删除，因为它无法再与其他区间相交了。
                    j++;
            }

            return ans.toArray(new int[ans.size()][]);
        }
    }


}
