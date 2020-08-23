package GinkgoStack.P12_Math.Factorial;


/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 *
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class FactorialTrailingZeroes {

    /**
     * O(n)
     * 超时
     */
    /**
     * 阶乘末尾的每个 0 表示乘以 10。
     * 为了确定最后有多少个零，我们应该看有多少对 2 和 5 的因子。
     * 因子 2 数总是比因子 5 大。为什么？因为每四个数字算作额外的因子 2，但是只有每 25 个数字算作额外的因子 5。
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {

        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {//计算机这个数有多少个因子5
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }

    /**
     * O（log N）
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int zeroCount = 0;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

}
