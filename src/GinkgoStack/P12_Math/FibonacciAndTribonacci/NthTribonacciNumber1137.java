package GinkgoStack.P12_Math.FibonacciAndTribonacci;

import java.util.HashMap;

/**
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 *
 *
 * 提示：
 *
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */
public class NthTribonacciNumber1137 {

    /**
     * dp + 空间压缩
     *     作者：LeetCode
     *     链接：https://leetcode-cn.com/problems/n-th-tribonacci-number/solution/di-n-ge-tai-bo-na-qi-shu-by-leetcode/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution1 {
        public int tribonacci(int n) {
            if (n < 3) return n == 0 ? 0 : 1;

            int tmp, x = 0, y = 1, z = 1;
            for (int i = 3; i <= n; ++i) {
                tmp = x + y + z;
                x = y;
                y = z;
                z = tmp;
            }
            return z;
        }
    }

    /**
     * 递归 + 记忆化
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x34zzi/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        //避免重复计算
        private HashMap<Integer, Integer> cache = new HashMap<>();

        public int tribonacci(int n) {
            //从缓存中获取
            if (cache.containsKey(n))
                return cache.get(n);

            /**
             * 边界条件
             */
            if (n == 0) {
                cache.put(n, 0);
                return 0;
            }
            if (n == 1 || n == 2) {
                cache.put(n, 1);
                return 1;
            }

            //递归
            int result =  tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
            //加入cache
            cache.put(n, result);
            return result;
        }
    }
}
