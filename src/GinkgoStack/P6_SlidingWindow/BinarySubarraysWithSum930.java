package GinkgoStack.P6_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 *
 *
 *
 * 示例：
 *
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 *
 * 提示：
 *
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 */
public class BinarySubarraysWithSum930 {

    /**
     *     作者：LeetCode
     *     链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-leetcode/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 方法三：三指针
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N 是数组 A 的长度。
     *
     * 空间复杂度：O(1)。
     * 在方法二中，我们在固定区间的右端点 j 时，用计数器求出满足要求的左端点 i 的数目。
     * 而由于此题的特殊性，前缀和数组 P 是单调不降的，因此左端点的位置一定是连续的，
     * 即可以用 [i_lo, i_hi] 表示，并且随着 j 的增加，i_lo 和 i_hi 也单调不降，
     * 因此可以用类似双指针的方法，使用三个指针维护左端点的区间。
     *
     * 我们遍历区间的右端点 j，同时维护四个变量：
     *
     * sum_lo：A[i_lo..j] 的值；
     *
     * sum_hi：A[i_hi..j] 的值；
     *
     * i_lo：最小的满足 sum_lo <= S 的 i；
     *
     * i_hi：最大的满足 sum_hi <= S 的 i。
     *
     * 例如，当数组 A 为 [1,0,0,1,0,1]，S 的值为 2 。
     * 当 j = 5 时，i_lo 的值为 1，i_hi 的值为 3。
     * 对于每一个 j，和为 S 的非空子数组的数目为 i_hi - i_lo + 1。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int numSubarraysWithSum(int[] A, int S) {
            int iLo = 0, iHi = 0;
            int sumLo = 0, sumHi = 0;
            int ans = 0;

            for (int j = 0; j < A.length; ++j) {
                // While sumLo is too big, iLo++
                sumLo += A[j];
                while (iLo < j && sumLo > S)
                    sumLo -= A[iLo++];

                // While sumHi is too big, or equal and we can move, iHi++
                sumHi += A[j];
                while (iHi < j && (sumHi > S || sumHi == S && A[iHi] == 0))
                    sumHi -= A[iHi++];

                if (sumLo == S)
                    ans += iHi - iLo + 1;
            }

            return ans;
        }
    }

    /**
     * 方法二：前缀和
     * 分析
     *
     * 设数组 P 为数组 A 的前缀和，即：
     *
     * P[i] = A[0] + A[1] + ... + A[i - 1]
     *
     * 这样就可以通过 P[j + 1] - P[i] = A[i] + A[i + 1] + ... + A[j]
     * 快速计算出 A[i..j] 的和。
     *
     * 我们可以对数组 P 进行一次线性扫描，当扫描到 P[j] 时，
     * 我们需要得到的是满足 P[j] = P[i] + S 且 i < j 的 i 的数目，
     * 使用计数器（map 或 dict）可以满足要求。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        public int numSubarraysWithSum(int[] A, int S) {
            int N = A.length;
            int[] P = new int[N + 1];
            for (int i = 0; i < N; ++i)
                P[i+1] = P[i] + A[i];

            Map<Integer, Integer> count = new HashMap();
            int ans = 0;
            for (int x: P) {
                ans += count.getOrDefault(x, 0);
                count.put(x+S, count.getOrDefault(x+S, 0) + 1);
            }

            return ans;
        }
    }



}
