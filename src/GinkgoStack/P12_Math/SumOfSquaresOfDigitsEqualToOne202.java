package GinkgoStack.P12_Math;


import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 *
 *
 * 示例：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class SumOfSquaresOfDigitsEqualToOne202 {

    /**
     * 根据我们的探索，我们猜测会有以下三种可能。
     *
     * 最终会得到 11。
     * 最终会进入循环。
     * 值会越来越大，最后接近无穷大。(这种情况不会发生，下面证明)
     * 最大的三位数  999 ，经过一轮计算等于243
     * 最大的四位数  9999 ，经过一轮计算等于324
     * 最大的13位数 9999999999999 ，经过一轮计算等于1053；
     * 所以，会收缩变小，不会无限增大。
     *
     *     作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {

        private int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }

        public boolean isHappy(int n) {
            Set<Integer> seen = new HashSet<>();
            while (n != 1 && !seen.contains(n)) {
                seen.add(n);
                n = getNext(n);
            }
            return n == 1;
        }
    }



}
